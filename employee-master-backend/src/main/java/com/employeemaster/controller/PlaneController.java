package com.employeemaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import com.employeemaster.service.AdminService;

@Controller
@RequestMapping("ems/controller")
public class PlaneController {

	@Autowired
	AdminService adminService;
	
	@GetMapping("/verify")
	public String verifyToken(@RequestParam String token, @RequestParam Long id, Model model) {
		try {
			if(adminService.verifyToken(id,token)) {
				model.addAttribute("message", "success");
				return "verification"; 
			}else {
				model.addAttribute("message", "cannot-verify");
				return "verification";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "error");
			return "verification";
		}
	}
}
