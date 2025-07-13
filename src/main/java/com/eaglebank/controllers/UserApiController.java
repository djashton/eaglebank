package com.eaglebank.controllers;

import com.eaglebank.api.UserApi;
import com.eaglebank.model.CreateUserRequest;
import com.eaglebank.model.ErrorResponse;
import com.eaglebank.model.UpdateUserRequest;
import com.eaglebank.model.UserResponse;
import com.eaglebank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
public class UserApiController implements UserApi {

  @Autowired
  private UserService userService;

  @Override
  public ResponseEntity<UserResponse> createUser(@Valid CreateUserRequest createUserRequest) {
    try {
      UserResponse userResponse = userService.createUser(createUserRequest);
      return ResponseEntity.status(CREATED).body(userResponse);
    } catch (Exception exception) {
      return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new UserResponse());
    }
  }

  @Override
  public ResponseEntity<Void> deleteUserByID(String userId) {
    return UserApi.super.deleteUserByID(userId);
  }

  @Override
  public ResponseEntity<UserResponse> fetchUserByID(String userId) {
    return UserApi.super.fetchUserByID(userId);
  }

  @Override
  public ResponseEntity<UserResponse> updateUserByID(String userId, UpdateUserRequest updateUserRequest) {
    return UserApi.super.updateUserByID(userId, updateUserRequest);
  }
}
