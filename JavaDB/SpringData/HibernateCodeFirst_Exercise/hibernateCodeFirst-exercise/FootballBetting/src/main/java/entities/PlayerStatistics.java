package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistics implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @Column(name = "scored_goals")
    private Short scoredGoals;

    @Column(name = "player_assists")
    private Short playerAssists;

    @Column(name = "player_minutes_during_game")
    private Short playerMinutesDuringGame;

    public PlayerStatistics(){}

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Short getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(Short scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public Short getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(Short playerAssists) {
        this.playerAssists = playerAssists;
    }

    public Short getPlayerMinutesDuringGame() {
        return playerMinutesDuringGame;
    }

    public void setPlayerMinutesDuringGame(Short playerMinutesDuringGame) {
        this.playerMinutesDuringGame = playerMinutesDuringGame;
    }
}
