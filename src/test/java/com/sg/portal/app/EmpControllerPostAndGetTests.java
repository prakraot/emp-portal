package com.sg.portal.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

public class EmpControllerPostAndGetTests {

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
    public void testPostAndGetEmployee() throws Exception {
		EmployeeDto employeeDto= new EmployeeDto("Prakash", "Rao", "Male", "19-11-1978", "IT");
        ResponseEntity<LinkedHashMap> responseEntity=this.restTemplate.postForEntity("http://localhost:" + port + "/api/employee", employeeDto,
        		LinkedHashMap.class);
        
		            
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        LinkedHashMap<String, Object> responseBody=responseEntity.getBody();
        assertThat(responseBody.isEmpty()).isFalse();
        assertThat(responseBody.get("Status")).isEqualTo("Created Successfully");    
        Integer Id=(Integer)responseBody.get("Id");
       
        ResponseEntity<Object> getResponseEntity=this.restTemplate.getForEntity("http://localhost:" + port + "/api/employee/"+Id, Object.class);
        LinkedHashMap<String, String> getResponseBody=(LinkedHashMap<String, String>)getResponseEntity.getBody();
        
        assertThat(getResponseBody.isEmpty()).isFalse();
        assertThat(getResponseBody.get("firstName")).isNotEmpty();   
        assertThat(getResponseBody.get("lastName")).isNotEmpty();   
        assertThat(getResponseBody.get("dob")).isNotEmpty();   
        assertThat(getResponseBody.get("department")).isNotEmpty();   
        
    }	
	
	@Test
    public void testGetEmployeeAll() throws Exception {
		ResponseEntity<Object> getResponseEntity=this.restTemplate.getForEntity("http://localhost:" + port + "/api/employee/all", Object.class);
        List<LinkedHashMap<String, String>> list=( List<LinkedHashMap<String, String>>)getResponseEntity.getBody();
        assertThat(!list.isEmpty()).isTrue();
        for(LinkedHashMap<String, String> employeeDto: list){
        	assertThat(employeeDto.get("firstName")).isNotEmpty();
        	assertThat(employeeDto.get("lastName")).isNotEmpty();
        	assertThat(employeeDto.get("dob")).isNotEmpty();
        	assertThat(employeeDto.get("gender")).isNotEmpty();
        	assertThat(employeeDto.get("department")).isNotEmpty();
        }
        
    }
	
}
