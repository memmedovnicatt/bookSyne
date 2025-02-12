package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Integer> {
    PasswordResetToken findByToken(String token);

    Optional<PasswordResetToken> findByUserId(Integer id);

    List<PasswordResetToken> findByExpiryDateBefore(Date date);
}
