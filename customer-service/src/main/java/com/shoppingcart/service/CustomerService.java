package com.shoppingcart.service;

import java.security.NoSuchAlgorithmException;

import com.shoppingcart.exception.AuthenticationFailedException;
import com.shoppingcart.model.Customer;
import com.shoppingcart.repository.CustomerRepository;
import com.shoppingcart.util.ShaHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;

	public Customer authenticate(String username, String password) throws AuthenticationFailedException, NoSuchAlgorithmException {
		Customer customer = repository.findByUsername(username);
		if (customer.getPassword().equals(ShaHashing.encrypted(password))) {
			return customer;
		} else {
			throw new AuthenticationFailedException();
		}
	}
	
	public Long create(Customer customer) throws NoSuchAlgorithmException {
		customer.setPassword(ShaHashing.encrypted(customer.getPassword()));
		return repository.save(customer).getId();
	}
	
}
