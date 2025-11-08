package com.ecommerce.shop.service;

import com.ecommerce.shop.dtos.CategoryDto;
import com.ecommerce.shop.entity.Category;
import com.ecommerce.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryService{
    private final CategoryRepository repo;

    public Object createCategory(CategoryDto request){
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return Map.of("data", repo.save(category));
    }
    public List<Category> getCategory(){
        return repo.findAll();
    }
    public Category getSingleCategory(Long id){
        Category exist = repo.findById(id).orElseThrow(()-> new RuntimeException("No category available"));
        return exist;
    }
    public Object updateCategory(CategoryDto request, Long id){
        Category exist = repo.findById(id).orElseThrow(()-> new RuntimeException("Category not available with id :"+ id));
        exist.setName(request.getName());
        exist.setDescription(request.getDescription());
        return Map.of("data", repo.save(exist));
    }
    public void deletCategory(Long id){
         repo.deleteById(id);
    }

}
