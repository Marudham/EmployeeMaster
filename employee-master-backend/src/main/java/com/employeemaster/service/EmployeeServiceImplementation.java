package com.employeemaster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemaster.entity.Employee;
import com.employeemaster.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplementation implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepo;

	@Override
	public boolean isEmployeeExist(String email) {
		if(employeeRepo.findByEmail(email) == null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<Employee> fetchAllEmployee() {
		return employeeRepo.findAll();
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeRepo.save(employee);	
	}

	@Override
	public Employee getEmployeeById(long id) {
		return employeeRepo.findById(id).get();
	}

	@Override
	public void deleteEmployee(long id) {
		employeeRepo.deleteById(id);
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		return employeeRepo.findByEmail(email);
	}

	@Override
	public List<Employee> fetchAllEmployeeByAdminId(long id) {

		return employeeRepo.findByAddedByAdminId(id);
	}

	@Override
	public List<Employee> filterEmployees(String filterBasedOn, String filterValue) throws Exception{
		switch(filterBasedOn) {
		case "firstName" :	return employeeRepo.findAllByFirstName(filterValue);
		case "secondName" :	return employeeRepo.findAllBySecondName(filterValue);
		case "email" :	return employeeRepo.findAllByEmail(filterValue);
		case "phoneNo" :	return employeeRepo.findAllByPhoneNo(filterValue);
		case "dateOfBirth" :	return employeeRepo.findAllByDateOfBirth(filterValue);
		case "address" :	return employeeRepo.findAllByAddress(filterValue);
		case "gender" :	return employeeRepo.findAllByGender(filterValue);
		case "education" :	return employeeRepo.findAllByEducation(filterValue);
		case "percentage10" :	return employeeRepo.findAllByPercentage10(filterValue);
		case "percentage12" :	return employeeRepo.findAllByPercentage12(filterValue);
		case "percentageDeg" :	return employeeRepo.findAllByPercentageDeg(filterValue);
		case "department" :	return employeeRepo.findAllByDepartment(filterValue);
		case "joinDate" :	return employeeRepo.findAllByJoinDate(filterValue);
		case "position" :	return employeeRepo.findAllByPosition(filterValue);
		case "salary" :	return employeeRepo.findAllBySalary(filterValue);
		case "supervisor" :	return employeeRepo.findAllBySupervisor(filterValue);
		case "project" :	return employeeRepo.findAllByProject(filterValue);
		case "status" :	return employeeRepo.findAllByStatus(filterValue);
		default : return employeeRepo.findAll();
		}
	}

	@Override
	public boolean isPhoneNoExist(String phoneNo) {
		if(employeeRepo.findByPhoneNo(phoneNo) == null) {
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepo.save(employee);
	}

}
