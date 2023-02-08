package com.assessment.backend.dto.mapper;

import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.assessment.backend.dto.CustomerAccountDTO;
import com.assessment.backend.models.Account;


@Component
public class CustomerAccountDTOMapper {
	private final TransactionDTOMapper transactionDTOMapper;

    public CustomerAccountDTOMapper(TransactionDTOMapper mapper) {
        this.transactionDTOMapper = mapper;
    }

    public CustomerAccountDTO convert(Account from) {
        return new CustomerAccountDTO(
                Objects.requireNonNull(from.getId()),
                from.getBalance(),
                from.getTransaction()
                        .stream()
                        .map(transactionDTOMapper::convert)
                        .collect(Collectors.toSet()),
                from.getCreationDate());
    }

}
