package com.demo.engine;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
    @KafkaListener(topics = "course", groupId = "group_id")
    public void consumeCourse(ConsumerRecord<?, ?> consumerRecord) throws IOException {
        logger.info(String.format("#### -> Course Consumed message -> %s", consumerRecord.toString()));
    }
}
