package com.shoppingcart.service;

import com.shoppingcart.model.Cart;
import com.shoppingcart.model.LineItem;
import com.shoppingcart.model.Order;
import com.shoppingcart.repository.OrderRepository;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private LineItemService lineItemService;

    @Autowired
    private CartService cartService;

    public Long order(Long cartId) {
        Cart cart = cartService.findById(cartId);
        if(cart != null) {
            List<LineItem> itemList = lineItemService.findByCartId(cartId);
            if(!itemList.isEmpty()) {
                Order order = new Order();
                BigDecimal totalPrice = itemList.stream()
                        .map(x -> x.getPrice())
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                order.setTotal(totalPrice);
                order.setCustomerId(cart.getCustomerId());
                order.setOrdered(new Date());
                order.setStatus("Ordered");
                Order result = repository.save(order);
                if(result != null && result.getId() != null) {
                    itemList.forEach(item -> item.setOrderId(order.getId()));
                    lineItemService.saveAll(itemList);
                }
                return result.getId();
            }
        }
        return null;
    }

    private Order create(Order order) {
        return repository.save(order);
    }

}
