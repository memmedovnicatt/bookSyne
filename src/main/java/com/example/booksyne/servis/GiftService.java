package com.example.booksyne.servis;

import com.example.booksyne.dao.entity.Gift;
import org.springframework.stereotype.Service;

@Service
public interface GiftService {
    Gift add(Gift gift);

}
