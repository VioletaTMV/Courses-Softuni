package com.example.CarDealer_XML.services;

import com.example.CarDealer_XML.entities.parts.Part;

import java.util.List;

public interface PartServices {
    long getCountOfPartsInDb();

    void addSPartsToDB(List<Part> partsToAddToDB);

    Part getPartByID(long id);
}
