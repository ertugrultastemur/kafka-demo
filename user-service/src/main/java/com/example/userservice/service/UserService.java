package com.example.userservice.service;

import com.example.userservice.config.kafka.producer.KafkaProducer;
import com.example.userservice.config.kafka.properties.UserCreatedTopicProperties;
import com.example.userservice.dto.UserCreateRequest;
import com.example.userservice.dto.UserCreatedPayload;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.internals.Topic;
import org.springframework.kafka.listener.GenericMessageListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final KafkaProducer kafkaProducer;

    private final UserCreatedTopicProperties userCreatedTopicProperties;

    public User create(UserCreateRequest userCreateRequest){
        User user = User.getUser(userCreateRequest);
        User savedUser = userRepository.save(user);

        UserCreatedPayload payload = UserCreatedPayload.getUserCreatedPayload(savedUser, userCreateRequest.getAddressText());

        Map<String, Object> headers = new HashMap<>();
        headers.put("TOPIC", userCreatedTopicProperties.getTopicName());
        headers.put("MESSAGE_KEY", savedUser.getId().toString());

        kafkaProducer.sendMessage(new GenericMessage<>(payload.toString(),headers));
        return savedUser;
    }

    public User getUserById(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()){
            return null;
        }
        return userOptional.get();

    }
}
