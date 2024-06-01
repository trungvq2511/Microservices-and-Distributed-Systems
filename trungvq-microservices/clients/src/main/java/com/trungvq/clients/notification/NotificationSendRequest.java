package com.trungvq.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public record NotificationSendRequest(
        Integer toCustomerId,
        String toCustomerEmail,
        String message,
        String sender
) {
}
