package com.employeemaster.service;

import java.util.List;

import com.employeemaster.entity.Admin;

public interface AdminService {

	boolean isAdminExist(String email);

	void addAdmin(Admin admin) throws Exception;

	boolean isValidAdmin(String email, String password);

	Admin getAdmin(String email);

	Admin getAdminById(long id);

	boolean verifyToken(Long id, String token);

	List<Admin> getAllAdmins();
	
	void updateAdmin(Admin admin);
	
	void deleteAdmin(Long id);

	void sendVerificationEmail(String email) throws Exception;
}
