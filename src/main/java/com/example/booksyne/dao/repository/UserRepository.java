package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.User;
import com.example.booksyne.model.dto.request.UserLoginDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
