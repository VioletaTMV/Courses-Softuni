package entities;

import enums.CountryCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "counties")
public class Country {

    @Id
    @Enumerated(value = EnumType.STRING)
    private CountryCode id;

    @Basic
    private String name;

    @ManyToMany
    @JoinTable(name = "countries_continents",
    joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id"))
    private Set<Continent> continent;


    public Country(){}


    public CountryCode getId() {
        return id;
    }

    public void setId(CountryCode id) {
        this.id = id;
    }

    public String getName() {
        return id.getCountryName();
    }

    public void setName() {
        this.name = id.getCountryName();
    }

    public Set<Continent> getContinent() {
        return continent;
    }

    public void setContinent(Set<Continent> continent) {
        this.continent = continent;
    }
}
