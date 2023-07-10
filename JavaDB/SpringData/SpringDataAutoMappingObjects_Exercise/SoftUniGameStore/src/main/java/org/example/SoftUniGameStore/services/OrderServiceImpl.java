package org.example.SoftUniGameStore.services;

import org.example.SoftUniGameStore.domain.entities.Game;
import org.example.SoftUniGameStore.domain.entities.Order;
import org.example.SoftUniGameStore.domain.entities.User;
import org.example.SoftUniGameStore.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static org.example.SoftUniGameStore.constants.ErrorMessages.*;
import static org.example.SoftUniGameStore.constants.Messages.*;

@Service
public class OrderServiceImpl implements OrderService {

    private Map<User, Set<Game>> shoppingCart = new LinkedHashMap<>();

    private OrderRepository orderRepository;
    private UserService userService;
    private GameService gameService;
    private ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, GameService gameService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.gameService = gameService;
        this.modelMapper = modelMapper;
    }

    @Override
    public String addItemToShoppingCart(String[] inputArr) {

        User currentlyLoggedInUser = userService.getLoggedInUser();

        if (currentlyLoggedInUser == null) {
            return USER_OPERATION_NOT_ALLOWED;
        }

        String gameTitleToAddToShoppingCart = inputArr[1];

        Game gameByTitle = gameService.getGameByTitle(gameTitleToAddToShoppingCart);

        boolean currentUserAlreadyOwnTheGame = currentlyLoggedInUser.getGames()
                .stream()
                .anyMatch(g -> g.getId().equals(gameByTitle.getId()));

        if (currentUserAlreadyOwnTheGame) {
            throw new IllegalArgumentException(GAME_ALREADY_OWNED);
        }

        boolean isAvailableCartForCurrentlyLoggedInUser = shoppingCart.containsKey(currentlyLoggedInUser);
        if (!isAvailableCartForCurrentlyLoggedInUser) {
            initializeShoppingCartForCurrentUser();
        }
        shoppingCart.get(currentlyLoggedInUser).add(gameByTitle);

        return String.format(GAME_ADDED_TO_CART, gameTitleToAddToShoppingCart);
    }

    @Override
    public String removeItemFromShoppingCart(String[] inputArr) {

        User currentlyLoggedInUser = userService.getLoggedInUser();

        if (currentlyLoggedInUser == null) {
            return USER_OPERATION_NOT_ALLOWED;
        }

        String gameTitleToRemoveFromShoppingCart = inputArr[1];

        boolean currentlyLoggedInUserHasAnOpenCart = shoppingCart.containsKey(currentlyLoggedInUser);
        if (!currentlyLoggedInUserHasAnOpenCart) {
            return USER_OPERATION_NOT_ALLOWED;
        }

        Game gameToRemoveFromShoppingCart = shoppingCart.get(currentlyLoggedInUser)
                .stream()
                .filter(g -> g.getTitle().equals(gameTitleToRemoveFromShoppingCart))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_GAME));

        shoppingCart.get(currentlyLoggedInUser).remove(gameToRemoveFromShoppingCart);

        return String.format(GAME_REMOVED_FROM_CART, gameTitleToRemoveFromShoppingCart);
    }

    @Override
    public String buyItemsInShoppingCart() {

        User currentlyLoggedInUser = userService.getLoggedInUser();

        if (currentlyLoggedInUser == null) {
            return USER_OPERATION_NOT_ALLOWED;
        }

        boolean isUserCartEmpty = shoppingCart.get(currentlyLoggedInUser).isEmpty();
        if (isUserCartEmpty) {
            return GAMES_NOT_AVAILABLE;
        }

        Set<Game> gamesOnOrder = shoppingCart.get(currentlyLoggedInUser);

        Order order = new Order(currentlyLoggedInUser, gamesOnOrder);
        orderRepository.save(order);

        userService.addGameToGameList(gamesOnOrder);

        StringBuilder sb = new StringBuilder();

        shoppingCart.get(currentlyLoggedInUser)
                .forEach(g -> sb.append("  -").append(g.getTitle()).append(System.lineSeparator()));

        shoppingCart.get(currentlyLoggedInUser).clear();

        return String.format(SUCCESSFUL_PURCHASE, sb.toString());
    }

    private void initializeShoppingCartForCurrentUser() {
        User currentlyLoggedInUser = userService.getLoggedInUser();

        this.shoppingCart.put(currentlyLoggedInUser, new LinkedHashSet<>());
    }
}
