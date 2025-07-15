package com.eaglebank.service;

import com.eaglebank.exception.ConflictException;
import com.eaglebank.model.BankAccountResponse;
import com.eaglebank.model.CreateUserRequest;
import com.eaglebank.model.CreateUserRequestAddress;
import com.eaglebank.model.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.TransactionSystemException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @MockBean
  private BankAccountService bankAccountService;

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
    assertThrows(DataIntegrityViolationException.class, () -> userService.createUser(request));
  }

  @Test
  public void testCannotDeleteUserWithBankAccount() {
    String userId = "abcdef";

    List<BankAccountResponse> accounts = new ArrayList<BankAccountResponse>();
    BankAccountResponse singleBankAccount = mock(BankAccountResponse.class);
    accounts.add(singleBankAccount);
    when(bankAccountService.fetchBankAccountsByUserId(userId))
        .thenReturn(accounts);
    assertThrows(ConflictException.class, () -> userService.deleteUser(userId));

  }
}
