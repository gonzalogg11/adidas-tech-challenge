package com.adidas.notification.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

public class HTTPUtil {

	HTTPUtil() {
	}

	public static HttpEntity<Object> getObjectHttpEntity(Object request, String authToken) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
		header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		if (StringUtils.hasText(authToken)) {
			header.add(HttpHeaders.AUTHORIZATION, authToken);
		}
		return new HttpEntity<>(request, header);
	}

}
