package com.sg.portal.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sg.portal.app.controller.EmployeeController;
import com.sg.portal.app.dto.EmployeeDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmpControllerInputValidationTests {

	@Autowired
	EmployeeController controller;

	@Autowired
    private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
		assertThat(restTemplate).isNotNull();
		
	}
	
	
	@Test
    public void testCreateEmployeeFirstNameNullOrEmpty() throws Exception {
		EmployeeDto employeeDto= new EmployeeDto("", "Rao", "Male", "19-11-1978", "IT");
        ResponseEntity<Object> responseEntity=this.restTemplate.postForEntity("http://localhost:" + port + "/api/employee", employeeDto,
        		Object.class);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        HashMap<String, String> errors=(HashMap<String, String>)responseEntity.getBody();
        assertThat(errors.isEmpty()).isFalse();
        assertThat(errors.get("firstName")).isEqualTo("Field can't be null or blank");   
        
        employeeDto= new EmployeeDto(null, "Rao", "Male", "19-11-1978", "IT");
        responseEntity=this.restTemplate.postForEntity("http://localhost:" + port + "/api/employee", employeeDto,Object.class);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        errors=(HashMap<String, String>)responseEntity.getBody();
        assertThat(errors.isEmpty()).isFalse();
        assertThat(errors.get("firstName")).isEqualTo("Field can't be null or blank");       
        
    }
	@Test
    public void testCreateEmployeeLastNameNullOrEmpty() throws Exception {
		EmployeeDto employeeDto= new EmployeeDto("Prakash", "", "Male", "19-11-1978", "IT");
        ResponseEntity<Object> responseEntity=this.restTemplate.postForEntity("http://localhost:" + port + "/api/employee", employeeDto,
        		Object.class);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        HashMap<String, String> errors=(HashMap<String, String>)responseEntity.getBody();
        assertThat(errors.isEmpty()).isFalse();
        assertThat(errors.get("lastName")).isEqualTo("Field can't be null or blank");   
        
        employeeDto= new EmployeeDto("Prakash", null, "Male", "19-11-1978", "IT");
        responseEntity=this.restTemplate.postForEntity("http://localhost:" + port + "/api/employee", employeeDto,Object.class);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        errors=(HashMap<String, String>)responseEntity.getBody();
        assertThat(errors.isEmpty()).isFalse();
        assertThat(errors.get("lastName")).isEqualTo("Field can't be null or blank");     
        
    }
	
	@Test
    public void testPostEmployeeGenderNullOrEmpty() throws Exception {
		EmployeeDto employeeDto= new EmployeeDto("Prakash", "Rao", "", "19-11-1978", "IT");
        ResponseEntity<Object> responseEntity=this.restTemplate.postForEntity("http://localhost:" + port + "/api/employee", employeeDto,
        		Object.class);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        HashMap<String, String> errors=(HashMap<String, String>)responseEntity.getBody();
        assertThat(errors.isEmpty()).isFalse();
        assertThat(errors.get("gender")).isEqualTo("Field allows only Male|male|Female|female");     
        
        employeeDto=  new EmployeeDto("Prakash", "Rao", null, "19-11-1978", "IT");
        responseEntity=this.restTemplate.postForEntity("http://localhost:" + port + "/api/employee", employeeDto,Object.class);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        errors=(HashMap<String, String>)responseEntity.getBody();
        assertThat(errors.isEmpty()).isFalse();
        assertThat(errors.get("gender")).isEqualTo("Field can't be null or blank");               
        
    }
	
	
	@Test
    public void testPostEmployeeDobNullOrEmpty() throws Exception {
		EmployeeDto employeeDto= new EmployeeDto("Prakash", "Rao", "Male", "", "IT");
        ResponseEntity<Object> responseEntity=this.restTemplate.postForEntity("http://localhost:" + port + "/api/employee", employeeDto,
        		Object.class);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        HashMap<String, String> errors=(HashMap<String, String>)responseEntity.getBody();
        assertThat(errors.isEmpty()).isFalse();
        assertThat(errors.get("dob")).isEqualTo("Field must be in dd-mm-yyyy format");    
        
        employeeDto= new EmployeeDto("Prakash", "Rao", "Male", null, "IT");
        responseEntity=this.restTemplate.postForEntity("http://localhost:" + port + "/api/employee", employeeDto,Object.class);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        errors=(HashMap<String, String>)responseEntity.getBody();
        assertThat(errors.isEmpty()).isFalse();
        assertThat(errors.get("dob")).isEqualTo("Field can't be null or blank");        
        
    }
	
	@Test
    public void testPostEmployeeDeptNullOrEmpty() throws Exception {
		EmployeeDto employeeDto= new EmployeeDto("Prakash", "Rao", "Male", "19-11-1978", "");
        ResponseEntity<Object> responseEntity=this.restTemplate.postForEntity("http://localhost:" + port + "/api/employee", employeeDto,
        		Object.class);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        HashMap<String, String> errors=(HashMap<String, String>)responseEntity.getBody();
        assertThat(errors.isEmpty()).isFalse();
        assertThat(errors.get("department")).isEqualTo("Field can't be null or blank");  
        
        employeeDto= new EmployeeDto("Prakash", "Rao", "Male", "19-11-1978", null);
        responseEntity=this.restTemplate.postForEntity("http://localhost:" + port + "/api/employee", employeeDto,Object.class);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        errors=(HashMap<String, String>)responseEntity.getBody();
        assertThat(errors.isEmpty()).isFalse();
        assertThat(errors.get("department")).isEqualTo("Field can't be null or blank");       
        
    }
	
	@Test
    public void testPostEmployeeInvalidNames() throws Exception {
		EmployeeDto employeeDto= new EmployeeDto("Prakash123", "123Rao", "Male", "19-11-1978", "IT");
        ResponseEntity<Object> responseEntity=this.restTemplate.postForEntity("http://localhost:" + port + "/api/employee", employeeDto,
        		Object.class);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        HashMap<String, String> errors=(HashMap<String, String>)responseEntity.getBody();
        assertThat(errors.isEmpty()).isFalse();
        assertThat(errors.get("firstName")).isEqualTo("Field allows only alphabets"); 
        assertThat(errors.get("lastName")).isEqualTo("Field allows only alphabets"); 
        
    }
	
	@Test
    public void testPostEmployeeInvalidGender() throws Exception {
		EmployeeDto employeeDto= new EmployeeDto("Prakash", "Rao", "XYZ", "19-11-1978", "IT");
        ResponseEntity<Object> responseEntity=this.restTemplate.postForEntity("http://localhost:" + port + "/api/employee", employeeDto,
        		Object.class);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        HashMap<String, String> errors=(HashMap<String, String>)responseEntity.getBody();
        assertThat(errors.isEmpty()).isFalse();
        assertThat(errors.get("gender")).isEqualTo("Field allows only Male|male|Female|female");
               
    }
	
	@Test
    public void testPostEmployeeInvalidDob() throws Exception {
		EmployeeDto employeeDto= new EmployeeDto("Prakash", "Rao", "Male", "19/11/1978", "IT");
        ResponseEntity<Object> responseEntity=this.restTemplate.postForEntity("http://localhost:" + port + "/api/employee", employeeDto,
        		Object.class);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        HashMap<String, String> errors=(HashMap<String, String>)responseEntity.getBody();
        assertThat(errors.isEmpty()).isFalse();
        assertThat(errors.get("dob")).isEqualTo("Field must be in dd-mm-yyyy format");
               
    }
}
