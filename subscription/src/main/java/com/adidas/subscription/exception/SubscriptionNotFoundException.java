package com.adidas.subscription.exception;


import com.adidas.subscription.util.Messages;

public class SubscriptionNotFoundException extends Exception {

	private static final String MESSAGE = Messages.SUBSCRIPTION_NOT_FOUND_EXCEPTION;

	public SubscriptionNotFoundException(Long id) {
		super(String.format(MESSAGE, id));
	}

}
