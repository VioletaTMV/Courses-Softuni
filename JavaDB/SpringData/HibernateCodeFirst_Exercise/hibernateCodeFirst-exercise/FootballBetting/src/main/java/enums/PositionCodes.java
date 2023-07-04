package enums;

import javax.persistence.*;

public enum PositionCodes {

    GK("goal keeper"),
    DF("defender");

    @Column
    public final String description;

    private PositionCodes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
