package com.eaglebank.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PasswordGeneratorImpl implements PasswordGenerator {

  public String generatePassword() {
    String upperCaseLetters = RandomStringUtils.secure().next(2, 65, 90, true, true);
    String lowerCaseLetters = RandomStringUtils.secure().next(2, 97, 122, true, true);
    String numbers = RandomStringUtils.secure().nextNumeric(2);
    String specialChar = RandomStringUtils.secure().next(2, 33, 47, false, false);
    String totalChars = RandomStringUtils.secure().nextAlphanumeric(2);
    String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
        .concat(numbers)
        .concat(specialChar)
        .concat(totalChars);
    List<Character> pwdChars = combinedChars.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.toList());
    Collections.shuffle(pwdChars);
    String password = pwdChars.stream()
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
        .toString();
    return password;
  }}
