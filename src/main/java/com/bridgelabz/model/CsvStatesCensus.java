package com.bridgelabz.model;
import com.opencsv.bean.CsvBindByName;

public class CsvStatesCensus {
    //BINDING THE COLUMN NAME IN CsvBindByName CLASS
    @CsvBindByName(column = "State")
    public static String State;

    @CsvBindByName(column = "Population")
    public long Population;

    @CsvBindByName(column = "AreaInSqKm")
    public long AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqkm")
    public int DensityPerSqkm;
    public CsvStatesCensus(long population, long areaInSqKm, int densityPerSqkm) {
        Population = population;
        AreaInSqKm = areaInSqKm;
        DensityPerSqkm = densityPerSqkm;
    }

    @Override
    public String toString() {
        return "CSVStatesCensus{" +
                "Population=" + Population +
                ", AreaInSqKm=" + AreaInSqKm +
                ", DensityPerSqkm=" + DensityPerSqkm +
                '}';
    }
}
