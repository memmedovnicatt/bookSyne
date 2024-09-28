package com.example.booksyne.servis.impl;


import com.example.booksyne.dao.entity.User;
import com.example.booksyne.dao.repository.UserRepository;
import com.example.booksyne.mapper.UserMapper;
import com.example.booksyne.model.dto.request.UserLoginDto;
import com.example.booksyne.model.dto.request.UserRegisterDto;
import com.example.booksyne.servis.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;



    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        User user=new User();
        user.setFullName(userRegisterDto.getFullName());
        user.setUsername(userRegisterDto.getUsername());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setPhoneNumber(userRegisterDto.getPhoneNumber());
        user.setBirthday(userRegisterDto.getBirthday());
        user.setRole(userRegisterDto.getRole());
        userRepository.save(user);
    }
}
