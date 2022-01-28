package com.adidas.subscription.service;

import com.adidas.subscription.dto.CreateSubscriptionDTO;
import com.adidas.subscription.dto.SubscriptionDTO;
import com.adidas.subscription.entity.Subscription;
import com.adidas.subscription.mapper.SubscriptionMapper;
import com.adidas.subscription.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private SubscriptionMapper subscriptionMapper;

	@Autowired
	private NotificationService notificationService;

	public Subscription saveSubscription(CreateSubscriptionDTO createSubscriptionDTO) {
		checkMandatoryFields(createSubscriptionDTO);
		Subscription subscription = subscriptionMapper.mapObject(createSubscriptionDTO, Subscription.class);
		Subscription subscriptionSaved = subscriptionRepository.save(subscription);

		CompletableFuture.runAsync(() -> notificationService.sendEmail(subscriptionSaved.getEmail()));

		return subscriptionSaved;
	}

	public List<SubscriptionDTO> getSubscriptions(List<Long> subscriptionIds) {
		List<Subscription> subscriptions;
		if (CollectionUtils.isEmpty(subscriptionIds)) {
			subscriptions = subscriptionRepository.findAll();
			return subscriptionMapper.mapList(subscriptions, SubscriptionDTO.class);
		}

		subscriptions = subscriptionRepository.findAllById(subscriptionIds);
		return subscriptionMapper.mapList(subscriptions, SubscriptionDTO.class);
	}

	public void cancelSubscription(Long subscriptionId) {
		Subscription subscription = subscriptionRepository.getById(subscriptionId);
		if (subscription == null) {
			throw new NullPointerException();
		}
		subscription.setConsent(false);
		subscriptionRepository.save(subscription);
	}

	private void checkMandatoryFields(CreateSubscriptionDTO createSubscriptionDTO) {
		if (!StringUtils.hasText(createSubscriptionDTO.getEmail()) || createSubscriptionDTO.getBirthdate() == null ||
				createSubscriptionDTO.getConsent() == null && createSubscriptionDTO.getNewsletterId() == null)
			throw new RuntimeException();
	}
}
