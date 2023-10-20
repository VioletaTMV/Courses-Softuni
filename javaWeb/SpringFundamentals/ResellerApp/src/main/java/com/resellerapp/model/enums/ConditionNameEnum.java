package com.resellerapp.model.enums;

public enum ConditionNameEnum {
    EXCELLENT("In perfect condition"),
    GOOD ("Some signs of wear and tear or minor defects"),
    ACCEPTABLE ("Some signs of wear and tear or minor defects");

    private final String conditionDescription;

    private ConditionNameEnum(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }
}
