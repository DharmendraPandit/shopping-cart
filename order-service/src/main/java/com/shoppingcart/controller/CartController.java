package com.shoppingcart.controller;

import com.shoppingcart.model.Cart;
import com.shoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService service;

    @PostMapping
    private ResponseEntity<Cart> create(@RequestBody Cart cart) {
        return new ResponseEntity<>(service.create(cart), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/{productId}/{quantity}")
    private ResponseEntity<Void> add(@PathVariable("id") Long id,
                                     @PathVariable("productId") Long productId, @PathVariable("quantity") Integer quantity) {
        service.add(id, productId, quantity);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
