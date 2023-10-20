package com.resellerapp.model.entity;

import com.resellerapp.model.enums.ConditionNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private ConditionNameEnum name;

    @Column(nullable = false)
    private String description;

    public Condition(){
    }

    public Condition(ConditionNameEnum conditionNameEnum){
        this.name = conditionNameEnum;
        setDescription(conditionNameEnum);
    }


    public Condition setDescription(ConditionNameEnum name){

        this.description = this.name.getConditionDescription();
        return this;
    }

    public Long getId() {
        return id;
    }

    public ConditionNameEnum getName() {
        return name;
    }

    public Condition setName(ConditionNameEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Condition setDescription(String name) {
        this.description = name;
        return this;
    }
}
