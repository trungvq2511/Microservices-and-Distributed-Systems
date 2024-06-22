package com.trungvq.customer.kafka.producer;

import com.trungvq.clients.notification.NotificationSendRequest;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public record ConsumerKafkaProducer(
        KafkaTemplate<String, NotificationSendRequest> kafkaTemplate
) {

    public void produceNotificationMessage(String notificationTopic, NotificationSendRequest notificationSendRequest) {
        // create a Producer Record
        ProducerRecord<String, NotificationSendRequest> notificationRecord =
                new ProducerRecord<>(notificationTopic, notificationSendRequest);

        // send data
        kafkaTemplate.send(notificationTopic, notificationSendRequest);
    }
}
