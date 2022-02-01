package com.adidas.subscription.client;

import com.adidas.subscription.dto.notification.EmailRequestDTO;
import com.adidas.subscription.util.HTTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RibbonClient(name = "notification")
@Component
public class NotificationClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${notification.api.url:http://notification/v0/}")
    private String notificationApiUrl;

    public void sendEmail(EmailRequestDTO notificationRequest) {
        HttpEntity<Object> httpEntity = HTTPUtil.getObjectHttpEntity(notificationRequest);
        this.restTemplate.postForLocation(notificationApiUrl + "email" , httpEntity);
    }
}
