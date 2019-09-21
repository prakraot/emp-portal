# Project Description
This is a spring boot project to demonstrate spring boot capabilities via a MVC based sample application.</br>
Using : </br>
- Spring boot version 2.1.3.RELEASE
- Java Version 1.8
- H2 DB Version 1.4.19
# prerequisites tools
 - Eclipse or Spring Tool Suite (Preferred)
 - Java 8
 - Maven
 - Postman
# Installation
clone or download the repository, unzip and import the project in Spring Tool Suite
# Usage
Run the class EmpPortalApplication as SpringBootApplication

# API End Points
**Use Case #1 : Create an employee**
- Http POST  http://localhost:8080/api/employee </br>
Body Param: </br>
      {	</br>
	        "firstName":"Prakash",</br>
	        "lastName":"Rao",</br>
	        "gender":"Male",</br>
	        "dob":"19/11/1978",</br>
	        "department":"IT"</br>
	      } 
				</br>
			
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
    




