package com.assessment.backend.dto.mapper;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.assessment.backend.dto.AccountCustomerDTO;
import com.assessment.backend.dto.CustomerDTO;
import com.assessment.backend.models.Customer;


@Component
public class CustomerDTOMapper {

	private final CustomerAccountDTOMapper customerAccountDTOMapper;

    public CustomerDTOMapper(CustomerAccountDTOMapper mapper) {
        this.customerAccountDTOMapper = mapper;
    }

    public AccountCustomerDTO convertToAccountCustomer(Optional<Customer> from) {
        return from.map(f -> new AccountCustomerDTO(f.getId(), f.getName(), f.getSurname())).orElse(null);
    }

    public CustomerDTO convertToCustomerDTO(Customer from) {
        return new CustomerDTO(
                from.getId(),
                from.getName(),
                from.getSurname(),
                from.getAccounts()
                        .stream()
                        .map(customerAccountDTOMapper::convert)
                        .collect(Collectors.toSet()));

    }
}
