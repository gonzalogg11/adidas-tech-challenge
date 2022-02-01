package com.adidas.notification.dto;

import com.adidas.notification.util.EmailOperation;
import com.adidas.notification.util.LangEnum;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EmailRequestDTO {

	@NotBlank(message = "Email is a required field")
	@Email(message = "Email must be a valid email")
	private String email;
	@NotNull(message = "Email operation is a required field")
	private EmailOperation emailOperation;
	private LangEnum language;

}
