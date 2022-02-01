package com.adidas.subscription.service;

import com.adidas.subscription.dto.CreateSubscriptionDTO;
import com.adidas.subscription.dto.SubscriptionDTO;
import com.adidas.subscription.entity.Subscription;
import com.adidas.subscription.exception.SubscriptionAlreadyExistsException;
import com.adidas.subscription.exception.SubscriptionNotFoundException;
import com.adidas.subscription.mapper.SubscriptionMapper;
import com.adidas.subscription.repository.SubscriptionRepository;
import com.adidas.subscription.util.EmailOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private SubscriptionMapper subscriptionMapper;

	@Autowired
	private NotificationService notificationService;

	public Subscription saveSubscription(CreateSubscriptionDTO createSubscriptionDTO) throws SubscriptionAlreadyExistsException {
		checkIfSubscriptionAlreadyExists(createSubscriptionDTO);
		Subscription subscription = subscriptionMapper.mapObject(createSubscriptionDTO, Subscription.class);
		subscription.setCreated(new Date());
		Subscription subscriptionSaved = subscriptionRepository.save(subscription);

		CompletableFuture.runAsync(() -> notificationService.sendEmail(subscriptionSaved.getEmail(),
				EmailOperation.SUBSCRIPTION_ACTIVATED));

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

	public void cancelSubscription(Long subscriptionId) throws SubscriptionNotFoundException {
		Optional<Subscription> optional = subscriptionRepository.findById(subscriptionId);
		if (!optional.isPresent()) throw new SubscriptionNotFoundException(subscriptionId);
		subscriptionRepository.delete(optional.get());

		CompletableFuture.runAsync(() -> notificationService.sendEmail(optional.get().getEmail(),
				EmailOperation.SUBSCRIPTION_CANCELED));
	}

	private void checkIfSubscriptionAlreadyExists(CreateSubscriptionDTO createSubscriptionDTO) throws SubscriptionAlreadyExistsException {
		Subscription existingSubscription = subscriptionRepository.findByEmail(createSubscriptionDTO.getEmail());
		if (existingSubscription != null) {
			throw new SubscriptionAlreadyExistsException(createSubscriptionDTO.getEmail());
		}
	}

}
