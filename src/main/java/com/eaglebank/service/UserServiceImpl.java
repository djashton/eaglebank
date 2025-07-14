package com.eaglebank.service;

import com.eaglebank.mapper.CreateUserRequestMapper;
import com.eaglebank.mapper.UpdateUserRequestMapper;
import com.eaglebank.mapper.UserResponseMapper;
import com.eaglebank.model.CreateUserRequest;
import com.eaglebank.model.UpdateUserRequest;
import com.eaglebank.model.User;
import com.eaglebank.model.UserResponse;
import com.eaglebank.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

  Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


  @Autowired
  private CreateUserRequestMapper createUserMapper;

  @Autowired
  private UpdateUserRequestMapper updateUserRequestMapper;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private UserResponseMapper userResponseMapper;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Autowired
  private PasswordGenerator passwordGenerator;

  @Override
  public UserResponse createUser(CreateUserRequest request) {

    // TODO This is not a very good way to generate the Id
    // It should be using a JPA custom generator instead
    String id = "usr-" + UUID.randomUUID().toString().replace("-", "");

    User user = createUserMapper.toEntity(request);
    user.setId(id);

    String password = passwordGenerator.generatePassword();
    String bcryptPassword = encoder.encode(password);
    user.setPassword(bcryptPassword);
    OffsetDateTime now = OffsetDateTime.now();
    user.setCreatedTimestamp(now);
    user.setUpdatedTimestamp(now);

    User storedUser = userRepository.save(user);
    logger.info("***{} Password Emailed for User Id {}: name {}",
        password, user.getId(), user.getName());

    return userResponseMapper.toDto(storedUser);
  }

  @Override
  public UserResponse fetchUserById(String userId) {
    User storedUser = userDetailsService.findUserById(userId);
    return userResponseMapper.toDto(storedUser);
  }

  @Override
  public UserResponse updateUserById(String userId, UpdateUserRequest request) {
    User existingUser = userDetailsService.findUserById(userId);

    User updatedUser = updateUserRequestMapper.toEntity(request);
    updatedUser.setId(userId);
    updatedUser.setPassword(existingUser.getPassword());

    User storedUser = userRepository.save(updatedUser);
    return userResponseMapper.toDto(storedUser);
  }
}
