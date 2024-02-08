package com.employeemaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EmployeeRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Employee employee;
	private Long adminId;
	private String field;
	private String value;
	private boolean isApproved;
	private boolean isExecuted;

	@Override
	public String toString() {
		return "EmployeeRequest [id=" + id + ", employee=" + employee + ", adminId=" + adminId + ", field=" + field
				+ ", value=" + value + ", isApproved=" + isApproved + ", isExecuted=" + isExecuted + "]";
	}

	public EmployeeRequest() {
		
	}
	
	public EmployeeRequest(Long id, Employee employee, Long adminId, String field, String value,
			boolean isApproved, boolean isExecuted) {
		super();
		this.id = id;
		this.employee = employee;
		this.adminId = adminId;
		this.field = field;
		this.value = value;
		this.isApproved = isApproved;
		this.isExecuted = isExecuted;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public boolean isExecuted() {
		return isExecuted;
	}

	public void setExecuted(boolean isExecuted) {
		this.isExecuted = isExecuted;
	}
	
}
