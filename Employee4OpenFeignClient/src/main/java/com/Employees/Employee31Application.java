package com.Employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Employee31Application {

	public static void main(String[] args) {
		SpringApplication.run(Employee31Application.class, args);
	}

}
