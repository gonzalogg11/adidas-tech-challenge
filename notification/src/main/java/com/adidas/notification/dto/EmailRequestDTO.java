package com.adidas.notification.dto;

import com.adidas.notification.util.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class EmailRequestDTO {

	private String email;
	private String firstName;
	private Gender gender;
	private Date birthdate;
	private Boolean consent;
	private Long newsletterId;

}
