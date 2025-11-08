package com.ecommerce.shop.controller;

import com.ecommerce.shop.dtos.CategoryDto;
import com.ecommerce.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController{
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@ModelAttribute CategoryDto request){
        return ResponseEntity.ok(categoryService.createCategory(request));
    }
    @GetMapping("/all")
    public ResponseEntity<?>getAllCategory(){
        return ResponseEntity.ok(categoryService.getCategory());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleCategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getSingleCategory(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@ModelAttribute CategoryDto request, @PathVariable Long id){
        return ResponseEntity.ok(categoryService.updateCategory(request, id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        categoryService.deletCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
