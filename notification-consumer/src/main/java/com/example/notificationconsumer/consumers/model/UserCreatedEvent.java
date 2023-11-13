package com.example.notificationconsumer.consumers.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreatedEvent {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean isDeleted;
}
