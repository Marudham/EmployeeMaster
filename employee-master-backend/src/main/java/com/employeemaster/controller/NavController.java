package com.employeemaster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/addEmployee")
	public String addEmployee(HttpSession session) {
		if(session.getAttribute("user") != null) {
			return "addEmployee";
		}else {
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String index(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	
	@GetMapping("/superAdminLogin")
	public String superAdminLogin() {
		return "superAdminLogin";
	}
	
	@GetMapping("/requestEmail")
	public String requestEmail() {
		return "requestEmail";
	}
	
	@GetMapping("/employeeLogin")
	public String employeeLogin() {
		return "employeeLogin";
	}
	
	@GetMapping("/forgotPassword")
	public String forgotPassword() {
		return "forgotPassword";
	}
	
		
}
