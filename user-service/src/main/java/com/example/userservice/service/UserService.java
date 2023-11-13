package com.example.userservice.service;

import com.example.userservice.config.kafka.producer.KafkaProducer;
import com.example.userservice.config.kafka.properties.UserCreatedTopicProperties;
import com.example.userservice.dto.AddressResponseDto;
import com.example.userservice.dto.UserCreateRequest;
import com.example.userservice.dto.UserCreatedPayload;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.springframework.kafka.support.KafkaHeaders.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final KafkaProducer kafkaProducer;

    private final UserCreatedTopicProperties userCreatedTopicProperties;

    private final RestTemplate restTemplate = new RestTemplate();


    public User create(UserCreateRequest userCreateRequest){
        User user = User.getUser(userCreateRequest);
        User savedUser = userRepository.save(user);

        UserCreatedPayload payload = UserCreatedPayload.getUserCreatedPayload(savedUser, userCreateRequest.getAddressText());

        Map<String, Object> headers = new HashMap<>();
        headers.put(TOPIC, userCreatedTopicProperties.getTopicName());
        headers.put(KEY, savedUser.getId().toString());
        kafkaProducer.sendMessage(new GenericMessage(payload,headers));
        return savedUser;
    }

    public UserResponse getUserById(Long id){
        String url = String.format("http://localhost:8081/api/address/%s", id);
        ResponseEntity<AddressResponseDto> address = restTemplate.getForEntity(url, AddressResponseDto.class);
            User user = userRepository.findById(Objects.requireNonNull(address.getBody()).getUserId()).orElseThrow();
        return UserResponse.getUserResponseWithAddress(user, address.getBody());

    }
}
