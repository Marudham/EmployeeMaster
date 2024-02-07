package com.employeemaster.service;

import java.util.List;

import com.employeemaster.entity.AdminActivity;

public interface AdminActivityService {

	void addActivity(AdminActivity adminActivity);

	List<AdminActivity> fetchActivityByAdminId(Long id);

	List<AdminActivity> fetchAllActivity();

}
