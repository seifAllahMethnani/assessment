package com.assessment.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class AccountDTO {
    private String id;
    private BigDecimal balance;
    private LocalDateTime creationDate;
    private AccountCustomerDTO customer;
    private Set<TransactionDTO> transactions;

    public AccountDTO(String id, BigDecimal balance, LocalDateTime creationDate, AccountCustomerDTO customer, Set<TransactionDTO> transactions) {
        this.id = id;
        this.balance = balance;
        this.creationDate = creationDate;
        this.customer = customer;
        this.transactions = transactions;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public AccountCustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(AccountCustomerDTO customer) {
        this.customer = customer;
    }

    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
