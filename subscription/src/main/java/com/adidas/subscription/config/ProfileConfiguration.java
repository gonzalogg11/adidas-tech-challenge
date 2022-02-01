package com.adidas.subscription.config;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ProfileConfiguration {

	@Configuration
	@Profile({ "default" })
	@PropertySource("classpath:subscription.properties")
	static class CloudDefaultConfig {
		@LoadBalanced
		@Bean
		RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}

	@Configuration
	@Profile({"test"})
	@PropertySource("classpath:subscription.properties")
	static class CloudTestConfig {
		@Primary
		@Bean
		RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
