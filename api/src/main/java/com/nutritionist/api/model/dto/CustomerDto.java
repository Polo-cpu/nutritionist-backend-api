package com.nutritionist.api.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDto {

    private String nameDto;
    private String sexDto;
    private Integer ageDto;
    private Double heightDto;
    private Double weightDto;
}
