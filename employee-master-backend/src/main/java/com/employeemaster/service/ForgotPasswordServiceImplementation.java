package com.employeemaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemaster.entity.ForgotPassword;
import com.employeemaster.repository.ForgotPasswordRepository;

@Service
public class ForgotPasswordServiceImplementation implements ForgotPasswordService{

	@Autowired
	ForgotPasswordRepository forgotPasswordRepo;
	
	@Override
	public void save(ForgotPassword forgotPassword) {
		forgotPasswordRepo.save(forgotPassword);
	}

	@Override
	public boolean verifyToken(String token, Long id) {
		if(forgotPasswordRepo.findById(id).get().getToken().equals(token)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public ForgotPassword getById(Long id) {
		return forgotPasswordRepo.findById(id).get();
	}

	@Override
	public ForgotPassword getByEmail(String email) {
		return forgotPasswordRepo.findByEmail(email);
	}

}
