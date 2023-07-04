package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "games")
public class Game extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "home_team_id", referencedColumnName = "id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", referencedColumnName = "id")
    private Team awayTeam;

    @Column(name = "home_goals")
    private Integer homeGoals;

    @Column(name = "away_goals")
    private Integer awayGoals;

    @Column(name = "date_time_of_game")
    private LocalDateTime dataAndTimeOfGame;

    @Column(name = "home_team_win_bet_rate")
    private BigDecimal homeTeamWinBetRate;

    @Column(name = "away_team_win_bet_rate")
    private BigDecimal awayTeamWinBetRate;

    @Column(name = "draw_game_bet_rate")
    private BigDecimal drawGameBetRate;

    @ManyToOne
    @JoinColumn(name = "round_id", referencedColumnName = "id")
    private Round round;

    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;


    public Game(){}

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Integer homeGoals) {
        this.homeGoals = homeGoals;
    }

    public Integer getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Integer awayGoals) {
        this.awayGoals = awayGoals;
    }

    public LocalDateTime getDataAndTimeOfGame() {
        return dataAndTimeOfGame;
    }

    public void setDataAndTimeOfGame(LocalDateTime dataAndTimeOfGame) {
        this.dataAndTimeOfGame = dataAndTimeOfGame;
    }

    public BigDecimal getHomeTeamWinBetRate() {
        return homeTeamWinBetRate;
    }

    public void setHomeTeamWinBetRate(BigDecimal homeTeamWinBetRate) {
        this.homeTeamWinBetRate = homeTeamWinBetRate;
    }

    public BigDecimal getAwayTeamWinBetRate() {
        return awayTeamWinBetRate;
    }

    public void setAwayTeamWinBetRate(BigDecimal awayTeamWinBetRate) {
        this.awayTeamWinBetRate = awayTeamWinBetRate;
    }

    public BigDecimal getDrawGameBetRate() {
        return drawGameBetRate;
    }

    public void setDrawGameBetRate(BigDecimal drawGameBetRate) {
        this.drawGameBetRate = drawGameBetRate;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
