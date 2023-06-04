package com.example.eurekaMoblieClient.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.eurekaMoblieClient.modules.Student;
import com.example.eurekaMoblieClient.service.StudentService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/mobile")
public class MobileController {

	@Autowired 
	//@LoadBalanced
	private RestTemplate restTemplate;
	
	@Autowired 
	private StudentService studentServiceFeign;
	
	@Autowired 
	private LoadBalancerClient loadBalancerClient;
	
	@Autowired
	private WebClient webClient;
	
	@GetMapping("/test")
	public String getMobile() {
		return "I am from Moblie service";
	}
	
	// Normal RestTemplate call
	@GetMapping("/students")
	public List<Student> getStudents() {
		String studentUrl = "http://192.168.1.33:1014/student/getAll";
		Student[] studentList = restTemplate.getForObject(studentUrl, Student[].class);
		List<Student> response = Arrays.asList(studentList);
		return response;
	}
	
	// FeignClient call 
	@GetMapping("/studentsFeign")
	public List<Student> getStudentsByFeighClient() {
		List<Student> response = studentServiceFeign.getAllStudents();
		return response;
	}	
	
	// RestTemplate with clientside Load-Balancing approach
	@GetMapping("/studentLoad")
	public List<Student> getAllStudentRecords() {
		
		// here Serviceinstance use for client side Load-Balancing
		// this Serviceinstance pick randomly or diffrent port no
		// instences if it has multiple port with same instances service
		ServiceInstance serviceInstance = loadBalancerClient
				.choose("STUDENT-SERVICE");
		
		String uri = serviceInstance.getUri().toString();
		if(!uri.isBlank()) {
			uri = uri + "/student/getAll";
			System.out.println("getUri : "+uri);
		}
		Student[] studentList = restTemplate.getForObject(uri.toString(), Student[].class);
		List<Student> response = Arrays.asList(studentList);
		return response;
	}
	
	// Client side load-balanceing using web-client
	@GetMapping("/studentWeb")
	public List<Student> getStudentsUsingWebClient() {
		ServiceInstance serviceInstance = loadBalancerClient
				.choose("STUDENT-SERVICE");
		
		String uri = serviceInstance.getUri().toString();
		if(!uri.isBlank()) {
			uri = uri + "/student/getAll";
			System.out.println("getUri : "+uri);
		}
		Flux<Student> studentList = webClient
				              .get()
				              .uri(uri.toString())
				              .retrieve()
				              .bodyToFlux(Student.class);
		List<Student> response = studentList
								.toStream()
								.collect(Collectors.toList());
		return response;
	}
}
