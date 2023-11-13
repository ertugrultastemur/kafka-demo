package com.example.notificationconsumer.entity;

import com.example.notificationconsumer.consumers.model.UserCreatedEvent;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.lang.annotation.Documented;

@Getter
@Setter
@Document
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Field
    private Long userId;

    @Field
    private String email;

    @Field
    private Boolean isSend;

    public Notification(Long userId, String email, Boolean isSend) {
        this.userId = userId;
        this.email = email;
        this.isSend = isSend;
    }

    public static Notification eventToNotificationEntity(UserCreatedEvent userCreatedEvent){
        return new Notification(
                userCreatedEvent.getId(),
                userCreatedEvent.getEmail(),
                Boolean.TRUE
        );
    }
}
