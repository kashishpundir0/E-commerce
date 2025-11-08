package com.ecommerce.shop.service;

import com.ecommerce.shop.dtos.ProductDto;
import com.ecommerce.shop.entity.Product;
import com.ecommerce.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repo;

    public Object createProduct(ProductDto request){
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        return Map.of("data", repo.save(product));
    }
    public List<Product> getProduct(){
        return  repo.findAll();
    }
    public Product getSingleProduct(Long id){
        Product exist = repo.findById(id).orElseThrow(()-> new RuntimeException("Product is not available"));
        return exist;
    }
    public Object updateProduct(ProductDto request, Long id){
        Product exist = repo.findById(id).orElseThrow(()->new RuntimeException("Product is not available"));
        exist.setName(request.getName());
        exist.setDescription(request.getDescription());
        exist.setQuantity(request.getQuantity());
        exist.setPrice(request.getPrice());
        return Map.of("data", repo.save(exist));
    }
    public void deleteProduct(Long id){
        repo.deleteById(id);
    }




}
