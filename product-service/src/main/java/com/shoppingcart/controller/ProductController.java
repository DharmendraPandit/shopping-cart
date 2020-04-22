package com.shoppingcart.controller;

import com.shoppingcart.exception.ProductNotFoundException;
import com.shoppingcart.model.Product;
import com.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    private ResponseEntity<Product> create(@RequestBody Product product) throws ProductNotFoundException {
        return new ResponseEntity<>(service.create(product), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Product> findById(@PathVariable("id") long id) throws ProductNotFoundException {
        return new ResponseEntity<>(service.findBy(id), HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<Product>> findByDescription(@RequestParam("description") String description) throws ProductNotFoundException {
        return new ResponseEntity<>(service.findByDescription(description), HttpStatus.OK);
    }

    @GetMapping("/cat")
    private ResponseEntity<List<Product>> findByCategory(@RequestParam("category") long category) throws ProductNotFoundException {
        return new ResponseEntity<>(service.findByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/all")
    private ResponseEntity<List<Product>> findAll() throws ProductNotFoundException {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
