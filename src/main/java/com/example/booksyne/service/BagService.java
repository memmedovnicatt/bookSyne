package com.example.booksyne.service;

import com.example.booksyne.dao.entity.User;
import com.example.booksyne.model.dto.request.BagRequestDto;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.dto.response.BagInfoResponse;
import com.example.booksyne.model.dto.response.BagResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface BagService {
    BagInfoResponse getById(Integer id);

    List<BagResponse> getByName(String name);

    BagResponse add(BagRequestDto bagRequestDto);

    void softDelete(Integer id, LocalDateTime localDateTime);

    void update(BagRequestDto bagRequestDto, Integer id);

    Page<BagResponse> getAll(Pageable pageable);

    void activateProduct(Integer id, LocalDateTime createdAt);
}
