package com.employeemaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemaster.entity.EmployeeLogin;

public interface EmployeeLoginRepository extends JpaRepository<EmployeeLogin, Long>{

	EmployeeLogin findByEmail(String email);

}
