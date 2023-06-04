package com.example.SpringSecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SecurityController {

	@PreAuthorize(value="hasRole('ROLE_NORMAL')")
	@GetMapping("/normal")
	public String normal() {
		return "I am normal kittu";
	}
	
	@PreAuthorize(value="hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public String admin() {
		return "I am admin priya";
	}
}
