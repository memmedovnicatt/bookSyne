package com.example.booksyne.mapper;

import com.example.booksyne.dao.entity.User;
import com.example.booksyne.model.dto.request.UserRegisterDto;
import com.example.booksyne.model.dto.response.NewUserInformation;
import com.example.booksyne.model.dto.response.UserProfileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    User toUser(UserRegisterDto userRegisterDto);

    UserProfileResponse toUserProfile(User user);

    void toNewUserInfos(NewUserInformation newUserInformation, @MappingTarget User user);
}
