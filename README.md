# Project Description
This is a spring boot project to demonstrate spring boot capabilities via a MVC based sample application.</br>
Using : </br>
- Spring boot version 2.1.3.RELEASE
- Java Version 1.8
- H2 DB Version 1.4.19
- http://localhost:8080/actuator/info

# Prerequisites Tools
 - Eclipse or Spring Tool Suite (Preferred)
 - Java 8
 - Maven
 - Postman

# Installation
  - clone this repository or download the zip file, unzip and import the project in Spring Tool Suite.
  - right click pom.xml and run as maven install  (This will build the project & also invoke Test cases)

# Usage
- Run the class EmpPortalApplication as a SpringBootApplication
- Call the GET API to create some Employees in the DB
- To verify the saved data in H2 DB access broswer console: http://localhost:8080/h2-console
- run the query:  select  * EMPLOYEE;

# Logging
- logs are printed in logs/server.log  (configurable in application.properties)

# API End Points
**Use Case #1 : Create an employee**
- Http POST  http://localhost:8080/api/employee </br>
- Sample Request#1 body param: </br>
      {	</br>
	        "firstName":"Prakash",</br>
	        "lastName":"Rao",</br>
	        "gender":"Male",</br>
	        "dob":"19/11/1978",</br>
	        "department":"IT"</br>
	      } 
				</br>
	
	Reponse: </br>
	{ </br>
           "Id": 1,</br>
           "Status": "Created Successfully"</br>
        }</br>
	
- Sample Request#2 body param: </br>
      {	</br>
	        "firstName":"Prakash",</br>
	        "lastName":"123",</br>
	        "gender":"xyz",</br>
	        "dob":"19/11/1978",</br>
	        "department":"IT"</br>
	      } </br>
	Response: </br>
	{</br>
    		"lastName": "Field allows only alphabets",</br>
    		"gender": "Field allows only Male|male|Female|female",</br>
    		"dob": "Field must be in dd-mm-yyyy format"</br>
	}</br>
			
**Use Case #2 : Get an employee**
- Http GET  http://localhost:8080/api/employee/{Id} </br>
Here id is the unique id of the employee (returned in the create emp response) </br>

**Use Case #3.1 : Get all the employees**
- Http GET  http://localhost:8080/api/employee/all </br>

**Use Case #3.2 : Get all the employee details in ascending order by first name**
- Http GET  http://localhost:8080/api/employee/all?sort=firstName </br>
Note: this is the default ordering if "sort" optional param is not passed

**Use Case #3.3 : Get all the employee details in ascending order by last name**
- Http GET  http://localhost:8080/api/employee/all?sort=lastName </br>
    
# Validations
- Field validations are performed through javax.validation f/w on EmployeeDto bean
- Validation messages are picked from messages.properties
- validations include not-null, not-empty checks, dob format, dob not greater than current date etc.

# Monitoring
http://localhost:8080/actuator/health




