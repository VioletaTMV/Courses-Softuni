package org.example.SoftUniGameStore.services;

import org.example.SoftUniGameStore.domain.entities.Game;
import org.example.SoftUniGameStore.domain.entities.User;

import java.util.Set;

public interface UserService {

    String registerUser(String[] args);

    String loginUser(String[] inputArr);

    String logoutUser();

    boolean isLoggedInUserAdmin();

    User getLoggedInUser();

    void addGameToGameList(Set<Game> purchasedGames);

    String viewOwnedGames();

}
