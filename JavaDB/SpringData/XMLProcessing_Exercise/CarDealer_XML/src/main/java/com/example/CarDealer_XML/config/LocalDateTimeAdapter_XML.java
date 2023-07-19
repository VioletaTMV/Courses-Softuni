package com.example.CarDealer_XML.config;

import com.google.gson.JsonPrimitive;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateTimeAdapter_XML extends XmlAdapter<String, LocalDateTime> {

    //private static final DateFormat ISO_LOCALDATETIME_FORMAT = new SimpleDateFormat("dd-MM-yyyyTHH:mm:ss");
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public LocalDateTime unmarshal(String s) throws Exception {
        return LocalDateTime.parse(s, dateTimeFormatter);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
       // return ISO_LOCALDATETIME_FORMAT.format(localDateTime);
        String formattedLDTforXML = localDateTime.format(dateTimeFormatter);
        return formattedLDTforXML;
    }
}
