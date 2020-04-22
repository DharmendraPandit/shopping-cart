package com.shoppingcart.service;


import com.shoppingcart.exception.ProductNotFoundException;
import com.shoppingcart.gateway.CategoryGateway;
import com.shoppingcart.model.Category;
import com.shoppingcart.model.Product;
import com.shoppingcart.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class ProductServiceTest {
    @InjectMocks
    private ProductService service;
    @Mock
    private CategoryGateway gateway;
    @Mock
    private ProductRepository repository;
    private Product product;
    private Category category;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        product = create();
        category = createCategory();
    }

    @Test
    public void createSuccess() throws ProductNotFoundException {
        when(gateway.getCategory(product.getCategoryId())).thenReturn(category);
        when(repository.save(product)).thenReturn(product);
        Product actualResult = service.create(product);
        Assertions.assertEquals(actualResult.getId(), product.getId());
    }

    @Test
    public void createFailed() {
        when(gateway.getCategory(product.getCategoryId())).thenReturn(null);
        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            service.create(product);
        });
    }

    @Test
    public void findBySuccess() throws ProductNotFoundException {
        Optional<Product> optionalProduct = Optional.of(product);
        when(repository.findById(product.getId())).thenReturn(optionalProduct);
        Product actualResult = service.findBy(product.getId());
        Assertions.assertEquals(actualResult.getId(), product.getId());
    }

    @Test
    public void findByFailed() {
        Optional<Product> optionalProduct = Optional.empty();
        when(repository.findById(product.getId())).thenReturn(optionalProduct);
        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            service.findBy(product.getId());
        });
    }

    @Test
    public void findByDescriptionSuccess() throws ProductNotFoundException {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(repository.findByDescription(product.getDescription())).thenReturn(productList);
        List<Product> actualResult = service.findByDescription(product.getDescription());
        Assertions.assertEquals(actualResult.size(), productList.size());
    }

    @Test
    public void findByDescriptionFailed() {
        List<Product> productList = new ArrayList<>();
        when(repository.findByDescription(product.getDescription())).thenReturn(productList);
        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            service.findByDescription(product.getDescription());
        });
    }

    @Test
    public void findByCategorySuccess() throws ProductNotFoundException {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(repository.findByCategoryId(product.getCategoryId())).thenReturn(productList);
        List<Product> actualResult = service.findByCategory(product.getCategoryId());
        Assertions.assertEquals(actualResult.size(), productList.size());
    }

    @Test
    public void findByCategoryFailed() {
        List<Product> productList = new ArrayList<>();
        when(repository.findByCategoryId(product.getCategoryId())).thenReturn(productList);
        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            service.findByCategory(product.getCategoryId());
        });
    }

    @Test
    public void findAllSuccess() throws ProductNotFoundException {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(repository.findAll()).thenReturn(productList);
        List<Product> actualResult = service.findAll();
        Assertions.assertEquals(actualResult.size(), productList.size());
    }

    @Test
    public void findAllFailed() {
        List<Product> productList = new ArrayList<>();
        when(repository.findAll()).thenReturn(productList);
        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            service.findAll();
        });
    }


    private Product create() {
        Product product = new Product();
        product.setId(1L);
        product.setCategoryId(1L);
        product.setDescription("Mac Pro");
        product.setPrice(BigDecimal.valueOf(50000));
        return product;
    }

    private Category createCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setDescription("Cosmatic");
        return category;
    }
}
