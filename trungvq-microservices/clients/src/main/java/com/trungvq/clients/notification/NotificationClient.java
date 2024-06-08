package com.trungvq.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "notification",
        url = "${clients.fraud.url}/api/v1/fraud-check")
public interface NotificationClient {

    @PostMapping
    public void sendNotification(@RequestBody NotificationSendRequest notificationSendRequest);
}
