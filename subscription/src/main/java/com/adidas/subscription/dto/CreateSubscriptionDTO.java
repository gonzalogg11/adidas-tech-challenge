package com.adidas.subscription.dto;

import com.adidas.subscription.util.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class CreateSubscriptionDTO {

	private String email;
	private String firstName;
	private Gender gender;
	private Date birthdate;
	private Boolean consent;
	private Long newsletterId;

}
