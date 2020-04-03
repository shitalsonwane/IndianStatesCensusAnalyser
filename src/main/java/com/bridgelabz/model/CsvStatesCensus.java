package com.bridgelabz.model;
import com.opencsv.bean.CsvBindByName;

public class CsvStatesCensus {
    //BINDING THE COLUMN NAME IN CsvBindByName CLASS
    @CsvBindByName(column = "State")
    public static String state;

    @CsvBindByName(column = "Population")
    public int population;

    @CsvBindByName(column = "AreaInSqKm")
    public int AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    public int DensityPerSqKm;
}
