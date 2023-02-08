package com.assessment.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class CustomerAccountDTO {
	 private String id;
	    private BigDecimal balance;
	    private Set<TransactionDTO> transactions;
	    private LocalDateTime creationDate;

	    public CustomerAccountDTO(String id, BigDecimal balance, Set<TransactionDTO> transactions, LocalDateTime creationDate) {
	        this.id = id;
	        this.balance = balance != null ? balance : BigDecimal.ZERO;
	        this.transactions = transactions;
	        this.creationDate = creationDate;
	    }

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public BigDecimal getBalance() {
			return balance;
		}

		public void setBalance(BigDecimal balance) {
			this.balance = balance;
		}

		public Set<TransactionDTO> getTransactions() {
			return transactions;
		}

		public void setTransactions(Set<TransactionDTO> transactions) {
			this.transactions = transactions;
		}

		public LocalDateTime getCreationDate() {
			return creationDate;
		}

		public void setCreationDate(LocalDateTime creationDate) {
			this.creationDate = creationDate;
		}


}
