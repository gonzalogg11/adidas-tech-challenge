package com.adidas.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {

	@Autowired
	private MessageSource messageSource;
	
	public String getMessage(String code, String language) {
		Locale locale = new Locale(language);
		return messageSource.getMessage(code, null, locale);
	}
}
