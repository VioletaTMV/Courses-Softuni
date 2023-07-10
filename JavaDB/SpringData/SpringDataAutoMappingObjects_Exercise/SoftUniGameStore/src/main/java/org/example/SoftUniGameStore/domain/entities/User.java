package org.example.SoftUniGameStore.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_games",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
    private Set<Game> games;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdministrator;

    @OneToMany(targetEntity = Order.class, mappedBy = "buyer")
    private Set<Order> orders;

    public User() {
        this.games = new HashSet<>();
    }

    public User(String email, String password, String fullName, Boolean isAdministrator) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.isAdministrator = isAdministrator;
        this.games = new HashSet<>();
    }


    public Set<Order> getOrders() {
        return orders;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Boolean getIsAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(Boolean administrator) {
        isAdministrator = administrator;
    }
}
