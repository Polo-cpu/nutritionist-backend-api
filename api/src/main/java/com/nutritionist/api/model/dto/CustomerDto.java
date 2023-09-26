package com.nutritionist.api.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class CustomerDto {

    private String nameDto;
    private String sexDto;
    private Integer ageDto;
    private Double heightDto;
    private Double weightDto;
}
