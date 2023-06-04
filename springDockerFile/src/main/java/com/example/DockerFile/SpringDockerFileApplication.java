package com.example.DockerFile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringDockerFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDockerFileApplication.class, args);
	}
	
	@GetMapping("/docker")
	public String get() {
		return "I am from dockerfile exmaple service!!!";
	}

}
