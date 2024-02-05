package com.employeemaster.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class AdminActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String changeMade;
	private LocalDateTime timestamp;
	@OneToOne
	private Employee before;
	@OneToOne
	private Employee after;
	@OneToOne
	private Admin admin;

	@Override
	public String toString() {
		return "AdminActivity [id=" + id + ", changeMade=" + changeMade + ", timestamp=" + timestamp + ", before="
				+ before + ", after=" + after + ", admin=" + admin + "]";
	}

	public AdminActivity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminActivity(Long id, String changeMade, LocalDateTime timestamp, Employee before, Employee after,
			Admin admin) {
		super();
		this.id = id;
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

	public Employee getBefore() {
		return before;
	}

	public void setBefore(Employee before) {
		this.before = before;
	}

	public Employee getAfter() {
		return after;
	}

	public void setAfter(Employee after) {
		this.after = after;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
}
