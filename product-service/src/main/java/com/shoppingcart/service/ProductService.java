package com.shoppingcart.service;

import com.shoppingcart.exception.ProductNotFoundException;
import com.shoppingcart.gateway.CategoryGateway;
import com.shoppingcart.model.Category;
import com.shoppingcart.model.Product;
import com.shoppingcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryGateway gateway;

    public Product create(Product product) throws ProductNotFoundException{
        Category category = gateway.getCategory(product.getCategoryId());
        if (category != null) {
            return repository.save(product);
        } else {
            throw new ProductNotFoundException();
        }
    }

    public Product findBy(Long id) throws ProductNotFoundException {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent())
            return product.get();
        else
            throw new ProductNotFoundException();
    }

    public List<Product> findByDescription(String description) throws ProductNotFoundException {
        List<Product> productList = repository.findByDescription(description);
        if (productList != null && !productList.isEmpty())
            return productList;
        else
            throw new ProductNotFoundException();
    }

    public List<Product> findByCategory(Long category) throws ProductNotFoundException {
        List<Product> products = repository.findByCategoryId(category);
        if (products.isEmpty() || products == null)
            throw new ProductNotFoundException();
        else
            return products;
    }

    public List<Product> findAll() throws ProductNotFoundException {
        List<Product> productList = new ArrayList<>();
        repository.findAll().forEach(product -> productList.add(product));
        if (!productList.isEmpty())
            return productList;
        else
            throw new ProductNotFoundException();
    }
}
