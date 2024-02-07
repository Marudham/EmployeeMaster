package com.employeemaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeeRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long employeeId;
	private Long adminId;
	private String field;
	private String value;
	private boolean isApproved;
	private boolean isExecuted;
	
	
	@Override
	public String toString() {
		return "EmployeeRequest [id=" + id + ", employeeId=" + employeeId + ", adminId=" + adminId + ", field=" + field
				+ ", value=" + value + ", isApproved=" + isApproved + ", isExecuted=" + isExecuted + "]";
	}

	public EmployeeRequest() {
		
	}

	public EmployeeRequest(Long id, Long employeeId, Long adminId, String field, String value, boolean isApproved,
			boolean isExecuted) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.adminId = adminId;
		this.field = field;
		this.value = value;
		this.isApproved = isApproved;
		this.isExecuted = isExecuted;
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

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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
