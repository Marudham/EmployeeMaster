package com.employeemaster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemaster.entity.Employee;
import com.employeemaster.entity.EmployeeRequest;
import com.employeemaster.repository.EmployeeRequestRepository;

@Service
public class EmployeeRequestService {

	@Autowired
	EmployeeRequestRepository employeeRequestRepo;

	@Autowired
	EmployeeService employeeService;

	public void save(EmployeeRequest employeeRequest) {
		employeeRequestRepo.save(employeeRequest);
	}

	public List<EmployeeRequest> fetchRequestsByEmployeeId(Long id) {
		return employeeRequestRepo.findByEmployeeId(id);
	}

	public List<EmployeeRequest> fetchRequestsByAdminId(Long id) {
		return employeeRequestRepo.findByAdminId(id);
	}

	public EmployeeRequest getById(Long id) {
		return employeeRequestRepo.findById(id).get();
	}

	public void execute(Long id) throws Exception{
		EmployeeRequest employeeRequest = getById(id);
		Long empId = employeeRequest.getEmployeeId();
		String field = employeeRequest.getField();
		String value = employeeRequest.getValue();
		Employee employee = employeeService.getEmployeeById(empId);
		switch(field) {
		case "firstName" :	{
			employee.setFirstName(value);
			break;
		}
		case "secondName" :	{
			employee.setSecondName(value);
			break;
		}
		case "email" :	{
			employee.setEmail(value);
			break;
		}
		case "phoneNo" :	{
			employee.setPhoneNo(value);
			break;
		}
		case "dateOfBirth" :	{
			employee.setDateOfBirth(value);
			break;
		}
		case "address" :	{
			employee.setAddress(value);
			break;
		}
		case "gender" :	{
			employee.setGender(value);
			break;
		}
		case "education" : {
			employee.setEducation(value);
			break;
		}
		case "percentage10" :	{
			employee.setPercentage10(value);
			break;
		}
		case "percentage12" :	{
			employee.setPercentage12(value);
			break;
		}
		case "percentageDeg" :	{
			employee.setPercentageDeg(value);
			break;
		}
		case "department" :	{
			employee.setEducation(value);
			break;
		}
		case "joinDate" :	{
			employee.setJoinDate(value);
			break;
		}
		case "position" :	{
			employee.setPosition(value);
			break;
		}
		case "salary" :	{
			employee.setSalary(value);
			break;
		}
		case "supervisor" :	{
			employee.setSupervisor(value);
			break;
		}
		case "project" :	{
			employee.setProject(value);
			break;
		}
		case "status" :	{
			employee.setStatus(value);
			break;
		}
		}
		employeeService.updateEmployee(employee);
	}
}