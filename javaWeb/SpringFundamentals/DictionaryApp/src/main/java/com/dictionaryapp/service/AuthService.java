package com.dictionaryapp.service;

import com.dictionaryapp.model.DTOs.UserLoginDTO;
import com.dictionaryapp.model.DTOs.UserRegistrationDTO;
import com.dictionaryapp.model.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface AuthService {

    Optional<User> findByUsername(String value);

    Optional<User> findByEmail(String value);

    void register(UserRegistrationDTO userRegistrationDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();

    boolean userIsLoggedIn();

    Optional<User> findById(Long id);
}
