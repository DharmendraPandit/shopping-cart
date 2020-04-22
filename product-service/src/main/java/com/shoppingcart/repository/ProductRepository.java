package com.shoppingcart.repository;

import com.shoppingcart.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByDescription(String description);
    List<Product> findByCategoryId(Long categoryId);
}
