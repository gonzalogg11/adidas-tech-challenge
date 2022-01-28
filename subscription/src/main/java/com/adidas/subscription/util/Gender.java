package com.adidas.subscription.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

	MALE("Male"),
	FEMALE("Female"),
	OTHER("Other");

	private final String value;

}
