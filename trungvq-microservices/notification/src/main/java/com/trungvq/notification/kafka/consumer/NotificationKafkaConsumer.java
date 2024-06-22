package com.trungvq.notification.kafka.consumer;

import com.trungvq.clients.notification.NotificationSendRequest;
import com.trungvq.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationKafkaConsumer {

    private final NotificationService notificationService;
    private final String notificationTopicName = "notification-topic";
    private final String notificationConsumerGroupId = "notification-group";
    private final String containerFactory = "messageFactory";

    public NotificationKafkaConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(
            topics = notificationTopicName,
            groupId = notificationConsumerGroupId,
            containerFactory = containerFactory
    )
    public void consumeNotificationMessage(NotificationSendRequest notificationSendRequest) {
        log.info("Consumed {} from queue", notificationSendRequest);
        notificationService.sendNotification(notificationSendRequest);
    }
}
