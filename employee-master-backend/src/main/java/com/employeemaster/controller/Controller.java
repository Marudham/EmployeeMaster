package com.employeemaster.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/controller")
public class Controller {

	@GetMapping("/msg")
	public String message() {
		System.out.println("request recieved");
		return "/***\n message from employee-master-backend\n ***/";
	}
}
