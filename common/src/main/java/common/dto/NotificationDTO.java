package common.dto;

public record NotificationDTO  (
	 String recipient,
     String content,
     String providerType
){}
