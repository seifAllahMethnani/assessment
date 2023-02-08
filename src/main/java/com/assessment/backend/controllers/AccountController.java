package com.assessment.backend.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.backend.dto.AccountDTO;
import com.assessment.backend.dto.CreateAccountRequest;
import com.assessment.backend.services.AccountService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/v1/account")
public class AccountController {

	@Autowired
    private final AccountService accountService;
    
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody CreateAccountRequest request){
        return ResponseEntity.ok(accountService.createAccount(request));
    }


}
