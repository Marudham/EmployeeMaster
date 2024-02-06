package com.employeemaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemaster.entity.EmployeeAfter;
import com.employeemaster.repository.EmployeeAfterRepository;

@Service
public class EmployeeAfterService {

	@Autowired
	EmployeeAfterRepository employeeAfterRepo;

	public void save(EmployeeAfter after) {
		employeeAfterRepo.save(after);
	}
}
