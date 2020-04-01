package com.bridgelabz.model;
import com.opencsv.bean.CsvBindByName;

public class CsvStatesCensus {
    //BINDING THE COLUMN NAME IN CsvBindByName CLASS
    @CsvBindByName(column = "State")
    private String state;

    @CsvBindByName(column = "Population")
    private String population;

    @CsvBindByName(column = "AreaInSqKm")
    private String AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    private String DensityPerSqKm;
}
