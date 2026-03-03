#!/bin/bash

# This script sends a JSON Array to the Producer
# The Producer will need to be updated to handle List<NotificationDTO>
curl -X POST http://localhost:8081/notifications/bulk \
     -H "Content-Type: application/json" \
     -d @post_data.json

echo -e "\n\n✅ Bulk notifications sent to Producer!"