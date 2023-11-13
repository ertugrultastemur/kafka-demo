package com.example.userservice.dto;

import com.example.userservice.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserCreatedPayload {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String addressText;
    private Date createdAt;
    private Date updatedAt;
    private Boolean isDeleted;


    public UserCreatedPayload(Long id, String firstName, String lastName, String email, String addressText, Date createdAt, Date updatedAt, Boolean isDeleted) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressText = addressText;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public static UserCreatedPayload getUserCreatedPayload(User user, String addressText){
        return new UserCreatedPayload(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                addressText,
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getIsDeleted()
        );
    }
}
