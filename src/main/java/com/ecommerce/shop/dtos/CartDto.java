package com.ecommerce.shop.dtos;

import lombok.Data;

@Data

public class CartDto {
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;

}
