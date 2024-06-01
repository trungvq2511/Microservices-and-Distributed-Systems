package com.trungvq.notification.service;

import com.trungvq.notification.model.Notification;
import com.trungvq.notification.repository.NotificationRepository;
import com.trungvq.clients.notification.NotificationSendRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository notificationRepository) {
    public void sendNotification(NotificationSendRequest notificationSendRequest) {
        Notification notification = Notification.builder()
                .toCustomerId(notificationSendRequest.toCustomerId())
                .toCustomerEmail(notificationSendRequest.toCustomerEmail())
                .message(notificationSendRequest.message())
                .sender(notificationSendRequest.sender())
                .sendAt(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);
    }
}
