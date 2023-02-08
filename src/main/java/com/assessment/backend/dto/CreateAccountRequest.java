package com.assessment.backend.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateAccountRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "CustomerId must not be empty")
    private String customerId;

    @Min(value = 0, message = "Initial Credit value must not be negative value")
    private BigDecimal initialCredit;

    public CreateAccountRequest() {
    	
    }
    
    public CreateAccountRequest(String customerId, BigDecimal initialCredit) {
        this.customerId = customerId;
        this.initialCredit = initialCredit;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getInitialCredit() {
        return initialCredit;
    }
}


