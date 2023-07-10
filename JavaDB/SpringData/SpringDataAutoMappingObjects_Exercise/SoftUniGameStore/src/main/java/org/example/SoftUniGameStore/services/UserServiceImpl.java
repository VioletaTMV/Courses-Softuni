package org.example.SoftUniGameStore.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.SoftUniGameStore.domain.dtos.UserLoginDTO;
import org.example.SoftUniGameStore.domain.dtos.UserRegisterDTO;
import org.example.SoftUniGameStore.domain.entities.Game;
import org.example.SoftUniGameStore.domain.entities.User;
import org.example.SoftUniGameStore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static org.example.SoftUniGameStore.constants.ErrorMessages.*;
import static org.example.SoftUniGameStore.constants.Messages.SUCCESSFULLY_LOGGED_IN;
import static org.example.SoftUniGameStore.constants.Messages.SUCCESSFULLY_LOGGED_OUT;

@Service
public class UserServiceImpl implements UserService {

    private User loggedInUser;

    private ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public String registerUser(String[] args) {

        int argsLength = args.length;

        final String email = argsLength > 1 ? args[1] : "";
        final String password = argsLength > 2 ? args[2] : "";
        final String confirmPassword = argsLength > 3 ? args[3] : "";
        final String fullName = argsLength > 4 ? args[4] : "";

        UserRegisterDTO userRegisterDTO;

        try {
            userRegisterDTO = new UserRegisterDTO(email, password, confirmPassword, fullName);
        } catch (IllegalArgumentException ex) {
            return ex.getMessage();
        }

        if (this.userRepository.findByEmail(email).isPresent()) {
            return EMAIL_ALREADY_EXISTS;
        }

        final User user = modelMapper.map(userRegisterDTO, User.class);

        boolean isFirstUserToRegister = userRepository.count() == 0;
        if (isFirstUserToRegister) {
            user.setAdministrator(true);
        } else {
            user.setAdministrator(false);
        }

        this.userRepository.save(user);

        return userRegisterDTO.successfullyRegisteredUser();
    }

    @Override
    public String loginUser(String[] inputArr) {

        if (this.loggedInUser != null) {
            return "User is already logged in.";
        }

        int inputArrLength = inputArr.length;

        final String email = inputArrLength > 1 ? inputArr[1] : "";
        final String password = inputArrLength > 2 ? inputArr[2] : "";

        Optional<User> userToBeLogged = this.userRepository.findByEmail(email);

        if (userToBeLogged.isEmpty()) {
            return INCORRECT_USERNAME_PASSWORD;
        }

        UserLoginDTO userLoginDTO = new UserLoginDTO(email, password);

        try {
            userLoginDTO.validate(userToBeLogged.get().getPassword());
        } catch (IllegalArgumentException ex) {
            return ex.getMessage();
        }

        this.loggedInUser = userToBeLogged.get();

        return SUCCESSFULLY_LOGGED_IN + userToBeLogged.get().getFullName();
    }

    @Override
    public String logoutUser() {

        if (this.loggedInUser == null) {
            return NO_LOGGED_USER;
        }

        String currentlyLoggedInUser = loggedInUser.getFullName();
        this.loggedInUser = null;

        return String.format(SUCCESSFULLY_LOGGED_OUT, currentlyLoggedInUser);

    }

    @Override
    public boolean isLoggedInUserAdmin() {
        return this.loggedInUser != null && this.loggedInUser.getIsAdministrator();
    }

    @Override
    public User getLoggedInUser() {

        return this.loggedInUser;
    }


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addGameToGameList(Set<Game> purchasedGames) {

        boolean b = this.loggedInUser.getGames().addAll(purchasedGames);
        Set<Game> updatedGameSet = loggedInUser.getGames();
        entityManager.merge(loggedInUser);
        entityManager.flush();

    }

    @Override
    public String viewOwnedGames() {

        StringBuilder sb = new StringBuilder();

        loggedInUser.getGames().forEach(g -> sb.append(g.getTitle()).append(System.lineSeparator()));

        return sb.toString().trim();
    }

}
