package com.nutritionist.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="nutritionist_name")
    private String nutritionistName;
    @Column(name="nutritionist_profession")
    private String profession;
    @Column(name="availability")
    private Boolean isAvailable;

}
