package org.example.SoftUniGameStore;

import org.example.SoftUniGameStore.services.GameService;
import org.example.SoftUniGameStore.services.OrderService;
import org.example.SoftUniGameStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static org.example.SoftUniGameStore.constants.Commands.*;

@Component
public class MyRunner implements CommandLineRunner {

    private Scanner scanner;
    private UserService userService;
    private GameService gameService;
    private OrderService orderService;

    @Autowired
    public MyRunner(Scanner scanner, UserService userService, GameService gameService, OrderService orderService) {
        this.scanner = scanner;
        this.userService = userService;
        this.gameService = gameService;
        this.orderService = orderService;
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println("Enter desired action and arguments: ");
        String input = scanner.nextLine();

        while (!input.equals(CLOSE)) {

            final String[] inputArr = input.split("\\|");
            final String command = inputArr[0];

            String output;

            try {
                output = switch (command) {
                    case REGISTER_USER -> this.userService.registerUser(inputArr);
                    case LOGIN_USER -> this.userService.loginUser(inputArr);
                    case LOGOUT_USER -> this.userService.logoutUser();
                    case ADD_GAME -> this.gameService.addGame(inputArr);
                    case EDIT_GAME -> this.gameService.editGame(inputArr);
                    case DELETE_GAME -> this.gameService.deleteGame(inputArr);
                    case VIEW_ALL_GAMES -> this.gameService.viewAllGames();
                    case VIEW_GAME_DETAILS -> this.gameService.viewGameDetails(inputArr);
                    case VIEW_OWNED_GAMES -> this.userService.viewOwnedGames();
                    case ADD_ITEM_TO_SHOPPING_CART -> this.orderService.addItemToShoppingCart(inputArr);
                    case REMOVE_ITEM_FROM_SHOPPING_CART -> this.orderService.removeItemFromShoppingCart(inputArr);
                    case BUY_ITEMS_FROM_SHOPPING_CART -> this.orderService.buyItemsInShoppingCart();
                    default -> "No command found";

                };

            } catch (IllegalArgumentException err) {
                output = err.getMessage();
            }

            System.out.println(output);

            System.out.println("Enter next desired action and arguments: ");
            input = scanner.nextLine();
        }


    }


}
