package com.adidas.subscription.exception;

import com.adidas.subscription.util.Messages;

public class SubscriptionAlreadyExistsException extends Exception {

	private static final String MESSAGE = Messages.SUBSCRIPTION_ALREADY_EXISTS;

	public SubscriptionAlreadyExistsException(String email) {
		super(String.format(MESSAGE, email));
	}

}
