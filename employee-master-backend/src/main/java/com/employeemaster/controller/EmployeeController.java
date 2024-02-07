package com.employeemaster.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeemaster.entity.AdminActivity;
import com.employeemaster.entity.ApiResponse;
import com.employeemaster.entity.Employee;
import com.employeemaster.service.AdminActivityService;
import com.employeemaster.service.AdminService;
import com.employeemaster.service.EmployeeService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/ems/controller")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	AdminActivityService adminActivityService;

	ApiResponse response;

	AdminActivity adminActivity;
	
	@PostMapping("/addEmployee")
	public ResponseEntity<ApiResponse> addEmployee(@RequestBody Employee employee, @RequestParam Long id) {
		response = new ApiResponse();
		try {
			adminActivity = new AdminActivity();
			if(!employeeService.isEmployeeExist(employee.getEmail())) {
				if(!employeeService.isPhoneNoExist(employee.getPhoneNo())) {
					employee.setAddedByAdminId(id);
					employeeService.addEmployee(employee);
					adminActivity.setActivity("Add");
					adminActivity.setChangeMade("Added Employee : " + employee.getFirstName() + " " + employee.getSecondName());
					adminActivity.setEmployeeId(employee.getId());
					adminActivity.setAdmin(adminService.getAdminById(id));
					adminActivity.setTimestamp(LocalDateTime.now());
					adminActivityService.addActivity(adminActivity);
					response.setStatus("success");
					return ResponseEntity.ok(response);
				}else {
					response.setStatus("phoneNo-exist");
					response.setMessage("Entered Employee Phone No already exist");
					return ResponseEntity.badRequest().body(response);
				}
			}else {
				response.setStatus("email-exist");
				response.setMessage("Entered Employee Email already exist");
				return ResponseEntity.badRequest().body(response);
			}

		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected error occured while Adding employee, Please try again");
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@GetMapping("/employeeDetails")
	public ResponseEntity<ApiResponse> employeeDetails(@RequestParam Long id) {
		response = new ApiResponse();
		try {
			adminActivity = new AdminActivity();
			Employee employee = employeeService.getEmployeeById(id);
			response.setStatus("success");
			response.setEmployee(employee);
			return ResponseEntity.ok(response);
		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected error occured while retrieving employee details");
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@PutMapping("/updateEmployee")
	public ResponseEntity<ApiResponse> updateEmployee(@RequestBody Employee employee, @RequestParam String changeMade) {
		response = new ApiResponse();
		try {
			employeeService.updateEmployee(employee);
		
			adminActivity = new AdminActivity();
			adminActivity.setAdmin(adminService.getAdminById(employee.getAddedByAdminId()));
			adminActivity.setActivity("Update");
			adminActivity.setTimestamp(LocalDateTime.now());
			if(changeMade != "") {
				adminActivity.setChangeMade("Updated " + changeMade + " of : " + employee.getFirstName() + " " + employee.getSecondName());
			}else {
				adminActivity.setChangeMade("Updated Employee : " + employee.getFirstName() + " " + employee.getSecondName());
			}
			adminActivity.setEmployeeId(employee.getId());
			adminActivityService.addActivity(adminActivity);
			response.setStatus("success");
			return ResponseEntity.ok(response);
		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected error occured while updating employee details");
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@GetMapping("/deleteEmployee")
	public ResponseEntity<ApiResponse> deleteEmployee(@RequestParam Long id) {
		response = new ApiResponse();
		try {
			Employee employee = employeeService.getEmployeeById(id);
			employeeService.deleteEmployee(id);
			adminActivity = new AdminActivity();
			adminActivity.setAdmin(adminService.getAdminById(employee.getAddedByAdminId()));
			adminActivity.setActivity("Delete");
			adminActivity.setTimestamp(LocalDateTime.now());
			adminActivity.setChangeMade("Deleted Employee : " + employee.getFirstName() + " " + employee.getSecondName());
			adminActivityService.addActivity(adminActivity);
			response.setStatus("success");
			return ResponseEntity.ok(response);
		} catch(Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected error occured while  Deleting Employee");
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@GetMapping("/applyFilter")
	public ResponseEntity<ApiResponse> applyFilter(@RequestParam("filterBasedOn") String filterBasedOn, 
			@RequestParam("filterValue") String filterValue, @RequestParam Long id) {
		response = new ApiResponse();
		try {
			List<Employee> employees = employeeService.filterEmployees(filterBasedOn, filterValue);
			List<Employee> filteredEmployees = new ArrayList<>();
			for(Employee emp : employees) {
				if(emp.getAddedByAdminId() == id) {
					filteredEmployees.add(emp);
				}
			}
			response.setStatus("success");
			response.setEmployeeList(filteredEmployees);
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected error occured while Applying filter on Employees");
			return ResponseEntity.internalServerError().body(response);
		}
	}

}
