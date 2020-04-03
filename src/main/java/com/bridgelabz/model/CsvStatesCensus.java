package com.bridgelabz.model;
import com.opencsv.bean.CsvBindByName;

public class CsvStatesCensus {
    //BINDING THE COLUMN NAME IN CsvBindByName CLASS
    @CsvBindByName(column = "State")
    public String state;

    @CsvBindByName(column = "Population")
    public String population;

    @CsvBindByName(column = "AreaInSqKm")
    public String AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    public String DensityPerSqKm;
}
