package com.example.pet_shop.repository.product;

import com.example.pet_shop.model.product.ProductCategory;
import com.example.pet_shop.model.product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductTypeRepo extends JpaRepository<ProductType, Long> {
    //


}
