package com.sg.portal.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


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
	
	@NotBlank(message = "{field.error.message.notnull.blank}")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message="{field.error.message.allows.alphabets}")
	private String firstName;
	
	@NotBlank(message = "{field.error.message.notnull.blank}")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message="{field.error.message.allows.alphabets}")
	private String lastName;
	
	@NotBlank(message = "{field.error.message.notnull.blank}")
	@Pattern(regexp = "^male$|^Male$|^Female$|^female$",  message="{field.error.message.allows.male.female}")
    private String gender;
	
	@NotBlank(message = "{field.error.message.notnull.blank}")
	@Pattern(regexp = "^((0|1)\\d{1})-((0|1|2)\\d{1})-((19|20)\\d{2})", message="{field.error.message.allows.date}")
	private String dob;
	
	@NotBlank(message = "{field.error.message.notnull.blank}")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message="{field.error.message.allows.alphabets}")
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
