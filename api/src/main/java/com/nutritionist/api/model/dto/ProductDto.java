package com.nutritionist.api.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductDto {
    private String productNameDto;

    private String productDetailsDto;

    private String productPriceDto;
}
