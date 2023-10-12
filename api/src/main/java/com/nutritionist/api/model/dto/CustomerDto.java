package com.nutritionist.api.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String gender;
    @NotEmpty
    private Integer age;
    @NotEmpty
    private Double height;
    @NotEmpty
    private Double weight;
    @NotEmpty
    private LocalDate startOperation;
}
