package com.eaglebank.controllers;

import com.eaglebank.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserApiController.class)
public class UserApiControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserService userService;

  private static String userJson = """
    "user": "able",
    "address": {
      "line1": "oneline",
      "town": "thistown",
      "county": "thiscounty",
      "postcode": "thispostcode"
    },
    "phoneNumber": "+441234567567",
    "email": "email@address.com"
  """;

  // POST /v1/users
  @Test
  public void testCreateUser_() throws Exception {
    mockMvc.perform(post("/v1/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(userJson))
        .andExpect(status().isForbidden());

  }
}
