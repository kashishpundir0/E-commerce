package com.ecommerce.shop.service;

import com.ecommerce.shop.dtos.CartDto;
import com.ecommerce.shop.entity.Cart;
import com.ecommerce.shop.entity.Products;
import com.ecommerce.shop.entity.User;
import com.ecommerce.shop.repository.CartRepository;
import com.ecommerce.shop.repository.ProductRepository;
import com.ecommerce.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Products products = productRepository.findById(request.getProductId())
                .orElseThrow(()-> new RuntimeException("Products not found with this id : "+ request.getProductId()));

        List<Cart> cartlist = user.getCart()!=null?user.getCart():new ArrayList<>();
        Cart cart=new Cart();
        cart.setProducts(products);
        cart.setQuantity(request.getQuantity());
        cartlist.add(cartRepository.save(cart));

        user.setCart(cartlist);

        return Map.of("data",userRepository.save(user).getCart() );

    }

    public List<Cart> viewCart(){
        return cartRepository.findAll();
    }
    public List<Cart> getCartByUser(Long userId){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User doesn't Exist"));
        return user.getCart();
    }
    public void removeCart(Long cartId){
        cartRepository.deleteById(cartId);
    }
    public void clearCart(Long userId){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User doesn't Exist"));
        cartRepository.deleteAll(user.getCart());
        user.setCart(new ArrayList<>());
        userRepository.save(user);
    }


}
