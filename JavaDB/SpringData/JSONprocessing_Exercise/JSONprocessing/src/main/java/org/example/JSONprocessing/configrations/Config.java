package org.example.JSONprocessing.configrations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.JSONprocessing.constants.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


@Configuration
public class Config {

    @Bean
    public Gson createGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ModelMapper createModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }


}


