package com.trungvq.customer.kafka.producer;

import com.trungvq.clients.notification.NotificationSendRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public record ConsumerKafkaProducer(
        KafkaTemplate<String, NotificationSendRequest> kafkaTemplate
) {

    public void produceNotificationMessage(String notificationTopicName, NotificationSendRequest notificationSendRequest) {
        // send data
        kafkaTemplate.send(notificationTopicName, notificationSendRequest);
    }
}
