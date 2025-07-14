package com.eaglebank.service;

import com.eaglebank.model.BankAccountResponse;

import java.util.List;

public interface BankAccountService {
  List<BankAccountResponse> fetchBankAccountsByUserId(String userId);
}
