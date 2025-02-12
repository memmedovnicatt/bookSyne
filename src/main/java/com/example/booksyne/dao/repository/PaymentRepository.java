package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
//    Optional<Payment> findByUserId(Integer userId);
}
