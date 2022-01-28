package com.adidas.oracle.service;

import com.adidas.oracle.client.SubscriptionClient;
import com.adidas.oracle.dto.CreateSubscriptionDTO;
import com.adidas.oracle.dto.SubscriptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionClient subscriptionClient;

	public Long saveSubscription(CreateSubscriptionDTO createSubscriptionDTO) {
		return subscriptionClient.createSubscription(createSubscriptionDTO);
	}

	public List<SubscriptionDTO> getSubscriptions(List<Long> subscriptionIds) {
		return subscriptionClient.getSubscriptions(subscriptionIds);
	}

	public void cancelSubscription(Long subscriptionId) {
		subscriptionClient.cancelSubscription(subscriptionId);
	}
}
