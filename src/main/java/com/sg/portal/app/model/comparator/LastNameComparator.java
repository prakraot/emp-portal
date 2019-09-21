package com.sg.portal.app.model.comparator;

import java.util.Comparator;

import com.sg.portal.app.model.Employee;

public class LastNameComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee emp1, Employee emp2) {		
		return emp1.getLastName().compareTo(emp2.getLastName());
	}
}
