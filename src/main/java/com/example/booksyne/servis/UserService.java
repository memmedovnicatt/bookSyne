package com.example.booksyne.servis;

import com.example.booksyne.model.dto.request.UserLoginDto;
import com.example.booksyne.model.dto.request.UserRegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

}
