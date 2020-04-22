package com.shoppingcart.controller;

import com.shoppingcart.model.Category;
import com.shoppingcart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping
    private ResponseEntity<Category> create(@RequestBody Category category) {
        return new ResponseEntity<>(service.create(category), HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<List<Category>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<Category>> getById(@PathVariable long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }
}
