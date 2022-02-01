package com.adidas.notification.client;

import com.adidas.notification.config.EmailApiPropertiesConfig;
import com.adidas.notification.dto.EmailDTO;
import com.adidas.notification.util.HTTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Component
public class EmailApiClient {

    private final Logger log = LoggerFactory.getLogger(EmailApiClient.class);

    @Autowired
    private EmailApiPropertiesConfig config;

    public void sendEmail(EmailDTO emailRequest) {
        HttpEntity<Object> httpEntity = HTTPUtil.getObjectHttpEntity(emailRequest, config.getEmailApiAuth());
        String response = getRestTemplate().postForObject(config.getEmailApiHost() + "/email", httpEntity, String.class);
        log.info("Email sent response -> {}", response);
    }

    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}
