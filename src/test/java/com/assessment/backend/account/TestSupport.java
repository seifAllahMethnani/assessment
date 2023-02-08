package com.assessment.backend.account;



import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

import com.assessment.backend.dto.CreateAccountRequest;
import com.assessment.backend.models.Customer;

public class TestSupport {

    public static final String CUSTOMER_API_ENDPOINT = "/v1/customer/";
    public static final String ACCOUNT_API_ENDPOINT = "/v1/account/";

    public Instant getCurrentInstant() {
        String instantExpected = "2021-06-15T10:15:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), Clock.systemDefaultZone().getZone());

        return Instant.now(clock);
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofInstant(getCurrentInstant(), Clock.systemDefaultZone().getZone());
    }

    public Customer generateCustomer() {
        return new Customer("customer-id", "customer-name", "customer-surname", Set.of());
    }

    public CreateAccountRequest generateCreateAccountRequest(int initialCredit) {
        return generateCreateAccountRequest("customer-id", initialCredit);
    }

    public CreateAccountRequest generateCreateAccountRequest(String customerId, int initialCredit) {
        return new CreateAccountRequest(customerId, new BigDecimal(initialCredit));
    }
}
