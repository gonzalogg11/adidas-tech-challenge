package com.adidas.oracle.exception;


import com.adidas.oracle.util.Messages;

public class UserNotAuthorizedException extends Exception {

	private static final String MESSAGE = Messages.USER_NOT_AUTHORIZED_EXCEPTION;

	public UserNotAuthorizedException() {
		super(MESSAGE);
	}

}
