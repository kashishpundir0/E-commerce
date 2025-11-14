package com.ecommerce.shop.dtos;

import com.ecommerce.shop.entity.Order;
import com.ecommerce.shop.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentDto {
    private Long userId;
    private Long orderId;
    private String paymentMode;
}
