package com.nutritionist.api.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Data

public class CustomerDto {

    private String nameDto;
    private String sexDto;
    private int ageDto;
    private double heightDto;
    private double weightDto;
}
