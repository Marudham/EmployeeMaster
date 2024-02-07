package com.employeemaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeemaster.entity.ApiResponse;
import com.employeemaster.entity.Employee;
import com.employeemaster.entity.EmployeeRequest;
import com.employeemaster.service.EmployeeRequestService;
import com.employeemaster.service.EmployeeService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/ems/controller")
public class EmployeeRequestController {

	@Autowired
	EmployeeRequestService employeeRequestService;
	
	@Autowired
	EmployeeService employeeService;
	
	ApiResponse response;
	
	@PostMapping("/employeeRequest")
	public ResponseEntity<ApiResponse> employeeRequest(@RequestBody EmployeeRequest employeeRequest, @RequestParam Long id){
		response = new ApiResponse();
		try {
			Employee employee = employeeService.getEmployeeById(id);
			employeeRequest.setAdminId(employee.getAddedByAdminId());
			employeeRequest.setEmployeeId(id);
			employeeRequestService.save(employeeRequest);
			response.setStatus("success");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setStatus("error");
			response.setMessage("Unexpected Error Occured While Saving Employee Request, Please try Again");
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
	@GetMapping("/fetchRequestByEmp")
	public ResponseEntity<ApiResponse> fetchRequestByEmp(@RequestParam Long id){
		response = new ApiResponse();
		try {
			List<EmployeeRequest> employeeRequests = employeeRequestService.fetchRequestsByEmployeeId(id);
			response.setStatus("success");
			response.setEmployeeRequests(employeeRequests);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setStatus("error");
			response.setMessage("Unexpected Error Occured While Retrieving Employee Requests, Please try Again");
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
	@GetMapping("/fetchRequests")
	public ResponseEntity<ApiResponse> fetchRequests(@RequestParam Long id){
		response = new ApiResponse();
		try {
			List<EmployeeRequest> employeeRequests = employeeRequestService.fetchRequestsByAdminId(id);
			response.setStatus("success");
			response.setEmployeeRequests(employeeRequests);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setStatus("error");
			response.setMessage("Unexpected Error Occured While Retrieving Employee Requests, Please try Again");
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
	@GetMapping("/approveRequest")
	public ResponseEntity<ApiResponse> approveRequest(@RequestParam Long id){
		response = new ApiResponse();
		try {
			EmployeeRequest employeeRequest = employeeRequestService.getById(id);
			employeeRequest.setApproved(true);
			employeeRequestService.save(employeeRequest);
			response.setStatus("success");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setStatus("error");
			response.setMessage("Unexpected Error Occured While Updating Employee Request, Please try Again");
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
	@GetMapping("/handleExecute")
	public ResponseEntity<ApiResponse> handleExecute(@RequestParam Long id){
		response = new ApiResponse();
		try {
			if(employeeRequestService.getById(id).isApproved()) {
				employeeRequestService.execute(id);
				EmployeeRequest employeeRequest = employeeRequestService.getById(id);
				employeeRequest.setExecuted(true);
				employeeRequestService.save(employeeRequest);
				response.setStatus("success");
				return ResponseEntity.ok(response);
			}else {
				response.setStatus("not-approved");
				response.setMessage("Employee Request is Not Approved");
				return ResponseEntity.badRequest().body(response);
			}
		} catch (Exception e) {
			response.setStatus("error");
			response.setMessage("Unexpected Error Occured While Executing Employee Request, Please try Again");
			return ResponseEntity.internalServerError().body(response);
		}
	}
}
