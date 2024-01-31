package com.employeemaster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemaster.repository.AdminRepository;
import com.employeemaster.entity.Admin;

@Service
public class AdminServiceImplementation implements AdminService{

	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	PasswordEncryptionService passwordEncryptionService;
	
	@Override
	public boolean isAdminExist(String email) {
		if(adminRepo.findByEmail(email) == null) {
			return false;
		}else{
			return true;
		}
	}

	@Override
	public int addAdmin(Admin admin) {
		try {
			String token = tokenService.generateUniqueToken();
			
			admin.setEmailVerificationToken(token);
			admin.setPassword(passwordEncryptionService.encryptPassword(admin.getPassword()));
			adminRepo.save(admin);
			String subject = "Account Verification - Employee Management System";
			String body = "To verify your account, click the following link: "
	                + "http://localhost:8080/verify?token=" + admin.getEmailVerificationToken() 
	                + "&id=" + admin.getId();
			emailService.sendEmail(admin.getEmail(), subject, body);
			return 0;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public boolean isValidAdmin(String email, String password) {
		if(passwordEncryptionService.checkPassword(password, adminRepo.findByEmail(email).getPassword())) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Admin getAdmin(String email) {
		return adminRepo.findByEmail(email);
	}

	@Override
	public Admin getAdminById(long id) {
		return adminRepo.findById(id).get();
	}

	@Override
	public boolean verifyToken(Long id, String token) {
		Admin admin = adminRepo.findByIdAndEmailVerificationToken(id, token);
		
		if(admin != null) {
			admin.setVerified(true);
			adminRepo.save(admin);
			return true;
		}
		return false;
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminRepo.findAll();
	}

	@Override
	public void updateAdmin(Admin admin) {
		adminRepo.save(admin);
	}

	@Override
	public void deleteAdmin(Long id) {
		adminRepo.deleteById(id);
	}

	@Override
	public void sendVerificationEmail(String email) throws Exception{
		String token = tokenService.generateUniqueToken();
		Admin admin = adminRepo.findByEmail(email);
		admin.setEmailVerificationToken(token);
		adminRepo.save(admin);
		String subject = "Account Verification - Employee Management System";
		String body = "To verify your account, click the following link: "
                + "http://localhost:8080/verify?token=" + admin.getEmailVerificationToken() 
                + "&id=" + admin.getId();
		emailService.sendEmail(admin.getEmail(), subject, body);
	}

}
