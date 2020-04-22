package com.shoppingcart.service;

import com.shoppingcart.model.Customer;
import com.shoppingcart.repository.CustomerRepository;
import com.shoppingcart.util.ShaHashing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.NoSuchAlgorithmException;

import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService service;
    @Mock
    private CustomerRepository repository;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customer = create();
    }

    @Test
    public void createSuccess() throws NoSuchAlgorithmException {
        Customer expectedResult = create();
        expectedResult.setPassword(ShaHashing.encrypted(customer.getPassword()));
        when(repository.save(customer)).thenReturn(expectedResult);
        Long actualResult = service.create(customer);
        Assertions.assertEquals(actualResult, expectedResult.getId());
    }


    private Customer create() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Alex");
        customer.setLastName("Jhony");
        customer.setPassword("123456");
        customer.setUsername("testing");
        return customer;

    }
}
