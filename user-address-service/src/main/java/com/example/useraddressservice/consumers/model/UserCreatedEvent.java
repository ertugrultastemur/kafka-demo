package com.example.useraddressservice.consumers.model;

import com.example.useraddressservice.entity.Address;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent {
    private Long id;
    private String addressText;

    public static Address getAddressEntityFromEvent(UserCreatedEvent event){
        return new Address(
                event.getId(),
                event.getAddressText()
        );
    }
}
