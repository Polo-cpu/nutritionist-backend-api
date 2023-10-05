package com.nutritionist.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


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
    @Column(name = "local_time")
    private LocalDate startOperation;


}
