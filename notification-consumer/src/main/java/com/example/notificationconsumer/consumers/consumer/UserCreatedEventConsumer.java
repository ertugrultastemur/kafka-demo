package com.example.notificationconsumer.consumers.consumer;

import com.example.notificationconsumer.consumers.model.UserCreatedEvent;
import com.example.notificationconsumer.entity.Notification;
import com.example.notificationconsumer.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserCreatedEventConsumer {

    private final NotificationService notificationService;

    public void consumeCreatedUserEvent(@Payload UserCreatedEvent eventData, @Headers ConsumerRecord<String,Object> consumerRecord){
        log.info("UserCreatedEventConsumer.consumeApprovalRequestResultedEvent consumed Event:{}" + "from partition : {} " + "with offset : {} " + "thread : {} " + "for message key: {}", eventData,consumerRecord.partition(),consumerRecord.offset(), Thread.currentThread().getName(), consumerRecord.key());
        Notification entity = Notification.eventToNotificationEntity(eventData);
        notificationService.save(entity);
    }
}
