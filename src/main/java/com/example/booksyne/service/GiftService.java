package com.example.booksyne.service;

import com.example.booksyne.dao.entity.Gift;
import com.example.booksyne.dao.entity.User;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.dto.request.GiftRequestDto;
import com.example.booksyne.model.dto.request.GiftRequestForUpdateDto;
import com.example.booksyne.model.dto.response.GiftDetailByAddress;
import com.example.booksyne.model.dto.response.GiftInfoResponse;
import com.example.booksyne.model.dto.response.GiftResponseDto;
import com.example.booksyne.model.dto.response.GiftResponseForGetAll;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GiftService {
    GiftResponseDto add(GiftRequestDto giftRequestDto);

    void delete(Integer id);

    GiftInfoResponse getById(Integer id);

    List<GiftDetailByAddress> getGiftsByAddress(String address);

    void update(GiftRequestForUpdateDto giftRequestForUpdateDto, Integer id);

    Page<GiftResponseForGetAll> getAll(Pageable pageable);
}
