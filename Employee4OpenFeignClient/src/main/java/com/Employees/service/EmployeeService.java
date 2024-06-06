package com.Employees.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.Employees.entity.Employee;
import com.Employees.openfeignClients.AddressClient;
import com.Employees.repository.EmployeeRepository;
import com.Employees.response.AddressResponse;
import com.Employees.response.EmployeeResponse;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository EmployeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WebClient webClient;
	
	@Autowired      
	private RestTemplate restTemplate;
	
	@Autowired
	private AddressClient addressClient;
	
		
	public EmployeeResponse getEmployeeById(int id) {
		
		
		
		Employee employee = EmployeeRepo.findById(id).get();  
		
		 EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		
		 	
       ResponseEntity<AddressResponse> addressResponseEntity = addressClient.getAddressByEmployeeId(id);
     
       AddressResponse addressResponse = addressResponseEntity.getBody();
       
     employeeResponse.setAddressResponse(addressResponse); 
	 
	 return employeeResponse ;
	}
		 
		 
		 private AddressResponse callToAddressServiceUsingWebClient(int id) {
			 return webClient
                     .get()
                     .uri("/address/"+id)
                     .retrieve()
                     .bodyToMono(AddressResponse.class)
                     .block(); 
		 }
		
		 
		 private AddressResponse callingAddressServiceUsingRestTemplate(int id) {
		
			 return restTemplate.getForObject("http://Address/Address/api/address/{id}", AddressResponse.class, id);
		 }
		
				
	}
        
	

