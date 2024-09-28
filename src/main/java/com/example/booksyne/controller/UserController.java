package com.example.booksyne.controller;

import com.example.booksyne.dao.entity.User;
import com.example.booksyne.dao.repository.UserRepository;
import com.example.booksyne.model.dto.request.UserLoginDto;
import com.example.booksyne.model.dto.request.UserRegisterDto;
import com.example.booksyne.servis.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping()
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDto userLoginDto) {
        User user = userRepository.findByUsername(userLoginDto.getUsername());
        if (user != null && passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            return ResponseEntity.ok("Login successfully");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        userService.registerUser(userRegisterDto);
        return ResponseEntity.ok("Register completed");
    }
}