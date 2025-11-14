package com.ecommerce.shop.service;

import com.ecommerce.shop.dtos.OrderDto;
import com.ecommerce.shop.entity.Order;
import com.ecommerce.shop.entity.Product;
import com.ecommerce.shop.entity.User;
import com.ecommerce.shop.repository.CartRepository;
import com.ecommerce.shop.repository.OrderRepository;
import com.ecommerce.shop.repository.ProductRepository;
import com.ecommerce.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Object placeOrder(OrderDto request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new RuntimeException("User not found"));

        Product product = productRepository.findById(request.getProductId()).orElseThrow(()-> new RuntimeException("Product not found"));

        if(product.getQuantity()<1){
            throw new RuntimeException("Product out of stock");
        }

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setOrderDate(new Date());
        order.setQuantity(request.getQuantity());
        order.setTotalAmount(product.getPrice() * request.getQuantity());
        product.setQuantity(product.getQuantity() - request.getQuantity());

        productRepository.save(product);
         Order saveOrder = orderRepository.save(order);
         return Map.of("message", "order place successfully", "data", saveOrder);

    }

    public List<Order> viewOrder(){
        return orderRepository.findAll();
    }

    public void cancelOrder(Long id){
        orderRepository.deleteById(id);
    }


}
