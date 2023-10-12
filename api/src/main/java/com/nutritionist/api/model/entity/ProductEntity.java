package com.nutritionist.api.model.entity;

import lombok.*;

import jakarta.persistence.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_details")
    private String productDetails;
    @Column(name = "product_price")
    private Double productPrice;
    @ManyToMany
    @JoinColumn()
    private List<CustomerEntity> customers;

}
