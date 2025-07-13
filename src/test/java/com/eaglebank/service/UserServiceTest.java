package com.eaglebank.service;

import com.eaglebank.model.CreateUserRequest;
import com.eaglebank.model.CreateUserRequestAddress;
import com.eaglebank.model.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void testCreateUser() {
    CreateUserRequest request = new CreateUserRequest();
    request.setName("Allan");
    request.setEmail("hello@barclays.com");
    request.setPhoneNumber("+447766123456");

    CreateUserRequestAddress address = new CreateUserRequestAddress();
    address.line1("line1");
    address.setTown("anTown");
    address.setCounty("anCounty");
    address.setPostcode("SL0 6TG");

    request.setAddress(address);

    UserResponse response = userService.createUser(request);
    assertNotNull(response.getId());
    assertEquals("Allan", response.getName());
    assertEquals("hello@barclays.com", response.getEmail());
    assertEquals("+447766123456", response.getPhoneNumber());

    assertEquals("line1", response.getAddress().getLine1());
    assertNull(response.getAddress().getLine2());
    assertNull(response.getAddress().getLine3());
    assertEquals("anTown", response.getAddress().getTown());
    assertEquals("anCounty", response.getAddress().getCounty());
    assertEquals("SL0 6TG", response.getAddress().getPostcode());
  }

  @Test
  public void testInvalidUser() {
    CreateUserRequest request = new CreateUserRequest();
    assertThrows(TransactionSystemException.class, () -> userService.createUser(request));
  }
}
