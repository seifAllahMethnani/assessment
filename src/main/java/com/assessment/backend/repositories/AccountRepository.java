package com.assessment.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.backend.models.Account;


public interface AccountRepository extends JpaRepository<Account, String> {

}
