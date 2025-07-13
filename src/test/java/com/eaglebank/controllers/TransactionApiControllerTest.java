package com.eaglebank.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionApiController.class)
public class TransactionApiControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testCreateTransaction_Unauthorised() throws Exception {
    //POST /v1/accounts/{accountNumber}/transactions
    mockMvc.perform(post("/v1/accounts/01234567/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}"))
        .andExpect(status().isForbidden());

  }

  @Test
  public void testFetchTransactionByID_Unauthorised() throws Exception {
    //GET /v1/accounts/{accountNumber}/transactions/{transactionId}
    mockMvc.perform(get("/v1/accounts/01234567/transactions/tan-1"))
        .andExpect(status().isUnauthorized());
  }


  @Test
  public void testListAccountTransactions_Unauthorised() throws Exception {
    // GET /v1/accounts/{accountNumber}/transactions
    mockMvc.perform(get("/v1/accounts/01234567/transactions"))
        .andExpect(status().isUnauthorized());
  }
}
