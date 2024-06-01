package com.trungvq.notification.controller;

import com.trungvq.clients.notification.NotificationSendRequest;
import com.trungvq.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/notification")
public record NotificationController(
        NotificationService notificationService
) {
    @PostMapping
    public void sendNotification(@RequestBody NotificationSendRequest notificationSendRequest) {
        log.info("New notification... {}", notificationSendRequest);
        notificationService.sendNotification(notificationSendRequest);
    }
}
