package com.resellerapp.service;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionNameEnum;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConditionServiceImpl {

    private ConditionRepository conditionRepository;

    @Autowired
    public ConditionServiceImpl(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public Condition getCondition(ConditionNameEnum valueOf) {
        return this.conditionRepository.getByName(valueOf);
    }
}
