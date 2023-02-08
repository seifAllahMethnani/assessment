package com.assessment.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.backend.models.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
