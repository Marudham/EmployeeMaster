package com.employeemaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeeAfter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String secondName;
	private String email;
	private String phoneNo;
	private String dateOfBirth;
	private String address;
	private String gender;
	private String education;
	private String percentage10;
	private String percentage12;
	private String percentageDeg;
	private String joinDate;
	private String department;
	private String position;
	private String salary;
	private String supervisor;
	private String project;
	private String status;
	private long addedByAdminId;
	
	@Override
	public String toString() {
		return "EmployeeBefore [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", email="
				+ email + ", phoneNo=" + phoneNo + ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", gender="
				+ gender + ", education=" + education + ", percentage10=" + percentage10 + ", percentage12="
				+ percentage12 + ", percentageDeg=" + percentageDeg + ", joinDate=" + joinDate + ", department="
				+ department + ", position=" + position + ", salary=" + salary + ", supervisor=" + supervisor
				+ ", project=" + project + ", status=" + status + ", addedByAdminId=" + addedByAdminId + "]";
	}

	public EmployeeAfter() {
	
	}
	
	public EmployeeAfter(Employee employee) {
		this.firstName = employee.getFirstName();
		this.secondName	= employee.getSecondName();
		this.email	= employee.getEmail();
		this.phoneNo	= employee.getPhoneNo();
		this.dateOfBirth	= employee.getDateOfBirth();
		this.address	= employee.getAddress();
		this.gender = employee.getGender();
		this.education	= employee.getEducation();
		this.percentage10	= employee.getPercentage10();
		this.percentage12	= employee.getPercentage12();
		this.percentageDeg	= employee.getPercentageDeg();
		this.joinDate	= employee.getJoinDate();
		this.department	= employee.getDepartment();
		this.position	= employee.getPosition();
		this.salary	= employee.getSalary();
		this.supervisor	= employee.getSupervisor();
		this.project	= employee.getProject();
		this.status	= employee.getStatus();
		this.addedByAdminId	= employee.getAddedByAdminId();
	}

	public EmployeeAfter(long id, String firstName, String secondName, String email, String phoneNo,
			String dateOfBirth, String address, String gender, String education, String percentage10,
			String percentage12, String percentageDeg, String joinDate, String department, String position,
			String salary, String supervisor, String project, String status, long addedByAdminId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.gender = gender;
		this.education = education;
		this.percentage10 = percentage10;
		this.percentage12 = percentage12;
		this.percentageDeg = percentageDeg;
		this.joinDate = joinDate;
		this.department = department;
		this.position = position;
		this.salary = salary;
		this.supervisor = supervisor;
		this.project = project;
		this.status = status;
		this.addedByAdminId = addedByAdminId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPercentage10() {
		return percentage10;
	}

	public void setPercentage10(String percentage10) {
		this.percentage10 = percentage10;
	}

	public String getPercentage12() {
		return percentage12;
	}

	public void setPercentage12(String percentage12) {
		this.percentage12 = percentage12;
	}

	public String getPercentageDeg() {
		return percentageDeg;
	}

	public void setPercentageDeg(String percentageDeg) {
		this.percentageDeg = percentageDeg;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getAddedByAdminId() {
		return addedByAdminId;
	}

	public void setAddedByAdminId(long addedByAdminId) {
		this.addedByAdminId = addedByAdminId;
	}
	

}
