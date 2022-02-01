package com.adidas.notification.service;

import com.adidas.notification.client.EmailApiClient;
import com.adidas.notification.dto.EmailDTO;
import com.adidas.notification.dto.EmailRequestDTO;
import com.adidas.notification.util.Constants;
import com.adidas.notification.util.LangEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private final Logger log = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private EmailApiClient emailApiClient;

	@Autowired
	private MessageService messageService;

	public void sendEmail(EmailRequestDTO emailRequest) {
		EmailDTO emailDTO = buildEmail(emailRequest);
		log.info("Sending email to {}, SUBJECT: {}, BODY:{}", emailDTO.getEmail(), emailDTO.getSubject(), emailDTO.getBody());
		emailApiClient.sendEmail(emailDTO);
	}

	private EmailDTO buildEmail(EmailRequestDTO emailRequest) {
		String language = LangEnum.getLanguage(emailRequest.getLanguage());
		String subject = messageService.getMessage(emailRequest.getEmailOperation().toString() +
				Constants.EMAIL_SUBJECT, language);
		String body = messageService.getMessage(emailRequest.getEmailOperation().toString() +
				Constants.EMAIL_BODY, language);


		return new EmailDTO(emailRequest.getEmail(), subject, body);
	}

}
