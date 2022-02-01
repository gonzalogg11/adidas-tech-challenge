package com.adidas.oracle.service;

import com.adidas.oracle.exception.UserNotAuthorizedException;
import com.adidas.oracle.util.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	public void auth(String authToken) throws UserNotAuthorizedException {
		if (!StringUtils.equals(authToken, Constants.API_AUTH_TOKEN)) {
			throw new UserNotAuthorizedException();
		}
	}

}
