package com.eaglebank.service;

public interface JwtService {
  String generateToken(String username);

  String extractUsername(String token);

  boolean validateToken(String token);
}
