package com.example.booksyne.model.dto.response;

import com.example.booksyne.dao.entity.Publisher;
import lombok.Data;

@Data
public class MagazineInfoResponse {
    private String publisherName;
    private String publisherAddress;
    private String publisherPhoneNumber;
    private String publisherEmail;
}
