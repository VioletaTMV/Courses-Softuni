package org.example.SoftUniGameStore.domain.dtos;

import org.example.SoftUniGameStore.domain.entities.Game;
import org.example.SoftUniGameStore.domain.entities.User;

import java.util.List;

public class OrderDTO {

    private User buyer;
    private List<Game> games;

    public OrderDTO(){}

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
