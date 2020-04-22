package com.shoppingcart.service;

import com.shoppingcart.model.Category;
import com.shoppingcart.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category create(Category category) {
        return repository.save(category);
    }

    public List<Category> getAll() {
        List<Category> categoryList = new ArrayList<>();
        repository.findAll().forEach(e -> categoryList.add(e));
        return categoryList;
    }

    public Optional<Category> getById(long id) {
        return repository.findById(id);
    }
}
