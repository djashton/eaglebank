package com.eaglebank.repository;

import com.eaglebank.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
  List<BankAccount> findByUserId(String userId);
}
