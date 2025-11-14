package com.ecommerce.shop.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {
    private Long userId;
    private Long productId;
    private Integer quantity;
}
