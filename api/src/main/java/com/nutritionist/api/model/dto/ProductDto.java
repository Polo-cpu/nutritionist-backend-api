package com.nutritionist.api.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotEmpty
    private String productName;
    @NotEmpty
    private String productDetails;
    @NotEmpty
    private Double productPrice;
}
