package com.resellerapp.init;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionNameEnum;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConditionSeeder implements CommandLineRunner {

    private ConditionRepository conditionRepository;

    @Autowired
    public ConditionSeeder(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.conditionRepository.count() == 0){

            List<Condition> conditionList = Arrays.stream(ConditionNameEnum.values())
                    .map(cn -> new Condition(cn))
                    .collect(Collectors.toList());

            this.conditionRepository.saveAll(conditionList);

        }



    }
}
