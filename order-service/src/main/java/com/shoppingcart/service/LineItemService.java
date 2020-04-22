package com.shoppingcart.service;

import com.shoppingcart.model.LineItem;
import com.shoppingcart.repository.LineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineItemService {

    @Autowired
    private LineItemRepository repository;

    public LineItem create(LineItem lineItem) {
        return repository.save(lineItem);
    }

    public List<LineItem> findByCartId(Long cartId) {
        return repository.findByCartId(cartId);
    }

    public void saveAll(List<LineItem> lineItemList) {
        repository.saveAll(lineItemList);
    }
}
