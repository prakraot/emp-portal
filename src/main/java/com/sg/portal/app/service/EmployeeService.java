package com.sg.portal.app.service;

import java.util.List;

import com.sg.portal.app.model.Employee;

public interface EmployeeService {

	public Long createEmployee(Employee employee);
	
	public Long deleteEmployee(Long id);
	
	public Long updateEmployee(Long id);

	public Employee getEmployeeById(Long id);

	public List<Employee> getEmployees();

}
