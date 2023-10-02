package com.nutritionist.api.model.dto;

import com.nutritionist.api.model.entity.CustomerEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
@Data
@Getter
public class VisitDto {

    private LocalDate localDate;

    private String description;

    private CustomerEntity customer;
}
