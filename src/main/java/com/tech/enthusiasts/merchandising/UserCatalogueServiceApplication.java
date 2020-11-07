package com.tech.enthusiasts.merchandising;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class UserCatalogueServiceApplication {
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		final HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory
			=  new HttpComponentsClientHttpRequestFactory();
		httpComponentsClientHttpRequestFactory.setConnectTimeout(3000);
		return new RestTemplate(httpComponentsClientHttpRequestFactory);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(UserCatalogueServiceApplication.class, args);
	}

}
