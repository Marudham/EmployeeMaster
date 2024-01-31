package com.employeemaster.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemaster.entity.EmployeeLogin;
import com.employeemaster.repository.EmployeeLoginRepository;

@Service
public class EmployeeLoginService {

	@Autowired
	EmployeeLoginRepository employeeLoginRepo;

	public void addEmployeeLogin(EmployeeLogin employeeLogin) {
		employeeLoginRepo.save(employeeLogin);
	}

	public boolean isExist(String email) {
		if(employeeLoginRepo.findByEmail(email) == null) {
			return false;
		}else {
			return true;
		}
	}

	public int generateVerificationCode() {
		return 100000 + new Random().nextInt(900000);
	}

	public boolean verifyToken(String token, Long id) {
		
		if(employeeLoginRepo.findById(id).get().getVerificationToken().equals(token)) {
			return true;
		}else {
			return false;
		}
	}

	public EmployeeLogin getEmployeeLogin(Long id) {
		return employeeLoginRepo.findById(id).get();
	} 

}
