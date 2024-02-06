package com.employeemaster.controller;

import java.time.LocalDateTime;
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
import com.employeemaster.entity.EmployeeAfter;
import com.employeemaster.entity.EmployeeBefore;
import com.employeemaster.service.AdminActivityService;
import com.employeemaster.service.AdminService;
import com.employeemaster.service.EmployeeAfterService;
import com.employeemaster.service.EmployeeBeforeService;
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
	
	@Autowired
	EmployeeBeforeService employeeBeforeService;
	
	@Autowired
	EmployeeAfterService employeeAfterService;

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
					adminActivity.setAdmin(adminService.getAdminById(id));
					EmployeeAfter after = new EmployeeAfter(employee);
					employeeAfterService.save(after);
					adminActivity.setAfter(after);
					adminActivity.setBefore(null);
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
			EmployeeBefore before = new EmployeeBefore(employeeService.getEmployeeById(employee.getId()));
			EmployeeAfter after = new EmployeeAfter(employee);
			employeeBeforeService.save(before);
			employeeAfterService.save(after);
			
			employeeService.updateEmployee(employee);
		
			adminActivity = new AdminActivity();
			adminActivity.setAdmin(adminService.getAdminById(employee.getAddedByAdminId()));
			adminActivity.setActivity("Update");
			adminActivity.setBefore(before);
			adminActivity.setAfter(after);
			adminActivity.setTimestamp(LocalDateTime.now());
			adminActivity.setChangeMade(changeMade);
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
			employeeService.deleteEmployee(id);
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
			@RequestParam("filterValue") String filterValue) {
		response = new ApiResponse();
		try {
			List<Employee> filteredEmployees = employeeService.filterEmployees(filterBasedOn, filterValue);
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
