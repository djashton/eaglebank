package com.eaglebank.controllers;

import com.eaglebank.api.AccountApi;
import com.eaglebank.model.BankAccountResponse;
import com.eaglebank.model.CreateBankAccountRequest;
import com.eaglebank.model.ListBankAccountsResponse;
import com.eaglebank.model.UpdateBankAccountRequest;
import com.eaglebank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountApiController implements AccountApi {
  @Override
  public ResponseEntity<BankAccountResponse> createAccount(CreateBankAccountRequest createBankAccountRequest) {
    return AccountApi.super.createAccount(createBankAccountRequest);
  }

  @Override
  public ResponseEntity<Void> deleteAccountByAccountNumber(String accountNumber) {
    return AccountApi.super.deleteAccountByAccountNumber(accountNumber);
  }

  @Override
  public ResponseEntity<BankAccountResponse> fetchAccountByAccountNumber(String accountNumber) {
    return AccountApi.super.fetchAccountByAccountNumber(accountNumber);
  }

  @Override
  public ResponseEntity<ListBankAccountsResponse> listAccounts() {
    return AccountApi.super.listAccounts();
  }

  @Override
  public ResponseEntity<BankAccountResponse> updateAccountByAccountNumber(String accountNumber, UpdateBankAccountRequest updateBankAccountRequest) {
    return AccountApi.super.updateAccountByAccountNumber(accountNumber, updateBankAccountRequest);
  }
}
