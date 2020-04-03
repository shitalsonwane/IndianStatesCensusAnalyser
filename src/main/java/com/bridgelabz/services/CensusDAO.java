package com.bridgelabz.services;

import com.bridgelabz.model.CsvStatesCensus;

public class CensusDAO {
    public String state;
    public int population;
    public int area;
    public int density;
    public String stateCode;

    public CensusDAO(CsvStatesCensus csvStatesCensus) {
        this.state = csvStatesCensus.state;
        this.population = csvStatesCensus.population;
        this.area = csvStatesCensus. AreaInSqKm;
        this.density = csvStatesCensus. DensityPerSqKm;
    }
}
