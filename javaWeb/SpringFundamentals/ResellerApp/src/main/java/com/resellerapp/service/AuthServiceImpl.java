package com.resellerapp.service;

import com.resellerapp.model.DTOs.UserLoginDTO;
import com.resellerapp.model.DTOs.UserRegistrationDTO;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private CurrentUser userSession;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser userSession) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }


    public Optional<User> findByUsername(String s) {

        return this.userRepository.findByUsername(s);
    }

    public Optional<User> findByEmail(String email){

        return this.userRepository.findByEmail(email);
    }

    public void register(UserRegistrationDTO userRegistrationDTO) {

        User newRegisteredUser = this.modelMapper.map(userRegistrationDTO, User.class);
        newRegisteredUser.setPassword(this.passwordEncoder.encode(userRegistrationDTO.getPassword()));

       this.userRepository.save(newRegisteredUser);

    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> byUsername = this.userRepository.findByUsername(userLoginDTO.getUsername());

        if (byUsername.isEmpty()){
            return false;
        }

        String rawPassword = userLoginDTO.getPassword();
        String encodedPassword = byUsername.get().getPassword();

        boolean successfulAuthentication = this.passwordEncoder.matches(rawPassword, encodedPassword);

        if (successfulAuthentication){
            this.userSession.login(byUsername.get());
        } else {
            logout();
        }

        return successfulAuthentication;
    }

    public void logout() {
        this.userSession.logout();
    }

    public Optional<User> findById(Long id) {

        return this.userRepository.findById(id);
    }

    public boolean userIsLoggedIn() {

        return (this.userSession.getId() != null);
    }
}
