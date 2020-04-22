package com.shoppingcart.repository;

import com.shoppingcart.model.LineItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineItemRepository extends CrudRepository<LineItem, Long> {
    public List<LineItem> findByCartId(Long cartId);
}
