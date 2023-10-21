package com.dictionaryapp.service.serviceImpl;

import com.dictionaryapp.model.DTOs.UserLoginDTO;
import com.dictionaryapp.model.DTOs.UserRegistrationDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.AuthService;
import com.dictionaryapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

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

    @Override
    public Optional<User> findByUsername(String value) {
        return this.userRepository.findByUsername(value);
    }

    @Override
    public Optional<User> findByEmail(String value) {
        return this.userRepository.findByEmail(value);
    }

    @Override
    public void register(UserRegistrationDTO userRegistrationDTO) {

        User newRegisteredUser = this.modelMapper.map(userRegistrationDTO, User.class);
        newRegisteredUser.setPassword(this.passwordEncoder.encode(userRegistrationDTO.getPassword()));

        this.userRepository.save(newRegisteredUser);
    }

    @Override
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

    @Override
    public boolean userIsLoggedIn() {

        return (this.userSession.getId() != null);
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }
}
