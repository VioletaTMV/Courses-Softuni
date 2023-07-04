package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "bet_games")
public class BetGame implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "bet_id", referencedColumnName = "id")
    private Bet bet;

    @OneToOne(optional = false)
    @JoinColumn(name = "result_prediction_id", referencedColumnName = "id")
    private ResultPrediction resultPrediction;

    public BetGame(){}

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }

}
