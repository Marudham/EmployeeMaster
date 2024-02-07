package com.employeemaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemaster.entity.EmployeeRequest;

public interface EmployeeRequestRepository extends JpaRepository<EmployeeRequest, Long>{

	List<EmployeeRequest> findByEmployeeId(Long id);

	List<EmployeeRequest> findByAdminId(Long id);

}
