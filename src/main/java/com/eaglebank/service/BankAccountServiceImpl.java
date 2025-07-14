package com.eaglebank.service;

import com.eaglebank.mapper.BankAccountMapper;
import com.eaglebank.model.BankAccount;
import com.eaglebank.model.BankAccountResponse;
import com.eaglebank.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountServiceImpl implements BankAccountService {

  @Autowired
  private BankAccountRepository bankAccountRepository;

  @Autowired
  private BankAccountMapper mapper;

  @Override
  public List<BankAccountResponse> fetchBankAccountsByUserId(String userId) {
    List<BankAccount> bankAccounts = bankAccountRepository.findByUserId(userId);
    return bankAccounts.stream()
        .map(acc -> mapper.toDto(acc))
        .toList();
  }
}
