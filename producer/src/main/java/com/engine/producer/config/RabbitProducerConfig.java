package com.engine.producer.config;


import com.engine.producer.messaging.NotificationProducer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.dto.NotificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Configuration
public class RabbitProducerConfig {
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    CommandLineRunner test(NotificationProducer producer) {
        return args -> {
            try {
                ObjectMapper mapper = new ObjectMapper();

                File jsonFile = Paths.get("../post_data.json").toFile();

                if (jsonFile.exists()) {
                    List<NotificationDTO> notification = mapper.readValue(jsonFile, new TypeReference<List<NotificationDTO>>() {
                    });

                    producer.sendNotification(notification);
                    System.out.println("Startup test: Sent " + notification.size() + " notifications from JSON.");
                } else {
                    System.out.println("post_data.json not found, skipping startup test.");
                }
            } catch (Exception e) {
                System.err.println("Failed to load startup data: " + e.getMessage());
            }

        };
    }
}