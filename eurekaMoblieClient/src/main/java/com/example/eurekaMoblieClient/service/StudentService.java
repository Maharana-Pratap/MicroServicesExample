package com.example.eurekaMoblieClient.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.eurekaMoblieClient.modules.Student;

//@Service
//@FeignClient(name = "abc", url = "http://192.168.1.35:8099")
@FeignClient(name = "student-service") // eureka server registered service name
public interface StudentService {

	@GetMapping("/student/getAll")
	public List<Student> getAllStudents();
}
