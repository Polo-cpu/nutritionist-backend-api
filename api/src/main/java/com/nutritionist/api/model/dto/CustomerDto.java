package com.nutritionist.api.model.dto;

import com.nutritionist.api.model.entity.NutritionistEntity;
import com.nutritionist.api.model.entity.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotEmpty
    private String name;
    private String gender;
    @NotEmpty
    private Integer age;
    @NotEmpty
    private Double height;
    @NotEmpty
    private Double weight;
    @NotEmpty
    private LocalDate startOperation;
    private NutritionistEntity nutritionist;
    private List<ProductEntity> products;

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        String genderType = "nonBinary";
        if(gender == null){
            gender = genderType;
            this.gender = gender;
        }
        else{
            this.gender = gender;
        }
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setStartOperation(LocalDate startOperation) {
        this.startOperation = startOperation;
    }

    public void setNutritionist(NutritionistEntity nutritionist) {
        this.nutritionist = nutritionist;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
