package com.adidas.notification.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class EmailApiPropertiesConfig {

    @Value("${email.api.host}")
    private String emailApiHost;

    @Value("${email.api.token}")
    private String emailApiAuth;

}
