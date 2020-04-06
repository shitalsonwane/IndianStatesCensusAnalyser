package com.bridgelabz.dto;
import com.opencsv.bean.CsvBindByName;

public class CsvStatesCensus {
    //BINDING THE COLUMN NAME IN CsvBindByName CLASS
    @CsvBindByName(column = "State")
    public String State;
    @CsvBindByName(column = "Population")
    public long Population;
    @CsvBindByName(column = "AreaInSqKm")
    public long AreaInSqKm;
    @CsvBindByName(column = "DensityPerSqkm")
    public long DensityPerSqkm;

    public CsvStatesCensus(String state,long population, long areaInSqKm, long densityPerSqkm) {
        State=state;
        Population = population;
        AreaInSqKm = areaInSqKm;
        DensityPerSqkm = densityPerSqkm;
    }

    @Override
    public String toString() {
        return "CsvStatesCensus{" +
                "State=" + State +
                "Population=" + Population +
                ", AreaInSqKm=" + AreaInSqKm +
                ", DensityPerSqkm=" + DensityPerSqkm +
                '}';
    }
}
