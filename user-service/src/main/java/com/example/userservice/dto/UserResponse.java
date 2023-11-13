package com.example.userservice.dto;

import com.example.userservice.model.User;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
@Builder
public class UserResponse extends BaseResponseDto{
    private String firstName;
    private String lastName;
    private String email;
    private AddressResponseDto address;

    public static UserResponse getUserResponseWithAddress(User user, AddressResponseDto address){
        return new UserResponse(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                address
        );
    }
}
