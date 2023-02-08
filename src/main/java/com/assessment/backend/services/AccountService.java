package com.assessment.backend.services;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.backend.dto.AccountDTO;
import com.assessment.backend.dto.CreateAccountRequest;
import com.assessment.backend.dto.mapper.AccountDTOMapper;
import com.assessment.backend.models.Account;
import com.assessment.backend.models.Customer;
import com.assessment.backend.models.Transaction;
import com.assessment.backend.repositories.AccountRepository;

@Service
public class AccountService {

	
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDTOMapper mapper;
    private final Clock clock;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService,
                          AccountDTOMapper mapper, Clock clock) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.mapper = mapper;
        this.clock = clock;
    }

    public AccountDTO createAccount(CreateAccountRequest createAccountRequest) {
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());

        Account account = new Account(
                customer,
                createAccountRequest.getInitialCredit(),
                getLocalDateTimeNow());

        if (createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = new Transaction(
                    createAccountRequest.getInitialCredit(),
                    getLocalDateTimeNow(),
                    account);

            account.getTransaction().add(transaction);
        }
        return mapper.convert(accountRepository.save(account));
    }

    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant,
                Clock.systemDefaultZone().getZone());
    }

}
