package entities;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town extends BaseEntityWithName {

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;


    public Town(){}


    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
