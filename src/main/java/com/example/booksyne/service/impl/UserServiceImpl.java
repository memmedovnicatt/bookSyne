package com.example.booksyne.service.impl;

import com.example.booksyne.dao.entity.User;
import com.example.booksyne.dao.repository.UserRepository;
import com.example.booksyne.mapper.UserMapper;
import com.example.booksyne.model.dto.request.ChangePasswordDto;
import com.example.booksyne.model.dto.response.UserProfileResponse;
import com.example.booksyne.model.exception.child.NotFoundException;
import com.example.booksyne.model.exception.child.PasswordWrongException;
import com.example.booksyne.service.JwtService;
import com.example.booksyne.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserProfileResponse showProfile(String username) {
        log.info("Show profile method was started for user:{}", username);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("This user not found with this username:" + username));
        log.info("User found with this username:{}", username);
        return userMapper.toUserProfile(user);

    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<String> changePassword(HttpServletRequest request, ChangePasswordDto changePasswordDto) {
        try {
            Integer userId = jwtService.getUserId(jwtService.resolveClaims(request));
            log.info("user changePassword method started by userId: {}", userId);
            User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("This user not found"));
            if (changePasswordDto.getNewPassword().equals(changePasswordDto.getRetryPassword()) && passwordEncoder.matches(changePasswordDto.getCurrentPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
                userRepository.save(user);
                log.info("Password changed by user_id = {}", userId);
                return ResponseEntity.ok("Password changed successfully");
            } else {
                log.error("failed to change password");
                throw new PasswordWrongException();
            }
        } catch (Exception e) {
            log.error("Error changing password: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Failed to change password: " + e.getMessage());
        }
    }
}
