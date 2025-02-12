package com.example.booksyne.service.impl;

import com.example.booksyne.dao.entity.PasswordResetToken;
import com.example.booksyne.dao.entity.User;
import com.example.booksyne.dao.repository.PasswordResetTokenRepository;
import com.example.booksyne.dao.repository.UserRepository;
import com.example.booksyne.mapper.UserMapper;
import com.example.booksyne.model.dto.request.*;
import com.example.booksyne.model.dto.response.ErrorResponse;
import com.example.booksyne.model.dto.response.LoginResponse;
import com.example.booksyne.model.enums.Role;
import com.example.booksyne.model.exception.child.*;
import com.example.booksyne.service.AuthService;
import com.example.booksyne.service.EmailSenderService;
import com.example.booksyne.service.JwtService;
import com.example.booksyne.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmailSenderService emailSenderService;
    private final PasswordResetTokenRepository tokenRepository;
    private final UserService userService;


    @Override
    public void register(UserRegisterDto userRegisterDto) {
        log.info("Registering new user:{}", userRegisterDto);
        User user = userMapper.toUser(userRegisterDto);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        if (userRepository.existsByUsername(userRegisterDto.getUsername())) {
            throw new AlreadyExistsException("Username " + userRegisterDto.getUsername() + " already exist");
        }
        if ((userRepository.existsByEmail(userRegisterDto.getEmail()))) {
            throw new AlreadyExistsException("Email " + userRegisterDto.getEmail() + " already exist");
        }
        if (userRepository.existsByPhoneNumber(userRegisterDto.getPhoneNumber())) {
            throw new AlreadyExistsException("Phone number " + userRegisterDto.getPhoneNumber() + " already exist");
        }
        userRepository.save(user);
        log.info("User successfully registered: username={}", userRegisterDto.getUsername());
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        log.info("authenticate method started by: {}", loginRequest.getUsername());
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            String token = jwtService.createToken(new User(loginRequest.getUsername()));
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setUsername(loginRequest.getUsername());
            return ResponseEntity.ok(loginResponse);
        } catch (BadCredentialsException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid username or password");
            log.error("Error due to: {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            log.error("Error due to {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    public ResponseEntity<String> requestPasswordReset(String email) {
        log.info("RequestPasswordResetMethod was started with email:{}", email);
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException(email + " does not belong to any user"));
        log.info("Email:{} was found", email);
        Integer userId = user.getId();
        Optional<PasswordResetToken> passwordResetToken = tokenRepository.findByUserId(userId);
        log.info("{}",passwordResetToken);
        passwordResetToken.ifPresent(tokenRepository::delete);
        String newToken = generateRandomToken();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);
        Date expiryDate = calendar.getTime();

        EmailRequest receiverEmail = new EmailRequest();
        receiverEmail.setReceiver(email);
        receiverEmail.setText(newToken);
        receiverEmail.setSubject("BookSyne - recovery password");
        try {
            emailSenderService.sendSimpleEmail(receiverEmail);
            createToken(user, newToken, expiryDate);
            log.info("{}",passwordResetToken);
        } catch (Exception e) {
            log.error("Error due to: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Email couldn't sent. Try again.");
        }
        log.info("token sent with email for recovery password to {}", email);
        return ResponseEntity.ok("Ok. Verify token was sent to your email");
    }

    private void deleteToken(PasswordResetToken token) {
        tokenRepository.delete(token);
    }

    @Override
    public ResponseEntity<String> resetPassword(RecoveryPassword recoveryPassword) {
        log.info("resetPassword method started by token: {}", recoveryPassword.getToken());
        if (recoveryPassword.getNewPassword().equals(recoveryPassword.getRetryPassword())) {
            PasswordResetToken passwordResetToken = tokenRepository.findByToken(recoveryPassword.getToken());
            if (!isTokenValid(passwordResetToken)) {
                log.info("Token is not valid");
                return ResponseEntity.badRequest().body("Ops! Something went wrong!");
            }
            User user = passwordResetToken.getUser();
            userService.changePassword(user, passwordEncoder.encode(recoveryPassword.getNewPassword()));
            log.info("password changed for userId: {}", user.getId());
            deleteToken(passwordResetToken);
            log.info("Password successfully reset by token: {}", recoveryPassword.getToken());
            return ResponseEntity.ok("Password reset successfully!");
        } else
            log.error("passwords entered do not match");
        throw new PasswordMismatchException();
    }


    private boolean isTokenValid(PasswordResetToken token) {
        log.info("isTokenValid method was started");
        return token != null && !token.getExpiryDate().before(new Date());
    }


    private String generateRandomToken() {
        log.info("generateRandomToken method was started");
        SecureRandom random = new SecureRandom();
        int TOKEN_LENGTH = 32;
        byte[] bytes = new byte[TOKEN_LENGTH / 2];
        random.nextBytes(bytes);
        return new BigInteger(1, bytes).toString(16);
    }

    private void createToken(User user, String token, Date expiryDate) {
        log.info("createToken method was started");
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUser(user);
        log.info("user set to passwordResetToken");
        passwordResetToken.setToken(token);
        log.info("token set to passwordResetToken");
        passwordResetToken.setExpiryDate(expiryDate);
        log.info("expiryDate set to passwordResetToken");
        tokenRepository.save(passwordResetToken);
        log.info("Token created for forgot password function and saved");
    }
}