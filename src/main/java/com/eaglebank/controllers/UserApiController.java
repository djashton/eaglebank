package com.eaglebank.controllers;

import com.eaglebank.api.UserApi;
import com.eaglebank.exception.ForbiddenException;
import com.eaglebank.model.CreateUserRequest;
import com.eaglebank.model.UpdateUserRequest;
import com.eaglebank.model.User;
import com.eaglebank.model.UserResponse;
import com.eaglebank.service.UserDetailsService;
import com.eaglebank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class UserApiController implements UserApi {

  @Autowired
  private UserService userService;

  @Override
  public ResponseEntity<UserResponse> createUser(@Valid CreateUserRequest createUserRequest) {
    UserResponse userResponse = userService.createUser(createUserRequest);
    return ResponseEntity.status(CREATED).body(userResponse);
  }

  @Override
  public ResponseEntity<Void> deleteUserByID(String userId) {
    return UserApi.super.deleteUserByID(userId);
  }

  @Override
  public ResponseEntity<UserResponse> fetchUserByID(String userId) {
    UserResponse userResponse = getUserForId(userId);
    return ResponseEntity.ok(userResponse);
  }

  @Override
  public ResponseEntity<UserResponse> updateUserByID(String userId, UpdateUserRequest updateUserRequest) {
    UserResponse user = getUserForId(userId);

    UserResponse userResponse = userService.updateUserById(userId, updateUserRequest);
    return ResponseEntity.ok(userResponse);
  }

  private UserResponse getUserForId(String userId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User loggedInUser = (User) authentication.getPrincipal();

    // I do not agree with this scenario, but it is checking that
    // a user exists before checking that the user is allowed access to the user
    UserResponse userResponse = userService.fetchUserById(userId);

    if (!loggedInUser.getId().equals(userId)) {
      throw new ForbiddenException("Forbidden");
    }
    return userResponse;
  }

}
