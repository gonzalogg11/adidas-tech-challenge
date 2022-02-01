package com.adidas.oracle.client;

import com.adidas.oracle.dto.ApiResponseDTO;
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

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RibbonClient(name = "subscription")
@Component
public class SubscriptionClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${subscription.api.url:http://subscription/v0/subscription}")
    private String subscriptionApiUrl;

    public Long createSubscription(CreateSubscriptionDTO createSubscriptionDTO) {
        HttpEntity<Object> httpEntity = HTTPUtil.getObjectHttpEntity(createSubscriptionDTO);
        ApiResponseDTO response = this.restTemplate.postForObject(subscriptionApiUrl, httpEntity, ApiResponseDTO.class);
        Integer id = (Integer) Objects.requireNonNull(response).getResponse();
        return id.longValue();
    }

    public List<SubscriptionDTO> getSubscriptions(List<Long> subscriptionIds) {
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(subscriptionApiUrl);
        if (!CollectionUtils.isEmpty(subscriptionIds)) {
            uri.queryParam("ids", subscriptionIds);
        }
        ApiResponseDTO response = this.restTemplate.getForObject(uri.toUriString(), ApiResponseDTO.class);
        if (response != null && response.getResponse() != null) {
            return (List<SubscriptionDTO>) response.getResponse();
        }
        return Collections.emptyList();
    }

    public void cancelSubscription(Long subscriptionId) {
        HttpEntity<Object> httpEntity = HTTPUtil.getObjectHttpEntity(null);
        this.restTemplate.delete(subscriptionApiUrl + String.format("/%s", subscriptionId), httpEntity);
    }
}
