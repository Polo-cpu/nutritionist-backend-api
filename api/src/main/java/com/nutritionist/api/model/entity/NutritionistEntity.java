package com.nutritionist.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "nutritionist")
public class NutritionistEntity {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    @Column(name="nutritionist_name")
    private String nutritionistName;
    @Column(name="nutritionist_profession")
    private String profession;
    @Column(name="availability")
    private Boolean isAvailable;
    @OneToMany(mappedBy = "nutritionist")
    private List<CustomerEntity> customers;

}
