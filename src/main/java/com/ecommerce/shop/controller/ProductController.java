package com.ecommerce.shop.controller;


import com.ecommerce.shop.dtos.ProductDto;
import com.ecommerce.shop.entity.Product;
import com.ecommerce.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController{
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@ModelAttribute ProductDto request){
        return ResponseEntity.ok(productService.createProduct(request));
    }
    @GetMapping("/read")
    public ResponseEntity<?> readAllProduct(){
        return ResponseEntity.ok(productService.getProduct());
    }
    @GetMapping("/singleProduct/{id}")
    public  ResponseEntity<?> getSingleProduct(@PathVariable Long id){
        Product product = productService.getSingleProduct(id);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted");
    }

}
