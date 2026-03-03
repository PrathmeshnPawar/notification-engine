package com.engine.producer.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import common.constants.AppConstants;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendNotification(Object payload) {
        rabbitTemplate.convertAndSend(
                AppConstants.EXCHANGE_NAME,
                AppConstants.ROUTING_KEY,
                payload
        );

        System.out.println("Sent → " + payload);
    }
}