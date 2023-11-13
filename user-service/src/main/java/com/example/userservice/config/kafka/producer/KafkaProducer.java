package com.example.userservice.config.kafka.producer;

import com.couchbase.client.core.deps.com.google.common.util.concurrent.ListenableFuture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(GenericMessage<String> message){
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(message);
            future.whenComplete((result, throwable) -> {
                if (throwable != null) {
                    log.error("Producing request failed: {}", throwable.getMessage());
                } else {
                    if (Objects.isNull(result)) {
                        log.info("Empty result on success for message {}", message);
                    } else {
                        log.info("Message:{} published, topic : {}, partition: {} and offset: {}", message.getPayload(),
                                result.getRecordMetadata().topic(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    }

                }
            });
    }
}
