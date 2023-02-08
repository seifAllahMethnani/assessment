package com.assessment.backend.account.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import com.assessment.backend.account.TestSupport;
import com.assessment.backend.dto.AccountCustomerDTO;
import com.assessment.backend.dto.AccountDTO;
import com.assessment.backend.dto.CreateAccountRequest;
import com.assessment.backend.dto.TransactionDTO;
import com.assessment.backend.dto.mapper.AccountDTOMapper;
import com.assessment.backend.exceptions.CustomerNotFoundException;
import com.assessment.backend.models.Account;
import com.assessment.backend.models.Customer;
import com.assessment.backend.models.Transaction;
import com.assessment.backend.models.TransactionType;
import com.assessment.backend.repositories.AccountRepository;
import com.assessment.backend.services.AccountService;
import com.assessment.backend.services.CustomerService;

class AccountServiceTest extends TestSupport {

    private AccountRepository accountRepository;
    private CustomerService customerService;
    private AccountDTOMapper converter;

    private AccountService service;

    private final Customer customer = generateCustomer();
    private final AccountCustomerDTO customerDTO = new AccountCustomerDTO("customer-id",
            "customer-name",
            "customer-surname");

    @BeforeEach
    public void setup() {
        accountRepository = mock(AccountRepository.class);
        customerService = mock(CustomerService.class);
        converter = mock(AccountDTOMapper.class);
        Clock clock = mock(Clock.class);

        service = new AccountService(accountRepository, customerService, converter, clock);

        when(clock.instant()).thenReturn(getCurrentInstant());
        when(clock.getZone()).thenReturn(Clock.systemDefaultZone().getZone());
    }
    
    @Test
    public void testCreateAccount_whenCustomerIdExistsAndInitialCreditMoreThanZero_shouldCreateAccountWithTransaction() {

        CreateAccountRequest request = generateCreateAccountRequest(100);

        Account account = generateAccount(100);
        Transaction transaction = new Transaction(null, TransactionType.INITIAL, request.getInitialCredit(), getLocalDateTime(), account);
        account.getTransaction().add(transaction);

        TransactionDTO transactionDTO = new TransactionDTO("", TransactionType.INITIAL, new BigDecimal(100), getLocalDateTime());
        AccountDTO expected = new AccountDTO("account-id", new BigDecimal(100), getLocalDateTime(), customerDTO, Set.of(transactionDTO));

        when(customerService.findCustomerById("customer-id")).thenReturn(customer);
        when(accountRepository.save(account)).thenReturn(account);

        when(converter.convert(account)).thenReturn(expected);

        AccountDTO result = service.createAccount(request);

        assertEquals(result, expected);

    }

    @Test
    public void testCreateAccount_whenCustomerIdExistsAndInitialCreditIsZero_shouldCreateAccountWithoutTransaction() {
        CreateAccountRequest request = generateCreateAccountRequest(0);

        Account account = generateAccount(0);
        AccountDTO expected = new AccountDTO("account-id", BigDecimal.ZERO, getLocalDateTime(), customerDTO, Set.of());

        when(customerService.findCustomerById("customer-id")).thenReturn(customer);
        when(accountRepository.save(account)).thenReturn(account);
        when(converter.convert(account)).thenReturn(expected);

        AccountDTO result = service.createAccount(request);

        assertEquals(result, expected);
    }

    @Test
    public void testCreateAccount_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException() {
        CreateAccountRequest request = generateCreateAccountRequest(0);

        when(customerService.findCustomerById("customer-id")).thenThrow(new CustomerNotFoundException("test-exception"));

        assertThrows(CustomerNotFoundException.class,
                () -> service.createAccount(request));

        verify(customerService).findCustomerById(request.getCustomerId());
        verifyNoInteractions(accountRepository);
        verifyNoInteractions(converter);
    }

    private Account generateAccount(int balance) {
        return new Account("", new BigDecimal(balance), getLocalDateTime(), customer, new HashSet<>());
    }


}