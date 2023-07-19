package com.example.CarDealer_XML.constants;

public enum FileType {

    JSON ("JSON"),
    XML ("XML");

    private String fileTypeValue;

    private FileType(String type){
        this.fileTypeValue = type;
    }

    public String getFileTypeValue() {
        return fileTypeValue;
    }
}
