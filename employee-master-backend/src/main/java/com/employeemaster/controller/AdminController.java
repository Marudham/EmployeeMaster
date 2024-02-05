package com.employeemaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeemaster.service.EmployeeService;
import com.employeemaster.entity.Admin;
import com.employeemaster.entity.ApiResponse;
import com.employeemaster.entity.Employee;
import com.employeemaster.entity.LoginData;
import com.employeemaster.entity.RegisterData;
import com.employeemaster.service.AdminService;
import com.employeemaster.service.EmailService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/ems/controller")
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmailService emailService;

	ApiResponse response = new ApiResponse();

	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@RequestBody LoginData data,Model model,HttpSession session) {
		try {
			response = new ApiResponse();
			String email = data.getEmail();
			String password = data.getPassword();
			if(adminService.isAdminExist(email)) {
				if(adminService.isValidAdmin(email,password)) {
					Admin admin = adminService.getAdmin(email);
					if(admin.isVerified()) {
						if(admin.isAdmin()) {
							session.setAttribute("user", adminService.getAdmin(email).getUsername());
							session.setAttribute("adminId", adminService.getAdmin(email).getId());
							response.setStatus("success");
							return ResponseEntity.ok(response);
						}else {
							response.setStatus("not-approved");
							response.setMessage("Entered Email is not Approved By SUPER_ADMIN");
							return ResponseEntity.badRequest().body(response);
						}
					}else {
						response.setStatus("not-verified");
						response.setMessage("Entered Email is not verified");
						return ResponseEntity.badRequest().body(response);
					}
				}else {
					response.setStatus("incorrect-password");
					response.setMessage("Entered Password is Incorrect");
					return ResponseEntity.badRequest().body(response);
				}
			}else {
				response.setStatus("email-not-match");
				response.setMessage("Entered Email Does not exist");
				return ResponseEntity.badRequest().body(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected Error Occured While Verifying Login, Please try Again");
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(@RequestBody RegisterData data,Model model) {
		try {
			response = new ApiResponse();
			Admin admin = new Admin();
			admin.setEmail(data.getEmail());
			admin.setPassword(data.getPassword());
			admin.setUsername(data.getUsername());
			if(!adminService.isAdminExist(admin.getEmail())) {
				adminService.addAdmin(admin);
				response.setStatus("success");
				response.setMessage("Registered Successfully");
				return ResponseEntity.ok(response);
			}else {
				response.setStatus("already-exist");
				response.setMessage("Entered Email already Registered");
				return ResponseEntity.badRequest().body(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected Error Occured While Registering, Please try Again");
			return ResponseEntity.internalServerError().body(response);
		}

	}

	@GetMapping("/fetchEmployees")
	public ResponseEntity<ApiResponse> fetchEmployees(@RequestParam Long id){
		try {
			response = new ApiResponse();
			List<Employee> employeeList = employeeService.fetchAllEmployeeByAdminId(id);
			response.setStatus("success");
			response.setEmployeeList(employeeList);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected Error Occured While Retrieving Employee details, Please try Again");
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@GetMapping("/getAdminId")
	public ResponseEntity<Long> getAdminId(@RequestParam String email){
		try {
			response = new ApiResponse();
			return ResponseEntity.ok(adminService.getAdmin(email).getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(-1l);
		}
	}
	
	@PostMapping("/requestEmail")
	public String requestEmail(@RequestParam String email, Model model) {
		try {
			if(adminService.isAdminExist(email)) {
				adminService.sendVerificationEmail(email);
				model.addAttribute("message", "Verification Email has been Sent");
				return "requestEmail";
			}else {
				model.addAttribute("message", "Cannot Find the Email, Please Enter Correct Email or Register");
				return "requestEmail";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Problem Occured While sending Email, Enter a Valid Email or try Again");
			return "requestEmail";
		}
	}

}
