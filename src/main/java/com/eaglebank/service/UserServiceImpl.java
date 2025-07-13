package com.eaglebank.service;

import com.eaglebank.auth.PasswordGenerator;
import com.eaglebank.mapper.CreateUserRequestMapper;
import com.eaglebank.mapper.UserResponseMapper;
import com.eaglebank.model.CreateUserRequest;
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
  private CreateUserRequestMapper userMapper;

  @Autowired
  private UserResponseMapper userResponseMapper;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Override
  public UserResponse createUser(CreateUserRequest request) {

    // TODO This is not a very good way to generate the Id
    // It should be using a JPA custom generator instead
    String id = "usr-" + UUID.randomUUID().toString().replace("-", "");

    User user = userMapper.toEntity(request);
    user.setId(id);

    String password = PasswordGenerator.generatePassword();
    String bcryptPassword = encoder.encode(password);
    user.setPassword(bcryptPassword);
    OffsetDateTime now = OffsetDateTime.now();
    user.setCreatedTimestamp(now);
    user.setUpdatedTimestamp(now);

    User storedUser = userRepository.save(user);
    logger.info("User Created with Id {}: name {}} password emailed is {}",
        user.getId(), user.getName(), password);

    return userResponseMapper.toDto(storedUser);
  }
}
