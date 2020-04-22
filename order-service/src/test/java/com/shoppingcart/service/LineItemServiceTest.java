package com.shoppingcart.service;

import com.shoppingcart.model.LineItem;
import com.shoppingcart.repository.LineItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class LineItemServiceTest {

    @InjectMocks
    private LineItemService service;
    @Mock
    private LineItemRepository repository;
    private LineItem lineItem;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        lineItem = create();
    }

    @Test
    public void createSuccess() {
        when(repository.save(lineItem)).thenReturn(lineItem);
        LineItem actualResult = service.create(lineItem);
        Assertions.assertEquals(actualResult.getId(), lineItem.getId());
    }

    @Test
    public void findByCartIdSuccess() {
        List<LineItem> lineItemList = new ArrayList<>();
        lineItemList.add(lineItem);
        when(repository.findByCartId(lineItem.getCartId())).thenReturn(lineItemList);
        List<LineItem> actualResult = service.findByCartId(lineItem.getCartId());
        Assertions.assertEquals(actualResult.size(), lineItemList.size());
    }

    @Test
    public void saveAllSuccess() {
        List<LineItem> lineItemList = new ArrayList<>();
        lineItemList.add(lineItem);
        when(repository.saveAll(lineItemList)).thenReturn(lineItemList);
        service.saveAll(lineItemList);
    }

    private LineItem create() {
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
