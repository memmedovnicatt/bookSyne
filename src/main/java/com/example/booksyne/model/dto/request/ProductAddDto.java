package com.example.booksyne.model.dto.request;

import com.example.booksyne.model.enums.ProductType;
import lombok.Data;

@Data
public class ProductAddDto {
    private String methodName;
    private String serviceName;
    private ProductType productType;
}
