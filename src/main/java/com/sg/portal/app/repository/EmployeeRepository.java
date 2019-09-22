package com.sg.portal.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sg.portal.app.model.Employee;



public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
