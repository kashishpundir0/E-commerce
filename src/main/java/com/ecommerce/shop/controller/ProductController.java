package com.ecommerce.shop.controller;


import com.ecommerce.shop.dtos.ProductDto;
import com.ecommerce.shop.entity.Products;
import com.ecommerce.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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
        Products products = productService.getSingleProduct(id);
        return ResponseEntity.ok(products);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>  updateProduct(@PathVariable Long id, @ModelAttribute ProductDto request){
        return ResponseEntity.ok(productService.updateProduct(request, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Products deleted");
    }

}
