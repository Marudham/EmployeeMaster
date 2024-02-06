package com.employeemaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemaster.entity.AdminActivity;

public interface AdminActivityRepository extends JpaRepository<AdminActivity, Long>{

	List<AdminActivity> findByAdminId(Long id);

}
