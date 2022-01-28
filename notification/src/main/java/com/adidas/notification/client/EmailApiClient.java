package com.adidas.notification.client;

import com.adidas.notification.config.EmailApiPropertiesConfig;
import com.adidas.notification.dto.EmailRequestDTO;
import com.adidas.notification.util.HTTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Component
public class EmailApiClient {

    @Autowired
    private EmailApiPropertiesConfig config;

    public void sendEmail(EmailRequestDTO emailRequest) {
        HttpEntity<Object> httpEntity = HTTPUtil.getObjectHttpEntity(emailRequest, config.getEmailApiAuth());
        getRestTemplate().postForLocation(config.getEmailApiHost() + "/email", httpEntity);
    }

    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}
