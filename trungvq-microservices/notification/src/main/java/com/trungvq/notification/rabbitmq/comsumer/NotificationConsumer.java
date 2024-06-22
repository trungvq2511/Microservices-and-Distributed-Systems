//package com.trungvq.notification.rabbitmq.comsumer;
//
//import com.trungvq.clients.notification.NotificationSendRequest;
//import com.trungvq.notification.service.NotificationService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//@Slf4j
//public class NotificationConsumer {
//
//    private final NotificationService notificationService;
//
//    @RabbitListener(queues = "${rabbitmq.queues.notification}")
//    public void consumer(NotificationSendRequest notificationRequest) {
//        log.info("Consumed {} from queue", notificationRequest);
//        notificationService.sendNotification(notificationRequest);
//    }
//}
