package com.ecommerce.shop.service;

import com.ecommerce.shop.config.JwtTokenUtil;
import com.ecommerce.shop.entity.User;
import com.ecommerce.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    // register newUSer
    public Object register(User request){
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User saveUser = repo.save(request);
        return Map.of("message", "UserRegistered successfully", "data", saveUser);
    }

    //Login User
    public Object loginUser(User request){
        User existUser = repo.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(), existUser.getPassword())){
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtTokenUtil.generateToken(existUser.getEmail());

        return Map.of("message", "Login successfully", "token", token, "user", existUser);
    }

}
