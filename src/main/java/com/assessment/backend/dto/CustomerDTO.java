package com.assessment.backend.dto;

import java.util.Set;

public class CustomerDTO {
	private String id;
    private String name;
    private String surname;
    private Set<CustomerAccountDTO> accounts;

    public CustomerDTO(String id, String name, String surname, Set<CustomerAccountDTO> accounts) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.accounts = accounts;
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

    public Set<CustomerAccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<CustomerAccountDTO> accounts) {
        this.accounts = accounts;
    }
}
