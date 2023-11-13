package com.example.userservice.controller;

import com.example.userservice.dto.AddressResponseDto;
import com.example.userservice.dto.UserCreateRequest;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private RestTemplate restTemplate = new RestTemplate();
    private final UserService userService;

    @PostMapping
    public User create(@RequestBody UserCreateRequest userCreateRequest){
        return userService.create(userCreateRequest);
    }

    @GetMapping
    public UserResponse getUserAddress(@PathVariable Long userId){
        String url = String.format("http://localhost:8002/api/address/%s", userId);
        ResponseEntity<AddressResponseDto> address = restTemplate.getForEntity(url, AddressResponseDto.class);
        User user = userService.getUserById(address.getBody().getUserId());
        return UserResponse.getUserResponseWithAddress(user, address.getBody());
    }

}
