package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.Supplier;
import com.example.booksyne.model.dto.response.GiftDetailByAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Optional<Supplier> findByAddress(String address);

}
