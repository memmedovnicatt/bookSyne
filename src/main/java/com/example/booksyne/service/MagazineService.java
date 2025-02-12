package com.example.booksyne.service;

import com.example.booksyne.dao.entity.User;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.dto.request.MagazineDtoForUpdate;
import com.example.booksyne.model.dto.request.MagazineRequestDto;
import com.example.booksyne.model.dto.response.MagazineInfoResponse;
import com.example.booksyne.model.dto.response.MagazineResponse;
import com.example.booksyne.model.dto.response.MagazineResponseForGetAll;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface MagazineService {
    MagazineResponse add(MagazineRequestDto magazineRequest);

    void softDelete(Integer id, LocalDateTime localDateTime);

    void activateProduct(Integer id, LocalDateTime createdAt);

    MagazineInfoResponse getById(Integer id);

    Page<MagazineResponseForGetAll> getAll(Pageable pageable);

    void update(MagazineDtoForUpdate magazineDtoForUpdate, Integer id);
}
