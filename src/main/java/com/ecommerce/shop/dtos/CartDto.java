package com.ecommerce.shop.dtos;

import com.ecommerce.shop.entity.Product;
import com.ecommerce.shop.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

public class CartDto {
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;

}
