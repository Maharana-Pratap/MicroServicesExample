package com.example.springDockerConsumerService.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/all")
	public List<EmpDto> getEmpsList() {
		String urlString = "http://localhost:1111/emp/all";
		EmpDto[] dtos = restTemplate.getForObject(urlString, EmpDto[].class);
		List<EmpDto> responseList = Arrays.asList(dtos);
		return responseList;
	}
}
