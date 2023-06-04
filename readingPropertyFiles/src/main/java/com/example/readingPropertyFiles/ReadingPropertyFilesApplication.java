package com.example.readingPropertyFiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ReadingPropertyFilesApplication {

	@Autowired
	private Environment environment;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ReadingPropertyFilesApplication.class, args);
	}
	
	@GetMapping("/pro")
	public String getPropertyvalue() {
		String port = environment.getProperty("server.port");
		String appName = environment.getProperty("spring.application.name");
		return "Server Port : "+port+", Application Name : "+appName;
	}

}
