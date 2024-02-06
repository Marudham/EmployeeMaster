package com.employeemaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemaster.entity.EmployeeBefore;
import com.employeemaster.repository.EmployeeBeforeRepository;

@Service
public class EmployeeBeforeService {

	@Autowired
	EmployeeBeforeRepository employeeBeforeRepo;

	public void save(EmployeeBefore before) {
		employeeBeforeRepo.save(before);
	}
	
}
