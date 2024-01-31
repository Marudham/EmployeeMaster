package com.employeemaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemaster.entity.SuperAdmin;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Integer>{

	SuperAdmin findByEmail(String email);

}
