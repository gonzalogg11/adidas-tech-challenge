package com.adidas.oracle.dto;

import com.adidas.oracle.util.Gender;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CreateSubscriptionDTO {

	@NotBlank(message = "Email is a required field")
	@Email(message = "Email must be a valid email")
	private String email;
	private String firstName;
	private Gender gender;
	@NotNull(message = "Birthdate is a required field")
	private Date birthdate;
	@NotNull(message = "Consent is a required field")
	private Boolean consent;
	@NotNull(message = "Newsletter id is a required field")
	private Long newsletterId;

}
