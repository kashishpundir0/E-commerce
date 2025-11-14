package com.ecommerce.shop.service;

import com.ecommerce.shop.dtos.PaymentDto;
import com.ecommerce.shop.entity.Order;
import com.ecommerce.shop.entity.Payment;
import com.ecommerce.shop.entity.User;
import com.ecommerce.shop.repository.OrderRepository;
import com.ecommerce.shop.repository.PaymentRepository;
import com.ecommerce.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepo;
    private final UserRepository userRepo;
    private final OrderRepository orderRepo;

    public Object createPayment(PaymentDto request){
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found"));

        Order order = orderRepo.findById(request.getOrderId())
                .orElseThrow(()-> new RuntimeException("Order not found"));

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setPaymentStatus("SUCCESS");
        payment.setPaymentMode(request.getPaymentMode());
        payment.setPaymentDate(new Date());

        return paymentRepo.save(payment);
    }


}
