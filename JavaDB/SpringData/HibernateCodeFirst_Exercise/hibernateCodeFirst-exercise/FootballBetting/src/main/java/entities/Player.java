package entities;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player extends BaseEntityWithName{

    @Column(name = "squad_number")
    private Integer squadNumber;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;

    @Column(name = "is_currently_injured")
    private Boolean isCurrentlyInjured;

    public Player(){}

    public Integer getSquadNumber() {
        return squadNumber;
    }

    public void setSquadNumber(Integer squadNumber) {
        this.squadNumber = squadNumber;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Boolean getCurrentlyInjured() {
        return isCurrentlyInjured;
    }

    public void setCurrentlyInjured(Boolean currentlyInjured) {
        isCurrentlyInjured = currentlyInjured;
    }
}
