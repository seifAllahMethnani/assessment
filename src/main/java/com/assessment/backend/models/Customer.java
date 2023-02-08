package com.assessment.backend.models;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private String surname;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Account> accounts;
    public Customer() {}

    public Customer(String name, String surname) {
        this.id = "";
        this.name = name;
        this.surname = surname;
        this.accounts = new HashSet<>();
    }
    public Customer(String id,String name, String surname,  Set<Account> accounts) {
        this.id = "";
        this.name = name;
        this.surname = surname;
        this.accounts = new HashSet<>();
    }
	
	
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
	
	
	
	@Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Customer customer = (Customer) other;
        if (id == null) {
            if (customer.id != null)
                return false;
        } else if (!id.equals(customer.id))
            return false;
        if (name == null) {
            if (customer.name != null)
                return false;
        } else if (!name.equals(customer.name))
            return false;
        if (surname == null) {
            if (customer.surname != null)
                return false;
        } else if (!surname.equals(customer.surname))
            return false;
        if (accounts == null) {
            if (customer.accounts != null)
                return false;
        } else if (!accounts.equals(customer.accounts))
            return false;
        return true;
    }
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = id != null ? id.hashCode() : 0;
        result = prime * result + (name != null ? name.hashCode() : 0);
        result = prime * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

}
