package com.adidas.oracle.client;

import com.adidas.oracle.dto.CreateSubscriptionDTO;
import com.adidas.oracle.dto.SubscriptionDTO;
import com.adidas.oracle.util.HTTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RibbonClient(name = "subscription")
@Component
public class SubscriptionClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${subscription.api.url:http://subscription/v0/subscription}")
    private String subscriptionApiUrl;

    public void sendEmail(String email) {
        this.restTemplate.postForLocation(subscriptionApiUrl + "email?email=" + email, null);
    }

    public Long createSubscription(CreateSubscriptionDTO createSubscriptionDTO) {
        HttpEntity<Object> httpEntity = HTTPUtil.getObjectHttpEntity(createSubscriptionDTO);
        return this.restTemplate.postForObject(subscriptionApiUrl, httpEntity, Long.class);
    }

    public List<SubscriptionDTO> getSubscriptions(List<Long> subscriptionIds) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(subscriptionApiUrl);
        if (!CollectionUtils.isEmpty(subscriptionIds)) {
            uriComponentsBuilder.queryParam("ids", subscriptionIds);
        }
        return Arrays.asList(Objects.requireNonNull(this.restTemplate.getForObject(subscriptionApiUrl, SubscriptionDTO[].class)));
    }

    public void cancelSubscription(Long subscriptionId) {
        HttpEntity<Object> httpEntity = HTTPUtil.getObjectHttpEntity(null);
        this.restTemplate.postForLocation(subscriptionApiUrl + String.format("/%s/cancel", subscriptionId), httpEntity);
    }
}
