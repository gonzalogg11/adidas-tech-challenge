package com.adidas.subscription.controller;

import com.adidas.subscription.dto.CreateSubscriptionDTO;
import com.adidas.subscription.dto.SubscriptionDTO;
import com.adidas.subscription.entity.Subscription;
import com.adidas.subscription.service.SubscriptionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Subscription")
@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@PostMapping(value = "/v0/subscription", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> createSubscription(@RequestBody CreateSubscriptionDTO createSubscriptionDTO) {
		Subscription subscription = subscriptionService.saveSubscription(createSubscriptionDTO);

		return ResponseEntity.ok(subscription.getId());
	}

	@GetMapping(value = "/v0/subscription", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubscriptionDTO>> getSubscriptions(@RequestParam(value = "ids", required = false) List<Long> subscriptionIds) {
		List<SubscriptionDTO> subscriptions = subscriptionService.getSubscriptions(subscriptionIds);

		return ResponseEntity.ok(subscriptions);
	}

	@PostMapping(value = "/v0/subscription/{id}/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SubscriptionDTO> cancelSubscription(@PathVariable(value = "id") Long subscriptionId) {
		subscriptionService.cancelSubscription(subscriptionId);

		return ResponseEntity.ok().build();
	}
	
}
