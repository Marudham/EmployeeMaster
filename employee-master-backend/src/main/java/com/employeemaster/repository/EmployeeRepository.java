package com.employeemaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employeemaster.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Employee findByEmail(String email);

	List<Employee> findByAddedByAdminId(long id);

	@Query(value = "SELECT * FROM employee e Where e.department=:department AND e.position=:position", nativeQuery = true)
	List<Employee> findByDepartmentPosition(String department, String position);

	List<Employee> findAllByFirstName(String firstName);

	List<Employee> findAllBySecondName(String secondName);

	List<Employee> findAllByEmail(String email);

	List<Employee> findAllByPhoneNo(String phoneNo);

	List<Employee> findAllByAddress(String address);

	List<Employee> findAllByGender(String gender);

	List<Employee> findAllByJoinDate(String joinDate);

	List<Employee> findAllByPosition(String position);

	List<Employee> findAllByDepartment(String department);

	List<Employee> findAllBySalary(String salary);

	List<Employee> findAllBySupervisor(String supervisor);

	List<Employee> findAllByProject(String project);

	List<Employee> findAllByEducation(String education);

	List<Employee> findAllByStatus(String status);

	Employee findByPhoneNo(String phoneNo);

}
