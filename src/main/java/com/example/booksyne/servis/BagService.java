package com.example.booksyne.servis;

import com.example.booksyne.dao.entity.Bag;
import com.example.booksyne.model.dto.request.BagRequestDto;
import com.example.booksyne.model.dto.response.BagInfoResponse;
import com.example.booksyne.model.dto.response.BagResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BagService {
    BagInfoResponse getById(Integer id);

    BagResponse getByName(String name);
    BagResponse add(BagRequestDto bagRequestDto);
    void delete(Integer id);

    void update(BagRequestDto bagRequestDto,Integer id);

    Page<BagResponse> getAll(Pageable pageable);

}
