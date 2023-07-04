package entities;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "bets")
public class Bet extends BaseEntity{

    @Column(name = "bet_money", nullable = false)
    private BigDecimal betMoney;

    @Column(name = "date_time_of_bet")
    @CreationTimestamp
    private LocalDateTime dateAndTimeOfBet = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Bet(){}

    public BigDecimal getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(BigDecimal betMoney) {
        this.betMoney = betMoney;
    }

    public LocalDateTime getDateAndTimeOfBet() {
        return dateAndTimeOfBet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
