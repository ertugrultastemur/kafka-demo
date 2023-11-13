package com.example.userservice.config.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
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

        CompletableFuture<SendResult<String,Object>> future = kafkaTemplate.send(message);
        future.whenComplete(new BiConsumer<SendResult<String, Object>, Throwable>() {
            @Override
            public void accept(SendResult<String, Object> result, Throwable throwable) {
                if (throwable != null){
                    log.error("Producing request failed: {}", throwable.getMessage());
                }else {
                    if (Objects.isNull(result)){
                        log.info("Empty result on success for message {}", message);
                    }else {
                        log.info("Message:{} published, topic : {}, partition: {} and offset: {}", message.getPayload(),
                                result.getRecordMetadata().topic(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    }

                }
            }
        });

    }
}
