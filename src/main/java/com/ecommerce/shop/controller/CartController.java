package com.ecommerce.shop.controller;

import com.ecommerce.shop.dtos.CartDto;
import com.ecommerce.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@ModelAttribute CartDto request){
        return ResponseEntity.ok(cartService.addToCart(request));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllCart(){
        return ResponseEntity.ok(cartService.viewCart());
    }
    @GetMapping("user/{id}")
    public ResponseEntity<?> getUserCart(@PathVariable Long userId){
        return ResponseEntity.ok(cartService.getCartByUser(userId));
    }
    @DeleteMapping("delete/{cartId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long cartId){
        cartService.removeCart(cartId);
        return ResponseEntity.ok("item removed");
    }
    @DeleteMapping("/clear/{userId}")
    public  ResponseEntity<?> clearCart(@PathVariable Long userId){
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart clear successfully");
    }

}
