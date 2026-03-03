package com.engine.worker.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import common.constants.AppConstants;
import common.dto.NotificationDTO;

@Component
@Slf4j
public class NotificationListener {

    @RabbitListener(queues = AppConstants.QUEUE_NAME)
    public void consume(NotificationDTO notification) {

        log.info("[WORKER] Received → recipient={}", notification.recipient());

        log.info("[WORKER] Processing → type={}, recipient={}",
                notification.providerType(),
                notification.recipient());

        log.info("[WORKER] Completed → recipient={}", notification.recipient());
    }
}