package com.adidas.oracle.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseDTO {

	private Date timestamp;
	private Integer status;
	private String error;
	private String message;
	private Object response;

	public ApiResponseDTO(HttpStatus status) {
		this.timestamp = new Date();
		this.status = status.value();
		this.response = status.getReasonPhrase();
	}

	public ApiResponseDTO(HttpStatus status, Object response) {
		this.timestamp = new Date();
		this.status = status.value();
		this.response = response;
	}

	public ApiResponseDTO(HttpStatus status, String error) {
		this.timestamp = new Date();
		this.status = status.value();
		this.error = status.getReasonPhrase();
		this.message = error;
	}

}
