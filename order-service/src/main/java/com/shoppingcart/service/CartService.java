package com.shoppingcart.service;

import com.shoppingcart.gateway.ProductGateway;
import com.shoppingcart.model.Cart;
import com.shoppingcart.model.LineItem;
import com.shoppingcart.model.Product;
import com.shoppingcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository repository;

    @Autowired
    private LineItemService lineItemService;

    @Autowired
    private ProductGateway gateway;

    public Cart create(Cart cart) {
        return repository.save(cart);
    }

    public Cart findById(Long id) {
        Optional<Cart> cart = repository.findById(id);
        if(cart.isPresent()) {
            return cart.get();
        }
        return null;
    }

    public void add(Long id, Long productId, Integer quantity) {
        Optional<Cart> cart = repository.findById(id);
        if(cart.isPresent()) {
            Product product = gateway.getProduct(productId);
            if (product != null) {
                LineItem lineItem = new LineItem();
                lineItem.setCartId(cart.get().getId());
                lineItem.setProductId(productId);
                lineItem.setQuantity(quantity);
                lineItem.setPrice(BigDecimal.valueOf(quantity).multiply(product.getPrice()));
                lineItemService.create(lineItem);
            }
        }
    }
}
