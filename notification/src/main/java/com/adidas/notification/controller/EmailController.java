package com.adidas.notification.controller;

import com.adidas.notification.dto.EmailRequestDTO;
import com.adidas.notification.service.EmailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Email")
@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping(value = "/v0/email", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequestDTO emailRequestDTO) {
		emailService.sendEmail(emailRequestDTO);
		return ResponseEntity.ok().build();
	}
	
}
