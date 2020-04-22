package com.shoppingcart.gateway;

import com.shoppingcart.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CategoryGateway {

    @Autowired
    private RestTemplate restTemplate;

    public Category getCategory(Long id) {
        Category category = restTemplate.getForObject("http://category-service:9092/categories/"+id, Category.class);
        return category;
    }

}
