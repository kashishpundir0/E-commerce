package com.ecommerce.shop.service;

import com.ecommerce.shop.dtos.ProductDto;
import com.ecommerce.shop.entity.Category;
import com.ecommerce.shop.entity.Products;
import com.ecommerce.shop.repository.CategoryRepository;
import com.ecommerce.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repo;
    private final CategoryRepository categoryRepo;

    public Object createProduct(ProductDto request){
        Products products = new Products();
        products.setName(request.getName());
        products.setDescription(request.getDescription());
        products.setPrice(request.getPrice());
        products.setQuantity(request.getQuantity());

        Category category = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id " + request.getCategoryId()));
        products.setCategory(category);

        return Map.of("data", repo.save(products));
    }
    public List<Products> getProduct(){
        return  repo.findAll();
    }
    public Products getSingleProduct(Long id){
        Products exist = repo.findById(id).orElseThrow(()-> new RuntimeException("Products is not available"));
        return exist;
    }
    public Object updateProduct(ProductDto request, Long id){
        Products exist = repo.findById(id).orElseThrow(()->new RuntimeException("Products is not available"));
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
