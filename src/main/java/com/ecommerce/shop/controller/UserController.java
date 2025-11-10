package com.ecommerce.shop.controller;

import com.ecommerce.shop.config.JwtTokenUtil;
import com.ecommerce.shop.entity.User;
import com.ecommerce.shop.repository.UserRepository;
import com.ecommerce.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@ModelAttribute User user){
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@ModelAttribute User user){
        return ResponseEntity.ok(userService.loginUser(user));
    }




}
