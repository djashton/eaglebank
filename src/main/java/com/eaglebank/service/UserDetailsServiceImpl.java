package com.eaglebank.service;

import com.eaglebank.exception.UserNotFoundException;
import com.eaglebank.model.User;
import com.eaglebank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException(
            "User with email " + email + " cannot be found"));
  }

  @Override
  public User findUserById(String id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(
            "User with id " + id + " cannot be found"));
  }
}
