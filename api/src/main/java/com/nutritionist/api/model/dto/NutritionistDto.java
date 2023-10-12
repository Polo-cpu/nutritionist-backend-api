package com.nutritionist.api.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NutritionistDto {
    @NotEmpty
    private String nutritionistName;
    @NotEmpty
    private String profession;
    @NotEmpty
    private Boolean isAvailable;
}
