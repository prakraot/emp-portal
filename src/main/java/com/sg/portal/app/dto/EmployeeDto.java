package com.sg.portal.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class EmployeeDto {
	
	public EmployeeDto(@NotNull @NotBlank String firstName,
			@NotNull @NotBlank String lastName,
			@NotNull @NotBlank String gender, @NotNull @NotBlank String dob,
			@NotNull @NotBlank String department) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
		this.department = department;
	}
	
	@NotBlank(message = "field can't be null or blank")
	private String firstName;
	
	@NotBlank(message = "field can't be null or blank")
	private String lastName;
	
	@NotBlank(message = "field can't be null or blank")
    private String gender;
	
	@NotBlank(message = "field can't be null or blank")
	private String dob;
	
	@NotBlank(message = "field can't be null or blank")
	private String department;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public EmployeeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
