package com.example.eurekaStudentClient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.eurekaStudentClient.module.Student;

@Service 
public class StudentService {

	public List<Student> getAllStidemt() {
		Student st1 = new Student(101, "mukesh", "samsung");
		Student st2 = new Student(102, "Suresh", "Iphone");
		List<Student> studentList = List.of(st1,st2);
		
		return studentList;				
	}
}
