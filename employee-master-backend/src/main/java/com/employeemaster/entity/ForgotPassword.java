package com.employeemaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ForgotPassword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String token;
	
	@Override
	public String toString() {
		return "ForgotPassword [id=" + id + ", email=" + email + ", token=" + token + "]";
	}

	public ForgotPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForgotPassword(Long id, String email, String token) {
		super();
		this.id = id;
		this.email = email;
		this.token = token;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
