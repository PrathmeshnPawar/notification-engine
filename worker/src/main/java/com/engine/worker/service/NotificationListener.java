package com.engine.worker.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import common.constants.AppConstants;
import common.dto.NotificationDTO;

@Component
public class NotificationListener {

    @RabbitListener(queues = AppConstants.QUEUE_NAME)
    public void consume(NotificationDTO message) {
        System.out.println("Received → " + message);
    }
}