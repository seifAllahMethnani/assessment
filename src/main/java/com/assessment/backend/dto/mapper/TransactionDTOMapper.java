package com.assessment.backend.dto.mapper;

import org.springframework.stereotype.Component;

import com.assessment.backend.dto.TransactionDTO;
import com.assessment.backend.models.Transaction;



@Component
public class TransactionDTOMapper {
	
	public TransactionDTO convert(Transaction from) {
		return new TransactionDTO(from.getId(),from.getTransactionType(),from.getAmount(),from.getTransactionDate());
	}

}
