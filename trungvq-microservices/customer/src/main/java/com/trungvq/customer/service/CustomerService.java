package com.trungvq.customer.service;

import com.trungvq.customer.model.Customer;
import com.trungvq.customer.request.CustomerRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService() {
    public void registerCustomer(CustomerRegistrationRequest request) {
        String
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // validate

        // save to DB
    }
}
