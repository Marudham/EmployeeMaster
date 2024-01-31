package com.employeemaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemaster.entity.SuperAdmin;
import com.employeemaster.repository.SuperAdminRepository;

@Service
public class SuperAdminService {

	@Autowired
	SuperAdminRepository repo;

	@Autowired
	PasswordEncryptionService passwordEncryptionService;
	
	public SuperAdmin getSuperAdmin(String email) {
		return	repo.findByEmail(email);
	}
	
	public boolean isSuperAdminExist(String email) {
		if(repo.findByEmail(email) == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isValidSuperAdmin(String email, String password) {
		if(passwordEncryptionService.checkPassword(password, repo.findByEmail(email).getPassword())) {
			return true;
		}else {
			return false;
		}
	}
}
