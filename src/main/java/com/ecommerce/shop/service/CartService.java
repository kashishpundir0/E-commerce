package com.ecommerce.shop.service;

import com.ecommerce.shop.dtos.CartDto;
import com.ecommerce.shop.entity.Cart;
import com.ecommerce.shop.entity.Product;
import com.ecommerce.shop.entity.User;
import com.ecommerce.shop.repository.CartRepository;
import com.ecommerce.shop.repository.ProductRepository;
import com.ecommerce.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

//import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Object addToCart(CartDto request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("User not found with this id : "+ request.getUserId()));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(()-> new RuntimeException("Product not found with this id : "+ request.getProductId()));

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(request.getQuantity());
        return Map.of("data", cartRepository.save(cart));
    }

    public List<Cart> viewCart(){
        return cartRepository.findAll();
    }
    public List<Cart> getCartByUser(Long userId){
        return cartRepository.findByUserId(userId);
    }
    public void removeCart(Long cartId){
        cartRepository.deleteById(cartId);
    }
    public void clearCart(Long userId){
        List<Cart> userCarts = cartRepository.findByUserId(userId);
        cartRepository.deleteAll(userCarts);
    }


}
