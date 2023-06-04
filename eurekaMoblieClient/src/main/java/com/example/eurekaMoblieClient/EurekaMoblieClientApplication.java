package com.example.eurekaMoblieClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class EurekaMoblieClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaMoblieClientApplication.class, args);
	}

	@Bean 
	public RestTemplate getRestClient() {
		return new RestTemplate();
	}
	
	
	  @Bean  
	  public WebClient webClient() { 
		  return WebClient.builder().build();
	  }	 
}
