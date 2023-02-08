package com.assessment.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.backend.dto.CustomerDTO;
import com.assessment.backend.dto.mapper.CustomerDTOMapper;
import com.assessment.backend.exceptions.CustomerNotFoundException;
import com.assessment.backend.models.Customer;
import com.assessment.backend.repositories.CustomerRepository;


@Service
public class CustomerService {

	
    private final CustomerRepository customerRepository;
    private final CustomerDTOMapper converter;
    
    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           CustomerDTOMapper converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    public Customer findCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer could not find by id: " + id));

    }

    public CustomerDTO getCustomerById(String customerId) {
        return converter.convertToCustomerDTO(findCustomerById(customerId));
    }

    public List<CustomerDTO> getAllCustomer() {

        return customerRepository.findAll()
                .stream()
                .map(converter::convertToCustomerDTO)
                .collect(Collectors.toList());
    }

}
