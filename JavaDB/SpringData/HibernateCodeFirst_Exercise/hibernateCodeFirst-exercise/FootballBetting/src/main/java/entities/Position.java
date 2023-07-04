package entities;

import enums.PositionCodes;

import javax.persistence.*;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @Enumerated
    private PositionCodes id;

    @Column(name = "position_description")
    private String positionDescription;


    public Position() {
    }

    public PositionCodes getId() {
        return id;
    }

    public void setId(PositionCodes id) {
        this.id = id;
    }

    public String getPositionDescription() {
        return id.getDescription();
    }

    public void setPositionDescription() {
        this.positionDescription = id.getDescription();
    }
}
