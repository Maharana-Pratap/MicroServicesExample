package com.example.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/cb")
public class CircutBreakerController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient webClient;
	
	private static String BASE_URL = "http://localhost:8181/b";

	private static final String SERVICE_B = "service_B";
	
	// Fault-Tolrance using @CircuitBreaker
	@GetMapping("/a")
	@CircuitBreaker(name = SERVICE_B, fallbackMethod = "fallBackForServiceB")
	public String callStudentService() {
		return restTemplate.getForObject(BASE_URL, String.class);
	}
	
	public String fallBackForServiceB(Exception ex) {
		return "this is fallBackMethod for SERVICE_B";
	}
	
	int count = 0;
	
	// Fault-Tolrance using @Retry
	@GetMapping("/callB")
	@Retry(name = SERVICE_B, fallbackMethod = "fallBackForServiceB")
	public String callBretry() {
		System.out.println("Calling attempts : "+ ++count);
		return webClient
				.get()
				.uri(BASE_URL)
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}
}
