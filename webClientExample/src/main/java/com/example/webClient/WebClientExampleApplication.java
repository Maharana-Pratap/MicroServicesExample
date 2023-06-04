package com.example.webClient;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@SpringBootApplication
@RestController 
public class WebClientExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebClientExampleApplication.class, args);
	}
	
	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}
	
	@GetMapping("/students")
	public ResponseEntity<?> getStudents() {
		WebClient client = WebClient.create("http://192.168.1.35:8099/student");
		Flux<Student> studentList = client.get().uri("/getAll").retrieve().bodyToFlux(Student.class);
		List<Student> response = studentList.toStream().toList();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getAllStudents")
	public ResponseEntity<?> getAllStudents() {
		//WebClient client = WebClient.create("http://192.168.1.35:8099/student");
		Flux<Student> studentList = webClient()
				.get()
				.uri("http://192.168.1.35:8099/student/getAll")
				.retrieve()
				.bodyToFlux(Student.class);
		List<Student> response = studentList.toStream().toList();
		return ResponseEntity.ok(response);
	}

}
