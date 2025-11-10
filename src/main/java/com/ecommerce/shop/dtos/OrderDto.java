package com.ecommerce.shop.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {
    private  Long id;
    private Long userId;
    private Long productId;
    private Date orderDate;
    private Integer quantity;
    private Double totalAmount;
}
