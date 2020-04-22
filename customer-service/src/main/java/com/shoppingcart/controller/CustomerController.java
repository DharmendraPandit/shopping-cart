package com.shoppingcart.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import com.shoppingcart.exception.AuthenticationFailedException;
import com.shoppingcart.model.Customer;
import com.shoppingcart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping("/login")
	private ResponseEntity<Customer> login(@RequestParam("username") String username,
										   @RequestParam("password") String password) throws NoSuchAlgorithmException, AuthenticationFailedException {
		return new ResponseEntity<>(service.authenticate(username, password), HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<Void> addCustomer(@RequestBody Customer customer, HttpServletRequest request) throws NoSuchAlgorithmException, URISyntaxException {
		Long id = service.create(customer);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI(request.getRequestURL() + "/" + id.toString()));
		return new ResponseEntity<>(header, HttpStatus.CREATED);
	}

}
