package com.ecommerce.shop.service;

import com.ecommerce.shop.dtos.OrderDto;
import com.ecommerce.shop.entity.Order;
import com.ecommerce.shop.entity.Products;
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

        Products products = productRepository.findById(request.getProductId()).orElseThrow(()-> new RuntimeException("Products not found"));

        if(products.getQuantity()<1){
            throw new RuntimeException("Products out of stock");
        }

        Order order = new Order();
        order.setUser(user);
        order.setProducts(products);
        order.setOrderDate(new Date());
        order.setQuantity(request.getQuantity());
        order.setTotalAmount(request.getTotalAmount());
        products.setQuantity(products.getQuantity() - request.getQuantity());

        productRepository.save(products);
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
