package org.example.SoftUniGameStore.services;

import org.example.SoftUniGameStore.domain.dtos.GameAddDTO;
import org.example.SoftUniGameStore.domain.dtos.GameDetailViewDTO;
import org.example.SoftUniGameStore.domain.dtos.GameEditDTO;
import org.example.SoftUniGameStore.domain.dtos.GameViewDTO;
import org.example.SoftUniGameStore.domain.entities.Game;
import org.example.SoftUniGameStore.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.example.SoftUniGameStore.constants.ErrorMessages.*;
import static org.example.SoftUniGameStore.constants.Messages.*;

@Service
public class GameServiceImpl implements GameService {

    private final ModelMapper modelMapper;
    private final GameRepository gameRepository;
    private final UserService userService;

    public GameServiceImpl(ModelMapper modelMapper, GameRepository gameRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    @Override
    public String addGame(String[] inputArr) {

        if (!userService.isLoggedInUserAdmin()){
             return USER_OPERATION_NOT_ALLOWED;
        }

        int inputLength = inputArr.length;

        String title = inputLength > 1 ? inputArr[1] : "";
        BigDecimal price = inputLength > 2 ? new BigDecimal(inputArr[2]) : null;
        Float size = inputLength > 3 ? Float.parseFloat(inputArr[3]) : null;
        String url = inputLength > 4 ? inputArr[4] : "";
        String thumbnailURL = inputLength > 5 ? inputArr[5] : "";
        String description = inputLength > 6 ? inputArr[6] : "";
        LocalDate releaseDate = inputLength > 7 ? LocalDate.parse(inputArr[7], DateTimeFormatter.ofPattern("dd-MM-yyyy")) : null;

        GameAddDTO gameAddDTO;

        try {
            gameAddDTO = new GameAddDTO(title, price, size, url, thumbnailURL, description, releaseDate);
        } catch (IllegalArgumentException ex) {
            return ex.getMessage();
        }

        Game game = modelMapper.map(gameAddDTO, Game.class);

        gameRepository.save(game);

        return String.format(GAME_ADDED, game.getTitle());
    }

    @Override
    public String deleteGame(String[] inputArr) {

        if (!userService.isLoggedInUserAdmin()){
            return USER_OPERATION_NOT_ALLOWED;
        }

        Long idOfGameToBeDeleted = Long.parseLong(inputArr[1]);

        Optional<Game> gameToBeDeleted = gameRepository.findById(idOfGameToBeDeleted);
        if (gameToBeDeleted.isEmpty()){
            return INVALID_GAME;
        }

        Game gameInDeleteProcess = gameToBeDeleted.get();

        gameRepository.delete(gameInDeleteProcess);

        return String.format(GAME_DELETED, gameInDeleteProcess.getTitle());
    }

    @Override
    public String editGame(String[] inputArr) {

        if (!userService.isLoggedInUserAdmin()){
            return USER_OPERATION_NOT_ALLOWED;
        }

        Long idOfGameToBeEdited = Long.parseLong(inputArr[1]);

        Optional<Game> gameToBeEdited = gameRepository.findById(idOfGameToBeEdited);
        if (gameToBeEdited.isEmpty()){
            return INVALID_GAME;
        }

        Game gameInEditProcess = gameToBeEdited.get();

        GameEditDTO gameEditDTO = modelMapper.map(gameInEditProcess, GameEditDTO.class);

        try {
            gameEditDTO = gameEditDTO.populateFieldsWithDataToBeEdited(gameEditDTO, inputArr);
        } catch (IllegalArgumentException ex){
           return ex.getMessage();
        }

        modelMapper.map(gameEditDTO, gameInEditProcess);

        gameRepository.save(gameInEditProcess);

        return String.format(GAME_EDITED, gameInEditProcess.getTitle());
    }

    @Override
    public String viewAllGames() {

        StringBuilder allGamesDTOView = new StringBuilder();

        if (gameRepository.count() == 0){
            return GAMES_NOT_AVAILABLE;
        }

        gameRepository.findAll()
                .stream()
                .map(game -> modelMapper.map(game, GameViewDTO.class))
                .forEach(gameDTO -> allGamesDTOView.append(gameDTO).append(System.lineSeparator()));

    return allGamesDTOView.toString().trim();

    }

    @Override
    public String viewGameDetails(String[] inputArr) {

        String requestedGame = inputArr[1];

        Optional<Game> gameToDisplay = gameRepository.findByTitle(requestedGame);

        if (gameToDisplay.isEmpty()){
            return INVALID_GAME;
        }

        GameDetailViewDTO gameDetailViewDTO = modelMapper.map(gameToDisplay.get(), GameDetailViewDTO.class);

        return gameDetailViewDTO.toString();

    }

    @Override
    public Game getGameByTitle(String title) {

        Optional<Game> byTitle = gameRepository.findByTitle(title);

        if (byTitle.isEmpty()){
            throw new IllegalArgumentException(INVALID_GAME);
        }

        return byTitle.get();
    }
}
