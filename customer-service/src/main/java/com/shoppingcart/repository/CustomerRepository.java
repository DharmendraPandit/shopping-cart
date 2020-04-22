package com.shoppingcart.repository;

import com.shoppingcart.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	public Customer findByUsername(String username);
}
