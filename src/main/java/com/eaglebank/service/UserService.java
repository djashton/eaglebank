package com.eaglebank.service;

import com.eaglebank.model.CreateUserRequest;
import com.eaglebank.model.UpdateUserRequest;
import com.eaglebank.model.UserResponse;

public interface UserService {
  UserResponse createUser(CreateUserRequest request);

  UserResponse fetchUserById(String userId);

  UserResponse updateUserById(String userId, UpdateUserRequest request);

  void deleteUser(String userId);
}
