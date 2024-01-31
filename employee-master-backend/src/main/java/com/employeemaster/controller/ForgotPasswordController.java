package com.employeemaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employeemaster.entity.ForgotPassword;
import com.employeemaster.service.AdminService;
import com.employeemaster.service.EmailService;
import com.employeemaster.service.ForgotPasswordService;
import com.employeemaster.service.PasswordEncryptionService;
import com.employeemaster.service.TokenService;
import com.employeemaster.entity.Admin;


@Controller
public class ForgotPasswordController {

	@Autowired
	ForgotPasswordService forgotPasswordService;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	PasswordEncryptionService passwordEncryptionService;
	
	@PostMapping("/forgotPassword")
	public String forgotPassword(@ModelAttribute ForgotPassword forgotPassword, Model model) {
		try {
			if(adminService.isAdminExist(forgotPassword.getEmail())) {
				forgotPassword.setToken(tokenService.generateUniqueToken());
				forgotPasswordService.save(forgotPassword);
				String to = forgotPassword.getEmail();
				String subject = "Reset Password - EmployeeMaster";
				String body = "To reset your password, click the following link:"
						+ "<br>" + "http://localhost:8080/resetPassword?token="+ forgotPassword.getToken()
						+ "&id=" + forgotPassword.getId();
				emailService.sendEmail(to, subject, body);
				model.addAttribute("message", "Reset Password Email has been sent.");
				return "forgotPassword";
			}else {
				model.addAttribute("message", "Entered Email does not exist.");
				return "forgotPassword";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Unexpected error has occured while preparing email, Please try again");
			return "forgotPassword";
		}
	}
	
	@GetMapping("/resetPassword")
	public String resetPassword(@RequestParam String token, @RequestParam Long id, Model model) {
		try {
			if(forgotPasswordService.verifyToken(token, id)) {
				model.addAttribute("forgotPassword", forgotPasswordService.getById(id).getEmail());
				return "resetPassword";
			}else {
				model.addAttribute("message", "Invalid Verification Link.");
				return "forgotPassword";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Unexpected error has occured while verifying email, Please try again");
			return "forgotPassword";
		}
	}
	
	@PostMapping("/resetPassword")
	public String resetPassword(@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword, Model model) {
		try {
			if(password.equals(confirmPassword)) {
				Admin admin = adminService.getAdmin(email);
				admin.setPassword(passwordEncryptionService.encryptPassword(password));
				adminService.updateAdmin(admin);
				model.addAttribute("message", "Password has been Reset Successfully.");
				return "login";
			}else {
				model.addAttribute("forgotPassword", email);
				model.addAttribute("message", "Entered password does not match.");
				return "resetPassword";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Unexpected error has occured while resetting password, Please try again");
			return "login";
		}
	}
	
}










