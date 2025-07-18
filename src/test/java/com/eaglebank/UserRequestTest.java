package com.eaglebank;

import com.eaglebank.service.PasswordGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRequestTest {

  private static final String domain = "http://localhost";

  @LocalServerPort
  private int port;

  private String root;

  @Autowired
  private TestRestTemplate restTemplate;

  @BeforeEach
  public void setup() {
    root = domain + ":" + port + "/";
  }

  @Test
  public void createUserTestSuccess() throws Exception {
    String userJson = """
      {
      "name": "able",
      "address": {
        "line1": "oneline",
        "town": "thistown",
        "county": "thiscounty",
        "postcode": "thispostcode"
      },
      "phoneNumber": "+441234567567",
      "email": "email@address.com"
      }
    """;

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> request = new HttpEntity<>(userJson, headers);

    ResponseEntity<String> createdUserDetailsEntity = restTemplate.postForEntity(root + "v1/users", request, String.class);

    assertTrue(createdUserDetailsEntity.getStatusCode().isSameCodeAs(CREATED));
  }

  @Test
  public void createUserBadRequestAuthenticated() throws Exception {
    // TODO I did not understand this scenario as the POST /v1/users endpoint is not authenticated
    // so I am just ignoring the authenticated comment in the scenario
    String userJson = """
      "user": "able",
      "address": {
        "line1": "oneline",
        "town": "thistown",
        "county": "thiscounty",
        "postcode": "thispostcode"
      },
      "phoneNumber": "+441234567567",
      "email": "NOT AN EMAIL ADDRESS"
    """;

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> request = new HttpEntity<>(userJson, headers);

    ResponseEntity<String> createdUserDetailsEntity = restTemplate.postForEntity(root + "v1/users", request, String.class);

    assertTrue(createdUserDetailsEntity.getStatusCode().isSameCodeAs(BAD_REQUEST));
  }

  @Test
  public void fetchNonExistentDetails() throws Exception {
    // Tested through PostMan
    // Need to implement the actual test here
  }

  @Test
  public void authorisedFetchUnauthorisedUser() throws Exception {
    // Tested through PostMan
    // Need to implement the actual test here
  }

  @Test
  public void updateUserDetails() throws Exception {
    // Tested through PostMan
    // 1) Create User POST /v1/users
    // 2) Login with the email and password
    // 3) Use the jwt to call the PATCH /v1/users/{userId} endpoint
    // 4) GET /v1/users/{userId} to check the update has occurred
  }

  @Test
  public void updateNonExistentUser() throws Exception {
    // Tested through PostMan
    // 1) Create User POST /v1/users
    // 2) Login with the email and password
    // 3) Use the jwt to call the PATCH /v1/users{non-existent userId} endpoint
    // 4) Response is a NOTFOUND
  }

  @Test
  public void updateAuthorisedOnADifferentUser() throws Exception {
    // Tested through PostMan
    // 1) Create User POST /v1/users
    // 2) Login with the email and password to get JWT
    // 3) Create a second user with POST /v1/users but do not login
    // 3) Use the jwt for user 1 to call the PATCH /v1/users{user 2 userId} endpoint
    // 4) Response is a FORBIDDEN
  }

  @Test
  public void deleteUserNoBankAccounts() throws Exception {
    // Tested in PostMan
  }
}
