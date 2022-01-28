package com.adidas.notification.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ProfileConfiguration {

	@Configuration
	@Profile({ "default" })
	@PropertySource("classpath:notification-default.properties")
	static class CloudDefaultConfig {
		@LoadBalanced
		@Bean
		RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}

}
