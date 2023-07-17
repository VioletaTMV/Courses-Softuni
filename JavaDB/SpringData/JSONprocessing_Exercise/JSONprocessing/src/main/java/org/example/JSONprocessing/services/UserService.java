package org.example.JSONprocessing.services;

import org.example.JSONprocessing.entities.users.User;
import org.example.JSONprocessing.entities.users.UserWithProductsSoldAndBuyerInfoDTO;
import org.example.JSONprocessing.entities.users.UsersWithProductsSoldWrapperDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {
    long getUserRepositorySize();

    void saveAllToDB(List<User> usersToBeAddedToDB);

    User getUserByID(long randomUserId);

    List<UserWithProductsSoldAndBuyerInfoDTO> getSellersWithProductsSoldInfoAndTheirBuyer() throws IOException;

    UsersWithProductsSoldWrapperDTO findUsersAndTheirProductsSold() throws IOException;
}
