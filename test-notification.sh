#!/bin/bash

# Trigger the Notification Producer API
curl -X POST http://localhost:8081/notifications \
     -H "Content-Type: application/json" \
     -d '{
           "recipient": "user@email.com",
           "content": "Welcome to Notification Engine",
           "providerType": "EMAIL"
         }'

echo -e "\nNotification sent to Producer!"
