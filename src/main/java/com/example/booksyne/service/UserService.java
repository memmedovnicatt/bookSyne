package com.example.booksyne.service;

import com.example.booksyne.dao.entity.User;
import com.example.booksyne.model.dto.request.ChangePasswordDto;
import com.example.booksyne.model.dto.response.NewUserInformation;
import com.example.booksyne.model.dto.response.UserProfileResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.mapstruct.MappingTarget;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserProfileResponse showProfile(String username);

    void changePassword(User user, String newPassword);

    ResponseEntity<String> changePassword(HttpServletRequest request, ChangePasswordDto changePasswordDto);

//    void editProfile(NewUserInformation newUserInformation);


}
