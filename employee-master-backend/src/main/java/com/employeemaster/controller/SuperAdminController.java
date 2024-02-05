package com.employeemaster.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeemaster.service.SuperAdminService;
import com.employeemaster.entity.Admin;
import com.employeemaster.entity.ApiResponse;
import com.employeemaster.entity.LoginData;

import jakarta.servlet.http.HttpSession;

import com.employeemaster.service.AdminService;
import com.employeemaster.service.EmailService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/ems/controller")
public class SuperAdminController {

	@Autowired
	SuperAdminService superAdminService;

	@Autowired
	AdminService adminService;

	@Autowired
	EmailService emailService;

	ApiResponse response = new ApiResponse();

	@PostMapping("/superAdminLogin")
	public ResponseEntity<ApiResponse> superAdminLogin(@RequestBody LoginData data, HttpSession session) {
		try {
			response = new ApiResponse();
			String email = data.getEmail();
			String password = data.getPassword();
			if(superAdminService.isSuperAdminExist(email)) {
				if(superAdminService.isValidSuperAdmin(email, password)) {
					session.setAttribute("superAdmin", email);
					response.setStatus("success");
					return ResponseEntity.ok(response);
				}else {
					response.setStatus("password-mismatch");
					response.setMessage("Incorrect Password");
					return ResponseEntity.badRequest().body(response);
				}
			}else {
				response.setStatus("not-exist");
				response.setMessage("Entered Email is not of the SUPER_ADMIN");
				return ResponseEntity.badRequest().body(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected Error Occured While Verifying Login, Please try Again");
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@GetMapping("/fetchAdmins")
	public ResponseEntity<ApiResponse> fetchAdmins(){
		try {
			response = new ApiResponse();
			response.setStatus("success");
			response.setMessage("Admin lists");
			response.setAdminList(adminService.getAllAdmins());
			return ResponseEntity.ok(response);
		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected error occured while retrieving Admin details , Please try Again");
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@GetMapping("/approve/{id}")
	public ResponseEntity<ApiResponse> approve(@PathVariable Long id, HttpSession session) {
		try {
			response = new ApiResponse();
			Admin admin = adminService.getAdminById(id);
			admin.setAdmin(true);
			adminService.updateAdmin(admin);
			response.setStatus("success");
			return ResponseEntity.ok(response);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected error occured while updating admin approve status, Please try Again");
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@GetMapping("/disapprove/{id}")
	public ResponseEntity<ApiResponse> disapprove(@PathVariable Long id) {
		try {
			response = new ApiResponse();
			Admin admin = adminService.getAdminById(id);
			admin.setAdmin(false);
			adminService.updateAdmin(admin);
			response.setStatus("success");
			return ResponseEntity.ok(response);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected error occured while updating admin approve status, Please try Again");
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
	@GetMapping("/deleteAdmin/{id}")
	public ResponseEntity<ApiResponse> deleteAdmin(@PathVariable Long id) {
		try {
			response = new ApiResponse();
			adminService.deleteAdmin(id);
			response.setStatus("success");
			return ResponseEntity.ok(response);
		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected error occured while deleting admin");
			return ResponseEntity.internalServerError().body(response);
		}
	}


	@GetMapping("/superAdmin")
	public String superAdmin(Model model,HttpSession session) {
		try {
			if(session.getAttribute("superAdmin") != null) {
				model.addAttribute("admins", adminService.getAllAdmins());
				return "superAdmin";
			}else {
				return "superAdminLogin";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "superAdminLogin";
		}
	}
}
