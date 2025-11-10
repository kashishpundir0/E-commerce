package com.ecommerce.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    private  User userId;
    private Date orderDate;
    private Double totalAmount;
}
