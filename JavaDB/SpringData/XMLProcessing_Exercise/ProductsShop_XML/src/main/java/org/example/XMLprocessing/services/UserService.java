package org.example.XMLprocessing.services;

import org.example.XMLprocessing.entities.users.User;
import org.example.XMLprocessing.entities.users.UserWithProductsSoldAndBuyerInfoDTO;
import org.example.XMLprocessing.entities.users.UserWithProductsSoldAndBuyerInfoWrapperDTO;
import org.example.XMLprocessing.entities.users.UsersWithProductsSoldWrapperDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {
    long getUserRepositorySize();

    void saveAllToDB(List<User> usersToBeAddedToDB);

    User getUserByID(long randomUserId);

    UserWithProductsSoldAndBuyerInfoWrapperDTO getSellersWithProductsSoldInfoAndTheirBuyer() throws IOException;

    UsersWithProductsSoldWrapperDTO findUsersAndTheirProductsSold() throws IOException;
}
