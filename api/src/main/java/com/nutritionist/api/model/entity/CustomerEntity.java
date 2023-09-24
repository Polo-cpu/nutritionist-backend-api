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
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "customer_name")
    private String name;
    @Column(name = "customer_sex")
    private String sex;
    @Column(name = "customer_age")
    private Integer age;
    @Column(name = "customer_height")
    private Double height;
    @Column(name = "customer_weight")
    private Double weight;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
