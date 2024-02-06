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
	@ManyToOne
	private EmployeeBefore before;
	@ManyToOne
	private EmployeeAfter after;
	@ManyToOne
	private Admin admin;
	
	@Override
	public String toString() {
		return "AdminActivity [id=" + id + ", activity=" + activity + ", changeMade=" + changeMade + ", timestamp="
				+ timestamp + ", before=" + before + ", after=" + after + ", admin=" + admin + "]";
	}

	public AdminActivity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminActivity(Long id, String activity, String changeMade, LocalDateTime timestamp, EmployeeBefore before,
			EmployeeAfter after, Admin admin) {
		super();
		this.id = id;
		this.activity = activity;
		this.changeMade = changeMade;
		this.timestamp = timestamp;
		this.before = before;
		this.after = after;
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getChangeMade() {
		return changeMade;
	}

	public void setChangeMade(String changeMade) {
		this.changeMade = changeMade;
	}

	public EmployeeBefore getBefore() {
		return before;
	}

	public void setBefore(EmployeeBefore before) {
		this.before = before;
	}

	public EmployeeAfter getAfter() {
		return after;
	}

	public void setAfter(EmployeeAfter after) {
		this.after = after;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	
}
