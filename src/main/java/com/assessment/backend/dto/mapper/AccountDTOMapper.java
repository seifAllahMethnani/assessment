package com.assessment.backend.dto.mapper;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.assessment.backend.dto.AccountDTO;
import com.assessment.backend.models.Account;


@Component
public class AccountDTOMapper {
	 private final CustomerDTOMapper customerDTOMapper;
	    private final TransactionDTOMapper transactionDTOMapper;

	    public AccountDTOMapper(CustomerDTOMapper customerDTOMapper,
	    		TransactionDTOMapper transactionDTOMapper) {
	        this.customerDTOMapper = customerDTOMapper;
	        this.transactionDTOMapper = transactionDTOMapper;
	    }

	    public AccountDTO convert(Account from) {
	        return new AccountDTO(from.getId(),
	                from.getBalance(),
	                from.getCreationDate(),
	                customerDTOMapper.convertToAccountCustomer(Optional.ofNullable(from.getCustomer())),
	                Objects.requireNonNull(from.getTransaction())
	                        .stream()
	                        .map(transactionDTOMapper::convert)
	                        .collect(Collectors.toSet()));
	    }

}
