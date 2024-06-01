package com.trungvq.customer.service;

import com.trungvq.customer.model.Customer;
import com.trungvq.customer.repository.CustomerRepository;
import com.trungvq.customer.request.CustomerRegistrationRequest;
import com.trungvq.customer.response.FraudCheckResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        RestTemplate restTemplate
) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // validate

        // save to DB
        customerRepository.saveAndFlush(customer);
        // check is fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Customer is fraudster!");
        }
        // send notification
    }
}
