package com.eaglebank.service;

import com.eaglebank.model.User;

public interface UserDetailsService {
    User findUserByEmail(String email);

    User findUserById(String id);
}
