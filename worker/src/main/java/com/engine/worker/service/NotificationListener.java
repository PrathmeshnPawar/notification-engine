package com.engine.worker.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import common.constants.AppConstants;
import common.dto.NotificationDTO;

import java.util.List;

@Component
public class NotificationListener {

    @RabbitListener(queues = AppConstants.QUEUE_NAME)
    public void consume(Object message) {
        System.out.println("Received → " + message);
        System.out.println("Received Single → " + message);
    }
}
