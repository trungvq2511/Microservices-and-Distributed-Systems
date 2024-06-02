package com.trungvq.clients.notification;

public record NotificationSendRequest(
        Integer toCustomerId,
        String toCustomerEmail,
        String message,
        String sender
) {
}
