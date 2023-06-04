package com.example.eurekaSererver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaSererverApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaSererverApplication.class, args);
	}

}
