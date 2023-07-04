package entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "competitions")
public class Competition extends BaseEntityWithName {

    @ManyToOne
    @JoinColumn(name = "competition_type_id", referencedColumnName = "id")
    private CompetitionType type;

    public Competition(){}

    public CompetitionType getType() {
        return type;
    }

    public void setType(CompetitionType type) {
        this.type = type;
    }
}
