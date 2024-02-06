package com.employeemaster.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ApiResponse {
	
    private String status;
    private String message;
    private List<Employee> employeeList;
    private List<Admin> adminList;
    private List<AdminActivity> adminActivities;
    private Employee employee;
    private Admin admin;
    
    @Override
	public String toString() {
		return "ApiResponse [status=" + status + ", message=" + message + ", employeeList=" + employeeList
				+ ", adminList=" + adminList + ", adminActivities=" + adminActivities + ", employee=" + employee
				+ ", admin=" + admin + "]";
	}

	public ApiResponse() {	
	}

	

	public ApiResponse(String status, String message, List<Employee> employeeList, List<Admin> adminList,
			List<AdminActivity> adminActivities, Employee employee, Admin admin) {
		super();
		this.status = status;
		this.message = message;
		this.employeeList = employeeList;
		this.adminList = adminList;
		this.adminActivities = adminActivities;
		this.employee = employee;
		this.admin = admin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public List<Admin> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<AdminActivity> getAdminActivities() {
		return adminActivities;
	}

	public void setAdminActivities(List<AdminActivity> adminActivities) {
		this.adminActivities = adminActivities;
	}
 
	
}

