package entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntityWithName extends BaseEntity{

    @Column(nullable = false)
    private String name;

    public BaseEntityWithName(){}

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
