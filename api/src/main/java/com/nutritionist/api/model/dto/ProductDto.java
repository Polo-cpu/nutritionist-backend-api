package com.nutritionist.api.model.dto;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productNameDto;

    private String productDetailsDto;

    private Double productPriceDto;
}
