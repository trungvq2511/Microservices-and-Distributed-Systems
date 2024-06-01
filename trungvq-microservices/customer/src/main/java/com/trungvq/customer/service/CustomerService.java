package com.trungvq.customer.service;

import com.trungvq.clients.fraud.FraudClient;
import com.trungvq.clients.fraud.FraudCheckResponse;
import com.trungvq.clients.notification.NotificationClient;
import com.trungvq.clients.notification.NotificationSendRequest;
import com.trungvq.customer.model.Customer;
import com.trungvq.customer.repository.CustomerRepository;
import com.trungvq.customer.request.CustomerRegistrationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        RestTemplate restTemplate,
        FraudClient fraudClient,
        NotificationClient notificationClient
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
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Customer is fraudster!");
        }

        // send notification
        NotificationSendRequest notificationSendRequest = new NotificationSendRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, Welcome to Microservices...", customer.getFirstName()),
                "TrungVQ8");
        notificationClient.sendNotification(notificationSendRequest);
    }
}
