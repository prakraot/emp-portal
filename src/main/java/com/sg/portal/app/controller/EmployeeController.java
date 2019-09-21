package com.sg.portal.app.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sg.portal.app.dto.EmployeeDto;
import com.sg.portal.app.model.Employee;
import com.sg.portal.app.model.comparator.FirstNameComparator;
import com.sg.portal.app.model.comparator.LastNameComparator;
import com.sg.portal.app.service.EmployeeService;
import com.sg.portal.app.validator.DateValidator;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	DateValidator dateValidator;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllEmployees(@RequestParam(required = false) String sort) {
		Comparator<Employee> comparator= new FirstNameComparator();
		if(sort !=null && sort.equals("lastName")){
			 comparator= new LastNameComparator();
		}
		
		List<Employee> result = employeeService.getEmployees();	
		if(result==null || result.isEmpty()){
			return ResponseEntity.status(HttpStatus.OK).body(
					new LinkedHashMap<String, Object>() {	
						private static final long serialVersionUID = 1L;	{
							
							put("Message", "No Data Available");
						}
					});
		}
		result.sort(comparator);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.map(result, new TypeToken<List<EmployeeDto>>() {}.getType()));

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getEmployee(@PathVariable Long id) {
		Employee employee=employeeService.getEmployeeById(id);
		if(employee==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new LinkedHashMap<String, Object>() {	
						private static final long serialVersionUID = 1L;

					{
							put("Id", id);
							put("Message", "Emp Not Present");
						}
					});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.map(employee, new TypeToken<EmployeeDto>() {}.getType()));

	}

	@RequestMapping(method = RequestMethod.POST, consumes = {"application/JSON"})	
	public ResponseEntity<LinkedHashMap<String, Object>> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		if(!dateValidator.isValidDob(employeeDto.getDob())){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new LinkedHashMap<String, Object>() {					
						private static final long serialVersionUID = 1L;
					{
							
							put("Status", "Failed");
							put("Message", "Invalid dob");
						}
					});
		}
		Employee employee=mapper.map(employeeDto, new TypeToken<Employee>(){}.getType());
		Long id=employeeService.createEmployee(employee);	
		return ResponseEntity.status(HttpStatus.OK).body(
				new LinkedHashMap<String, Object>() {					
					private static final long serialVersionUID = 1L;

				{
						put("Id", id);
						put("Status", "Created Successfully");
					}
				});

	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HashMap<String, String>> handleException(MethodArgumentNotValidException ex) {
		HashMap<String, String> errors= new HashMap<String, String>();
		for(FieldError error: ex.getBindingResult().getFieldErrors()){
			errors.put(error.getField(),error.getDefaultMessage());		
			}		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

}


