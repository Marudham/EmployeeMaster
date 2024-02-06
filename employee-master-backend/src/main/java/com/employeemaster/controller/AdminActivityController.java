package com.employeemaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeemaster.entity.AdminActivity;
import com.employeemaster.entity.ApiResponse;
import com.employeemaster.service.AdminActivityService;
import com.employeemaster.service.AdminService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/ems/controller")
public class AdminActivityController {
	
	@Autowired
	AdminActivityService adminActivityService;
	
	@Autowired
	AdminService adminService;
	
	ApiResponse response;

	@GetMapping("/fetchActivity")
	public ResponseEntity<ApiResponse> fetchActivity(@RequestParam Long id){
		response = new ApiResponse();
		try {
			List<AdminActivity> adminActivities = adminActivityService.fetchActivityByAdminId(id);
			response.setStatus("success");
			response.setAdminActivities(adminActivities);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected Error Occured retrieving Admin Activities");
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
}
