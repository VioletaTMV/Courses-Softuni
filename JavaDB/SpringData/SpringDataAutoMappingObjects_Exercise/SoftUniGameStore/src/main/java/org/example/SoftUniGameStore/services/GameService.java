package org.example.SoftUniGameStore.services;

import org.example.SoftUniGameStore.domain.entities.Game;

public interface GameService {
    String addGame(String[] inputArr);

    String deleteGame(String[] inputArr);

    String editGame(String[] inputArr);

    String viewAllGames();

    String viewGameDetails(String[] inputArr);

    Game getGameByTitle(String title);
}
