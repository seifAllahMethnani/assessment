package com.assessment.backend;

import java.time.Clock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.assessment.backend.models.Customer;
import com.assessment.backend.repositories.CustomerRepository;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;







@SpringBootApplication
public class BackendApplication implements CommandLineRunner{
	
	private final CustomerRepository customerRepository;

	public BackendApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	
	
	


	@Bean
	public Clock clock() {
		return Clock.systemUTC();
	}

	@Override
	public void run(String... args)  {
		Customer customer = customerRepository.save(new Customer("SeifAllah", "Methnani"));
		Customer customer2 = customerRepository.save(new Customer("AhmedYessine", "Methnani"));


	}

}
