package com.eaglebank.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountApiController.class)
public class AccountAPIControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testCreateAccount_NotImplemented() throws Exception {
    mockMvc.perform(post("/v1/accounts")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"abc\",\"accountType\":\"personal\"}"))
        .andExpect(status().isForbidden());
  }

  @Test
  public void testGetAllAccounts_NotImplemented() throws Exception {
    mockMvc.perform(get("/v1/accounts"))
        .andExpect(status().isForbidden());
  }

  @Test
  public void testGetAccount_NotImplemented() throws Exception {
    mockMvc.perform(get("/v1/accounts/01234567")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}"))
        .andExpect(status().isForbidden());
  }

  @Test
  public void testDeleteAccount_NotImplemented() throws Exception {
    mockMvc.perform(delete("/v1/accounts/01234567")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{}"))
        .andExpect(status().isForbidden());
  }

  @Test
  public void testUpdateAccount_NotImplemented() throws Exception {
    mockMvc.perform(patch("/v1/accounts/01234567")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}"))
        .andExpect(status().isForbidden());
  }
}
