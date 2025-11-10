package com.ecommerce.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    private User user;
    @OneToOne
    private Products products;
    private Date orderDate;
    private Integer quantity;
    private Double totalAmount;
}
