package com.nutritionist.api.model.dto;

import com.nutritionist.api.model.entity.CustomerEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class NutritionistDto {
    @NotEmpty
    private String nutritionistName;
    @NotEmpty
    private String profession;
    @NotEmpty
    private Boolean isAvailable;
    private List<CustomerEntity> customers;
}
