package com.adidas.oracle.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class HTTPUtil {

	HTTPUtil() {
	}

	public static HttpEntity<Object> getObjectHttpEntity(Object request) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
		header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(request, header);
	}

}
