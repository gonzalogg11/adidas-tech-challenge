package com.adidas.notification.service;

import com.adidas.notification.client.EmailApiClient;
import com.adidas.notification.dto.EmailRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private EmailApiClient notificationClient;

	public void sendEmail(EmailRequestDTO emailRequest) {
		notificationClient.sendEmail(emailRequest);
	}

}
