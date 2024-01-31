package com.employeemaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemaster.entity.ForgotPassword;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long>{

	ForgotPassword findByEmail(String email);

}
