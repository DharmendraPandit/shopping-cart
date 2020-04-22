package com.shoppingcart.service;

import com.shoppingcart.gateway.ProductGateway;
import com.shoppingcart.model.Cart;
import com.shoppingcart.model.LineItem;
import com.shoppingcart.model.Product;
import com.shoppingcart.repository.CartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class CartServiceTest {

    @InjectMocks
    private CartService service;
    @Mock
    private CartRepository repository;
    @Mock
    private LineItemService lineItemService;
    @Mock
    private ProductGateway productGateway;
    private Cart cart;
    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cart = create();
    }

    @Test
    public void createSuccess() {
        when(repository.save(cart)).thenReturn(cart);
        Cart actualResult = service.create(cart);
        Assertions.assertEquals(actualResult.getId(), cart.getId());
    }

    @Test
    public void findByIdSuccess() {
        Optional<Cart> optionalCart = Optional.of(cart);
        when(repository.findById(cart.getId())).thenReturn(optionalCart);
        Cart actualResult = service.findById(cart.getId());
        Assertions.assertEquals(actualResult.getId(), cart.getId());
    }

    @Test
    public void findByIdNull() {
        Optional<Cart> optionalCart = Optional.empty();
        when(repository.findById(cart.getId())).thenReturn(optionalCart);
        Cart actualResult = service.findById(cart.getId());
        Assertions.assertNull(actualResult);
    }

    @Test
    public void addSuccess() {
        Optional<Cart> optionalCart = Optional.of(cart);
        long productId = 1L;
        Integer quantity = 2;
        Product product = product();
        LineItem lineItem = createLineItem();
        when(repository.findById(cart.getId())).thenReturn(optionalCart);
        when(productGateway.getProduct(productId)).thenReturn(product);
        when(lineItemService.create(lineItem)).thenReturn(lineItem);
        service.add(cart.getId(), product.getId(), quantity);
    }


    private Cart create() {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setCustomerId(1L);
        cart.setSubtotal(BigDecimal.valueOf(10000));
        return cart;
    }

    private Product product() {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(10000));
        product.setDescription("Mac Pro");
        product.setCategoryId(1L);
        return product;
    }

    private LineItem createLineItem() {
        LineItem lineItem = new LineItem();
        lineItem.setId(1L);
        lineItem.setOrderId(1L);
        lineItem.setPrice(BigDecimal.valueOf(10000));
        lineItem.setQuantity(2);
        lineItem.setProductId(1L);
        lineItem.setCartId(1L);
        return lineItem;
    }
}
