package com.adidas.subscription.service;

import com.adidas.subscription.client.NotificationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	@Autowired
	private NotificationClient notificationClient;

	public void sendEmail(String email) {
		notificationClient.sendEmail(email);
	}

}
