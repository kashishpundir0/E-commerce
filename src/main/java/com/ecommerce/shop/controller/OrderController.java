package com.ecommerce.shop.controller;


import com.ecommerce.shop.dtos.OrderDto;
import com.ecommerce.shop.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController{
    private final OrderService orderService;

    @PutMapping("/place")
    public ResponseEntity<?> placeOrder(@ModelAttribute OrderDto request){
        return ResponseEntity.ok(orderService.placeOrder(request));
    }

    @GetMapping("/view")
        public ResponseEntity<?> viewOrder(){
            return ResponseEntity.ok(orderService.viewOrder());
        }

     @DeleteMapping("/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id){
        orderService.cancelOrder(id);
        return ResponseEntity.ok("Cancel item successfully");
     }
}
