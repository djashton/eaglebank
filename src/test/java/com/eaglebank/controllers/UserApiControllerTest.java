package com.eaglebank.controllers;

import com.eaglebank.config.SecurityConfig;
import com.eaglebank.filter.JwtRequestFilter;
import com.eaglebank.model.CreateUserRequest;
import com.eaglebank.model.UserResponse;
import com.eaglebank.service.JwtService;
import com.eaglebank.service.UserDetailsService;
import com.eaglebank.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserApiController.class)
public class UserApiControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @MockBean
  private JwtService jwtService;

  @MockBean
  private UserDetailsService userDetailsService;

  private static String userJson = """
    {
    "user": "able",
    "address": {
      "line1": "oneline",
      "town": "thistown",
      "county": "thiscounty",
      "postcode": "thispostcode"
    },
    "phoneNumber": "+441234567567",
    "email": "email@address.com"
    }
  """;

  // POST /v1/users
  @Test
  public void testCreateUser_IsCreated() throws Exception {

    when(userService.createUser(any(CreateUserRequest.class)))
        .thenReturn(new UserResponse());
    mockMvc.perform(post("/v1/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(userJson))
        .andExpect(status().isCreated());

  }

}
