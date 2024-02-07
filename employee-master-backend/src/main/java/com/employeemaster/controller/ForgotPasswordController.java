package com.employeemaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.employeemaster.entity.ForgotPassword;
import com.employeemaster.service.AdminService;
import com.employeemaster.service.EmailService;
import com.employeemaster.service.ForgotPasswordService;
import com.employeemaster.service.PasswordEncryptionService;
import com.employeemaster.service.TokenService;
import com.employeemaster.entity.Admin;
import com.employeemaster.entity.ApiResponse;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/ems/controller")
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
	
	ApiResponse response;
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<ApiResponse> forgotPassword(@RequestBody ForgotPassword forgotPassword) {
		response = new ApiResponse();
		try {
			if(adminService.isAdminExist(forgotPassword.getEmail())) {
				forgotPassword.setEmail(forgotPassword.getEmail());
				forgotPassword.setToken(tokenService.generateUniqueToken());
				forgotPasswordService.save(forgotPassword);
				String to = forgotPassword.getEmail();
				String subject = "Reset Password - EmployeeMaster";
				String body = "To reset your password,"
						+ "<br><a href=\"http://localhost:8080/ems/controller/resetPassword?token="+ forgotPassword.getToken()
						+ "&id=" + forgotPassword.getId() +"\">Click Here.</a>";
				emailService.sendEmail(to, subject, body);
				response.setStatus("success");
				return ResponseEntity.ok(response);
			}else {
				response.setStatus("email-not-exist");
				response.setMessage("Entered Email does not Exist");
				return ResponseEntity.badRequest().body(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected error occured while preparing link, Please try Again");
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
	@GetMapping("/resetPassword")
	public ModelAndView resetPassword(@RequestParam String token, @RequestParam Long id, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			if(forgotPasswordService.verifyToken(token, id)) {
	            modelAndView.setViewName("resetPassword");
	            modelAndView.addObject("forgotPassword", forgotPasswordService.getById(id).getEmail());
	    		return modelAndView;
			}else {
	            modelAndView.setViewName("forgotPassword");
	            modelAndView.addObject("message", "Invalid Verification Link.");
	    		return modelAndView;
			}
		} catch (Exception e) {
			e.printStackTrace();
            modelAndView.setViewName("forgotPassword");
            modelAndView.addObject("message", "Unexpected error has occured while verifying email, Please try again");
    		return modelAndView;
		}
	}
	
	@PostMapping("/resetPassword")
	public ModelAndView resetPassword(@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword, Model model) {
	    ModelAndView modelAndView = new ModelAndView();
	    try {
	        if(password.equals(confirmPassword)) {
	            Admin admin = adminService.getAdmin(email);
	            admin.setPassword(passwordEncryptionService.encryptPassword(password));
	            adminService.updateAdmin(admin);
	            modelAndView.setViewName("forgotPassword");
	            modelAndView.addObject("message", "Password has been Reset Successfully.");
	    	    return modelAndView;
	        } else {
	            modelAndView.setViewName("resetPassword");
	            modelAndView.addObject("forgotPassword", email);
	            modelAndView.addObject("message", "Entered password does not match.");
	    	    return modelAndView;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        modelAndView.setViewName("forgotPassword");
	        modelAndView.addObject("message", "Unexpected error has occured while resetting password, Please try again");
		    return modelAndView;
	    }
	}
	
}










