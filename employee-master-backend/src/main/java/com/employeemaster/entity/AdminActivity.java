package com.employeemaster.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class AdminActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String activity;
	private String changeMade;
	private LocalDateTime timestamp;
	private Long employeeId;
	
	@ManyToOne
	private Admin admin;
	@Override
	public String toString() {
		return "AdminActivity [id=" + id + ", activity=" + activity + ", changeMade=" + changeMade + ", timestamp="
				+ timestamp + ", employeeId=" + employeeId + ", admin=" + admin + "]";
	}
	
	public AdminActivity() {
	}
	
	public AdminActivity(Long id, String activity, String changeMade, LocalDateTime timestamp, Long employeeId,
			Admin admin) {
		super();
		this.id = id;
		this.activity = activity;
		this.changeMade = changeMade;
		this.timestamp = timestamp;
		this.employeeId = employeeId;
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getChangeMade() {
		return changeMade;
	}

	public void setChangeMade(String changeMade) {
		this.changeMade = changeMade;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
}
