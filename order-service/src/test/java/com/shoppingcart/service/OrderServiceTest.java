package com.shoppingcart.service;

import com.shoppingcart.model.Cart;
import com.shoppingcart.model.LineItem;
import com.shoppingcart.model.Order;
import com.shoppingcart.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @InjectMocks
    private OrderService service;
    @Mock
    private OrderRepository repository;
    @Mock
    private LineItemService lineItemService;
    @Mock
    private CartService cartService;
    private Cart cart;
    private LineItem lineItem;
    private Order order;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        lineItem = createLineItem();
        cart = createCart();
        order = createOrder();
    }

    @Test
    public void orderSuccess() {
        List<LineItem> lineItemList = new ArrayList<>();
        lineItemList.add(lineItem);
        Order order2 = createOrder();
        when(cartService.findById(cart.getId())).thenReturn(cart);
        when(lineItemService.findByCartId(cart.getId())).thenReturn(lineItemList);
        when(repository.save(any(Order.class))).thenReturn(order2);
        Long actualResult = service.order(cart.getId());
        Assertions.assertEquals(actualResult, order.getId());
    }


    private Cart createCart() {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setCustomerId(1L);
        cart.setSubtotal(BigDecimal.valueOf(10000));
        return cart;
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

    private Order createOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setTotal(BigDecimal.valueOf(10000));
        order.setStatus("Ordered");
        order.setOrdered(new Date());
        order.setCustomerId(1L);
        return order;
    }
}
