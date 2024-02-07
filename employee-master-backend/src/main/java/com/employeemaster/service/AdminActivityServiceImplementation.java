package com.employeemaster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemaster.entity.AdminActivity;
import com.employeemaster.repository.AdminActivityRepository;

@Service
public class AdminActivityServiceImplementation implements AdminActivityService{
	
	@Autowired
	AdminActivityRepository adminActivityRepo;
	
	@Override
	public void addActivity(AdminActivity adminActivity) {
		adminActivityRepo.save(adminActivity);
	}

	@Override
	public List<AdminActivity> fetchActivityByAdminId(Long id) {
		return adminActivityRepo.findByAdminId(id);
	}

	@Override
	public List<AdminActivity> fetchAllActivity() {
		return adminActivityRepo.findAll();
	}

}
