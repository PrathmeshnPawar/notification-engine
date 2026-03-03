package com.engine.producer.controller;

import com.engine.producer.messaging.NotificationProducer;
import common.dto.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This tells Spring how to handle the web request
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationProducer producer;

    @PostMapping
    public String handleNotification(@RequestBody NotificationDTO notification) {
        producer.sendNotification(notification);
        return "Notification sent to queue!";
    }

    @PostMapping("/bulk")
    public String sendBulk(@RequestBody List<NotificationDTO> notification){
        notification.forEach(producer::sendNotification);
        return "Successfully queued " + notification.size() + " notifications!";
    }
}