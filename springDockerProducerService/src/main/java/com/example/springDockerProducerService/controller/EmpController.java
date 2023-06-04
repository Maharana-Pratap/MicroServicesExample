package com.example.springDockerProducerService.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springDockerProducerService.models.Emp;
import com.example.springDockerProducerService.service.EmpService;

@RestController
@RequestMapping("/emp")
public class EmpController {

	@Autowired
	private EmpService empService;
	
	@GetMapping("/all")
	public List<Emp> getEmps() {
		return empService.getallEmps();
	}
	
	@PostMapping("/emp")
	public List<Emp> addEmp(@RequestBody Emp emp) {
		return empService.addEmp(emp);
	}
	
	@PutMapping("/emp")
	public Emp editEmp(@RequestBody Emp emp) {
		return empService.editEmp(emp);
	}
	
	@PatchMapping("/emp/{id}")
	public Emp patchEmp(@PathVariable Integer id, @RequestBody Map<String,Object> fields) {
		return empService.partialyUpdateEmp(id,fields);
	}
}
