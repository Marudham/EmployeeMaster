package com.employeemaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employeemaster.service.SuperAdminService;
import com.employeemaster.entity.Admin;

import jakarta.servlet.http.HttpSession;

import com.employeemaster.service.AdminService;
import com.employeemaster.service.EmailService;

@Controller
public class SuperAdminController {

	@Autowired
	SuperAdminService superAdminService;

	@Autowired
	AdminService adminService;

	@Autowired
	EmailService emailService;

	@PostMapping("/superAdminLogin")
	public String superAdminLogin(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
		try {
			if(superAdminService.isSuperAdminExist(email)) {
				if(superAdminService.isValidSuperAdmin(email, password)) {
					session.setAttribute("superAdmin", email);
					model.addAttribute("admins", adminService.getAllAdmins());
					return "superAdmin";
				}else {
					model.addAttribute("message", "Incorrect Password");
					return "superAdminLogin";
				}
			}else {
				model.addAttribute("message", "Entered Email Does not exist");
				return "superAdminLogin";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Problem occured while Verifying the Login, Please try again");
			return "superAdminLogin";
		}
	}

	@GetMapping("/approve/{id}")
	public String approve(@PathVariable Long id, Model model, HttpSession session) {
		try {
			if(session.getAttribute("superAdmin") != null) {
				Admin admin = adminService.getAdminById(id);
				admin.setAdmin(true);
				adminService.updateAdmin(admin);
				String subject = "Approval Status - EmployeeMaster";
				String body = "You Have Been Approved By the SUPER_ADMIN";
				emailService.sendEmail(admin.getEmail(), subject, body);
				model.addAttribute("admins", adminService.getAllAdmins());
				model.addAttribute("message", "Approve Status has been Updated");
				return "superAdmin";
			}else {
				return "superAdminLogin";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			try {
				model.addAttribute("message", "Some problem occured while updating approve status");
				model.addAttribute("admins", adminService.getAllAdmins());
				return "superAdmin";
			}catch(Exception e1){
				e1.printStackTrace();
				return "superAdminLogin";
			}		
		}
	}

	@GetMapping("/disapprove/{id}")
	public String disapprove(@PathVariable Long id, Model model, HttpSession session) {
		try {
			if(session.getAttribute("superAdmin") != null) {
				Admin admin = adminService.getAdminById(id);
				admin.setAdmin(false);
				adminService.updateAdmin(admin);
				String subject = "Approval Status - EmployeeMaster";
				String body = "You Have Been Disapproved By the SUPER_ADMIN";
				emailService.sendEmail(admin.getEmail(), subject, body);
				model.addAttribute("admins", adminService.getAllAdmins());
				model.addAttribute("message", "Approve Status has been Updated");
				return "superAdmin";
			}else {
				return "superAdminLogin";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			try {
				model.addAttribute("message", "Some problem occured while updating approve status");
				model.addAttribute("admins", adminService.getAllAdmins());
				return "superAdmin";
			}catch(Exception e1){
				e1.printStackTrace();
				return "superAdminLogin";
			}	
		}
	}

	@GetMapping("/sendMail/{id}")
	public String sendMail(@PathVariable Long id, Model model, HttpSession session) {
		try {
			if(session.getAttribute("superAdmin") != null) {
				adminService.sendVerificationEmail(adminService.getAdminById(id).getEmail());
				model.addAttribute("message", "Verification Email has been Sent");
				model.addAttribute("admins", adminService.getAllAdmins());
				return "superAdmin";
			}else {
				return "superAdminLogin";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			try {
				model.addAttribute("message", "Problem Occured While sending Email, Enter a Valid Email or try Again");
				model.addAttribute("admins", adminService.getAllAdmins());
				return "superAdmin";
			}catch(Exception e1){
				e1.printStackTrace();
				return "superAdminLogin";
			}	
		}
	}

	@GetMapping("/deleteAdmin/{id}")
	public String deleteAdmin(@PathVariable Long id, Model model, HttpSession session) {
		try {
			if(session.getAttribute("superAdmin") != null) {
				adminService.deleteAdmin(id);
				model.addAttribute("message", "Admin has been Deleted");
				model.addAttribute("admins", adminService.getAllAdmins());
				return "superAdmin";
			}else {
				return "superAdminLogin";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			try {
				model.addAttribute("message", "Problem Occured While Deleting the Admin, Please try again");
				model.addAttribute("admins", adminService.getAllAdmins());
				return "superAdmin";
			}catch(Exception e1){
				e1.printStackTrace();
				return "superAdminLogin";
			}	
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
