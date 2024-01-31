package com.employeemaster.service;

import com.employeemaster.entity.ForgotPassword;

public interface ForgotPasswordService {

	void save(ForgotPassword forgotPassword);

	boolean verifyToken(String token, Long id);

	ForgotPassword getById(Long id);

	ForgotPassword getByEmail(String email);

	
}
