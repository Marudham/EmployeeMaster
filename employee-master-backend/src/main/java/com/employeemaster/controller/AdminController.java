package com.employeemaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employeemaster.service.EmployeeService;
import com.employeemaster.entity.Admin;
import com.employeemaster.service.AdminService;
import com.employeemaster.service.EmailService;

import jakarta.servlet.http.HttpSession;



@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmailService emailService;

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password,Model model,HttpSession session) {
		try {
			if(adminService.isAdminExist(email)) {
				if(adminService.isValidAdmin(email,password)) {
					if(adminService.getAdmin(email).isVerified()) {
						if(adminService.getAdmin(email).isAdmin()) {
							session.setAttribute("user", adminService.getAdmin(email).getUsername());
							session.setAttribute("adminId", adminService.getAdmin(email).getId());
							model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
							return "redirect:/adminHome";
						}else {
							model.addAttribute("message", "Entered Email is not Approved By SUPER_ADMIN");
							return "login";
						}
					}else {
//						ResponseEntity.badRequest().
						model.addAttribute("message", "Entered Email is not verified, Check your Email or Click on Verify Email");
						model.addAttribute("verify", true);
						return "login";
					}
				}else {
					model.addAttribute("message", "Incorrect Password");
					return "login";
				}
			}else {
				model.addAttribute("message", "Entered Email Does not exist");
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Problem Occured While Verifying Login, Please try Again");
			return "login";
		}
	}

	@PostMapping("/register")
	public String register(@ModelAttribute Admin admin,Model model) {
		try {
			if(!adminService.isAdminExist(admin.getEmail())) {
				if(adminService.addAdmin(admin) == 0) {
					model.addAttribute("message", "Registered Successfully");
					model.addAttribute("note", true);
				}else {
					model.addAttribute("message", "Some Problem Occured while Register. Enter a Valid Email or Please Try Again");				
				}
				return "register";
			}else {
				model.addAttribute("message", "Entered Email already Registered");
				return "register";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Problem Occured While Registering , Please try Again");
			return "register";
		}

	}
	
	@GetMapping("/verify")
	public String verifyToken(@RequestParam String token, @RequestParam Long id, Model model) {
		try {
			if(adminService.verifyToken(id,token)) {
				model.addAttribute("message", "Email Verified Sucessfully");
				return "login"; 
			}else {
				model.addAttribute("message", "Cannot verify This Email");
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Problem Occured While Verifying the Email");
			return "login";
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
//
//	@PostMapping("/forgotPassword")
//	public String forgotPassword(@RequestParam String email, Model model) {
//		try {
//			if(adminService.isAdminExist(email)) {
//				String subject = "Reset Password - EmployeeMaster";
//				String body = "To Reset your password click the following link: "
//							+ "http://localhost:8080/resetPassword?id=" + adminService.getAdmin(email).getId();
//				emailService.sendEmail(email, subject, body);
//				model.addAttribute("message", "Reset Password Email send successfully");
//				return "login";
//			}else {
//				model.addAttribute("message", "Entered Email Does not exist");
//				return "forgotPassword";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("message", "Problem Occured While sending Email, Enter a Valid Email or try Again");
//			return "login";
//		}
//	}
//	
//	@PostMapping("/resetPassword")
//	public String resetPassword(@PathVariable Long id, Model model) {
//		try {
//			//
//			model.addAttribute("message", "Entered Email Does not exist");
//			return "forgotPassword";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("message", "Problem Occured While verifying the Link. Please Try Again");
//			return "forgotPassword";
//		}
//	}
//	
}
