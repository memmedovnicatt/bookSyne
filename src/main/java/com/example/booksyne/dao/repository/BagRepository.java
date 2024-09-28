package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.Bag;
import com.example.booksyne.model.dto.response.BagInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BagRepository extends JpaRepository<Bag,Integer> {

    Page<Bag> findAll(Pageable pageable);

    Optional<Bag> findByType(String type);
}
