package com.shoppingcart.gateway;

import com.shoppingcart.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductGateway {

    @Autowired
    private RestTemplate restTemplate;

    public Product getProduct(Long productId) {
        return restTemplate.getForObject("http://product-service:9093/products/"+productId, Product.class);
    }
}
