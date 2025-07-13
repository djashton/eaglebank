package com.eaglebank.controllers;

import com.eaglebank.api.TransactionApi;
import com.eaglebank.model.CreateTransactionRequest;
import com.eaglebank.model.ListTransactionsResponse;
import com.eaglebank.model.TransactionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionApiController implements TransactionApi {
  @Override
  public ResponseEntity<TransactionResponse> createTransaction(String accountNumber, CreateTransactionRequest createTransactionRequest) {
    return TransactionApi.super.createTransaction(accountNumber, createTransactionRequest);
  }

  @Override
  public ResponseEntity<TransactionResponse> fetchAccountTransactionByID(String accountNumber, String transactionId) {
    return TransactionApi.super.fetchAccountTransactionByID(accountNumber, transactionId);
  }

  @Override
  public ResponseEntity<ListTransactionsResponse> listAccountTransaction(String accountNumber) {
    return TransactionApi.super.listAccountTransaction(accountNumber);
  }
}
