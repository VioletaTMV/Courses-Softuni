package org.example.SoftUniGameStore.domain.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User buyer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orders_games",
    joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
    inverseJoinColumns= @JoinColumn(name = "game_id", referencedColumnName = "id"))
    private Set<Game> games;

    public Order(){
        this.games = new HashSet<>();
    }

    public Order(User buyer, Set<Game> games) {
        this.buyer = buyer;
        this.games = games;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
