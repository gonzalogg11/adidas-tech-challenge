package com.adidas.subscription.dto.notification;

import com.adidas.subscription.util.EmailOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequestDTO {

	private String email;
	private EmailOperation emailOperation;

}
