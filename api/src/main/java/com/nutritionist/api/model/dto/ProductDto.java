package com.nutritionist.api.model.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductDto {
    private String productNameDto;

    private String productDetailsDto;

    private String productPriceDto;
}
