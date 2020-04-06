package com.bridgelabz.dto;

import com.opencsv.bean.CsvBindByName;

public class CSVUSCensus {
    @CsvBindByName(column = "State Id")
    public String StateID;

    @CsvBindByName(column = "State")
    public String State;

    @CsvBindByName(column = "Population Density")
    public int PopulationDensity;

    @CsvBindByName(column = "Population")
    public long Population;

    @CsvBindByName(column = "Total area")
    public long Area;

    @CsvBindByName(column = "Housing units")
    public String HousingUnits;

    @CsvBindByName(column = "Water area")
    public String WaterArea;

    @CsvBindByName(column = "Land Area")
    public String LandArea;

    @CsvBindByName(column = "Housing Density")
    public float HousingDensity;

    public CSVUSCensus(String state,long population, long area, int populationDensity){
        State=state;
        Population = population;
        Area = area;
        PopulationDensity = populationDensity;
    }
    @Override
    public String toString() {
        return "CSVUSCensus{" +
                "State=" + State +
                "Population=" + Population +
                ", Area=" + Area +
                ", PopulationDensity=" + PopulationDensity +
                '}';
    }
}
