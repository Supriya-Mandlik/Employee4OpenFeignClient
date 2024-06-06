package com.Employees.openfeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Employees.response.AddressResponse;

//name should be instance name registered with eureka. and contextPath.
@FeignClient(name = "Address" , path = "/Address/api/")   // name should be instance name registered with eureka.
public interface AddressClient {   // proxy
	
	@GetMapping("/address/{employeeId}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int id);
		

}
