package com.ecommerce.shop.dtos;

import com.ecommerce.shop.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {
    private  Long id;
    private User userId;
    private Date orderDate;
    private Double totalAmount;
}
