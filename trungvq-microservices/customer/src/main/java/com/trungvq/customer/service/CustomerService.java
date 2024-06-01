package com.trungvq.customer.service;

import com.trungvq.customer.model.Customer;
import com.trungvq.customer.repository.CustomerRepository;
import com.trungvq.customer.request.CustomerRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // validate

        // save to DB
        customerRepository.save(customer);
    }
}
