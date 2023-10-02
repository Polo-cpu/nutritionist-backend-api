package com.nutritionist.api.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Data
@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "visits")
public class Visit {

    @Column(name = "visit_date",columnDefinition = "DATE")
    private LocalDate localDate;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @Column(name = "customer_id")
    private CustomerEntity customer;

}
