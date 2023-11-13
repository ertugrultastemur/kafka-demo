package com.example.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AddressResponseDto {
    private Long id;
    private Long userId;
    private String addressText;
    private Date createdAt;
    private Date updatedAt;
}
