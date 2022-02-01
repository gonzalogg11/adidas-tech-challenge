package com.adidas.subscription.service;

import com.adidas.subscription.client.NotificationClient;
import com.adidas.subscription.dto.notification.EmailRequestDTO;
import com.adidas.subscription.util.EmailOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	@Autowired
	private NotificationClient notificationClient;

	public void sendEmail(String email, EmailOperation emailOperation) {
		EmailRequestDTO notificationRequest = new EmailRequestDTO(email, emailOperation);
		notificationClient.sendEmail(notificationRequest);
	}

}
