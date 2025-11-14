package com.ecommerce.shop.controller;

import com.ecommerce.shop.dtos.PaymentDto;
import com.ecommerce.shop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<?> pay(@ModelAttribute PaymentDto request){
        return ResponseEntity.ok(paymentService.createPayment(request));
    }
}
