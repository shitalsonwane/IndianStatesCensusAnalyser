package com.bridgelabz.dto;

import com.opencsv.bean.CsvBindByName;

public class CSVUSCensus {
    @CsvBindByName(column = "State Id")
    public String StateID;

    @CsvBindByName(column = "State")
    public String State;

    @CsvBindByName(column = "Population Density")
    public long PopulationDensity;

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

    public CSVUSCensus(String stateId, String state, long population, long totalArea, long populationDensity) {
        this.StateID = stateId;
        this.State = state;
        this.Population = population;
        this.Area = totalArea;
        this.PopulationDensity = populationDensity;
    }
   @Override
    public String toString() {
        return "CSVUSCensus{" +
                "StateID=" + StateID +
                ", State=" + State +
                ", Population=" + Population +
                ", Area=" + Area +
                ", PopulationDensity=" + PopulationDensity +
                '}';
    }
}
