package com.assessment.backend.models;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;



import org.hibernate.annotations.GenericGenerator;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Account {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private BigDecimal balance = BigDecimal.ZERO;
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<Transaction> transaction = new HashSet<>();

    public Account() {}

    public Account(Customer customer, BigDecimal balance, LocalDateTime creationDate) {
        this.customer = customer;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public Account(String id, BigDecimal balance, LocalDateTime creationDate, Customer customer,
			Set<Transaction> transaction) {
		this.id = id;
		this.balance = balance;
		this.creationDate = creationDate;
		this.customer = customer;
		this.transaction = transaction;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Account account = (Account) other;

        if (id != null ? !id.equals(account.id) : account.id != null) return false;
        if (balance != null ? !balance.equals(account.balance) : account.balance != null) return false;
        if (!creationDate.equals(account.creationDate)) return false;
        if (customer != null ? !customer.equals(account.customer) : account.customer != null) return false;
        		return true;
        		}

    @Override
    public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (balance != null ? balance.hashCode() : 0);
		result = 31 * result + creationDate.hashCode();
		result = 31 * result + (customer != null ? customer.hashCode() : 0);
		return result;
        		}
        		
}