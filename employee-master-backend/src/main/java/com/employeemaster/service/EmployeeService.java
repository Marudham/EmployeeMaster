package com.employeemaster.service;

import java.util.List;

import com.employeemaster.entity.Employee;

public interface EmployeeService {

	boolean isEmployeeExist(String email);

	List<Employee> fetchAllEmployee();

	void addEmployee(Employee employee);

	Employee getEmployeeById(long id);

	void deleteEmployee(long id);

	Employee getEmployeeByEmail(String email);

	List<Employee> fetchAllEmployeeByAdminId(long id);

	List<Employee> filterEmployees(String department, String position);

	boolean isPhoneNoExist(String phoneNo);

	void updateEmployee(Employee employee);

}
