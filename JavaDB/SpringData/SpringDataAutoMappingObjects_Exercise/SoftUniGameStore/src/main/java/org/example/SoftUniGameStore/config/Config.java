package org.example.SoftUniGameStore.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class Config {

    @Bean
    public ModelMapper createModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }


    @Bean
    public Scanner createScanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }
}
