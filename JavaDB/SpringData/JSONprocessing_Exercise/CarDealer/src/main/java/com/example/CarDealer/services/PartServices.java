package com.example.CarDealer.services;

import com.example.CarDealer.entities.parts.Part;

import java.util.List;

public interface PartServices {
    long getCountOfPartsInDb();

    void addSPartsToDB(List<Part> partsToAddToDB);

    Part getPartByID(long id);
}
