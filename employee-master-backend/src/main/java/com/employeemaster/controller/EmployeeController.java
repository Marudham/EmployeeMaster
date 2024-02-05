package com.employeemaster.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employeemaster.entity.ApiResponse;
import com.employeemaster.entity.Employee;
import com.employeemaster.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/ems/controller")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	ApiResponse response;

	@PostMapping("/addEmployee")
	public ResponseEntity<ApiResponse> addEmployee(@RequestBody Employee employee, @RequestParam Long id) {
		try {
			response = new ApiResponse();
			if(!employeeService.isEmployeeExist(employee.getEmail())) {
				if(!employeeService.isPhoneNoExist(employee.getPhoneNo())) {
					employee.setAddedByAdminId(id);
					employeeService.addEmployee(employee);
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
			response.setMessage("Entered Employee Phone No already exist");
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@GetMapping("/employeeDetails")
	public ResponseEntity<ApiResponse> employeeDetails(@RequestParam Long id) {
		try {
			response = new ApiResponse();
			Employee employee = employeeService.getEmployeeById(id);
			response.setStatus("success");
			response.setEmployee(employee);
			return ResponseEntity.ok(response);
		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected error occured retrieving employee details");
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@PutMapping("/updateEmployee")
	public ResponseEntity<ApiResponse> updateEmployee(@RequestBody Employee employee) {
		response = new ApiResponse();
		try {
			System.out.println(employee);
			employeeService.updateEmployee(employee);
			response.setStatus("success");
			return ResponseEntity.ok(response);
		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus("error");
			response.setMessage("Unexpected error occured updating employee details");
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable long id, Model model,HttpSession session) {
		try {
			if(session.getAttribute("user") != null) {
				employeeService.deleteEmployee(id);
				model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
				model.addAttribute("message", "Employee Deleted successfully");
				return "adminHome";
			}else {
				return "login";
			}
		} catch(Exception e) {
			e.printStackTrace();
			try {
				model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
				model.addAttribute("message", "Problem occured while Deleting Employee,  Try again");
				return "adminHome";
			}catch(Exception e1){
				e1.printStackTrace();
				return "login";
			}	
		}
	}


	@GetMapping("/admin")
	public String admin(Model model,HttpSession session) {
		try {
			if(session.getAttribute("user") != null) {
				model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
				return "adminHome";
			}else {
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
				model.addAttribute("message", "Problem occured while retrieving Employee details,  Try again");
				return "adminHome";
			}catch(Exception e1){
				e1.printStackTrace();
				return "login";
			}	
		}
	}


	@GetMapping("/filter")
	public String filter(Model model, HttpSession session) {
		try {
			if(session.getAttribute("user") != null) {
				model.addAttribute("employees",employeeService.fetchAllEmployee());
				return "filter";
			}else {
				return "login";
			}
		}catch (Exception e) {
			e.printStackTrace();
			try {
				model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
				model.addAttribute("message", "Problem occured while retrieving Employee details,  Try again");
				return "adminHome";
			}catch(Exception e1){
				e1.printStackTrace();
				return "login";
			}	
		}
	}

	@GetMapping("/applyFilter")
	public String applyFilter(@RequestParam("filterBasedOn") String filterBasedOn, 
			@RequestParam("filterValue") String filterValue, 
			Model model, HttpSession session) {
		try {
			if(session.getAttribute("user") != null) {
				model.addAttribute("employees",employeeService.fetchAllEmployee());
				model.addAttribute("filterEmployees", employeeService.filterEmployees(filterBasedOn, filterValue));
				return "filter";
			}else {
				return "login";
			}
		}catch (Exception e) {
			e.printStackTrace();
			try {
				model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
				model.addAttribute("message", "Problem occured while retrieving Employee details,  Try again");
				return "adminHome";
			}catch(Exception e1){
				e1.printStackTrace();
				return "login";
			}	
		}
	}


}
