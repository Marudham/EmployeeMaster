package com.employeemaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeeLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String verificationToken;
	
	@Override
	public String toString() {
		return "EmployeeLogin [id=" + id + ", email=" + email + ", verificationToken=" + verificationToken + "]";
	}

	public EmployeeLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeLogin(Long id, String email, String verificationToken) {
		super();
		this.id = id;
		this.email = email;
		this.verificationToken = verificationToken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}
	
}
