package com.example.eurekaStudentClient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eurekaStudentClient.module.Student;
import com.example.eurekaStudentClient.service.StudentService;

@RestController 
@RequestMapping("/student")
public class StudentController {
	
	@Autowired 
	private StudentService studentService;
	
	@GetMapping("/test")
	public String callTest() {
		return "I am from Student service";
	}

	@GetMapping("/getAll")
	public List<Student> getAll() {
		return studentService.getAllStidemt();
	}
}
