package com.trungvq.fraud.request;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {}
