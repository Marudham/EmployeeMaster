package com.employeemaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemaster.entity.Admin;


public interface AdminRepository  extends JpaRepository<Admin, Long>{

	Admin findByEmail(String email);

	Admin findByIdAndEmailVerificationToken(Long id, String token);

}
