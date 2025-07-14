package com.eaglebank.controllers;

import com.eaglebank.api.AuthApi;
import com.eaglebank.exception.UnauthorisedException;
import com.eaglebank.exception.UserNotFoundException;
import com.eaglebank.model.LoginRequest;
import com.eaglebank.model.User;
import com.eaglebank.service.JwtService;
import com.eaglebank.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthApiController implements AuthApi {
  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  BCryptPasswordEncoder passwordEncoder;

  @Autowired
  JwtService jwtService;

  @Override
  public ResponseEntity<String> login(LoginRequest loginRequest) {
    User user = userDetailsService.findUserByEmail(loginRequest.getEmail());
    if (user == null) {
      throw new UnauthorisedException("Invalid credentials");
    }

    String passwordEncoded = user.getPassword();
    if (passwordEncoded == null ||
        !passwordEncoder.matches(loginRequest.getPassword(), passwordEncoded)) {
      throw new UnauthorisedException("Invalid credentials");
    }

    String jwtToken = jwtService.generateToken(user.getId());
    return ResponseEntity.ok(jwtToken);
  }
}
