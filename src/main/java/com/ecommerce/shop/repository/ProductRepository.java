package com.ecommerce.shop.repository;

import com.ecommerce.shop.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long>{

}