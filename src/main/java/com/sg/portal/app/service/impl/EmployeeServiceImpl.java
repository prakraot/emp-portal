package com.sg.portal.app.service.impl;

import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.portal.app.repository.EmployeeRepository;
import com.sg.portal.app.model.Employee;
import com.sg.portal.app.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Long createEmployee(Employee employee) {
		Employee emp = employeeRepository.save(employee);
		return emp.getId();
	}

	@Override
	public Long deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		return id;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).isPresent() ? employeeRepository.findById(id).get() : null;
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Long updateEmployee(Long id) {
		// TODO Auto-generated method stub
		return 1L;
	}

}
