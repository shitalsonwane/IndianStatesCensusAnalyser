package com.bridgelabz.dao;

import com.bridgelabz.dto.CSVStatesPojoClass;
import com.bridgelabz.dto.CSVUSCensus;
import com.bridgelabz.dto.CsvStatesCensus;
import com.bridgelabz.services.CensusAnalyser;

public class CensusDAO {
        public float HousingDensity;
        public String StateID;
        public String State;
        public long Population;
        public long AreaInSqKm;
        public long DensityPerSqkm;
        public String StateCode;
        public int TIN;
        public int SrNo;

        public CensusDAO(CsvStatesCensus csvStatesCensus) {
            this.State = csvStatesCensus.State;
            this.Population = csvStatesCensus.Population;
            this.AreaInSqKm = csvStatesCensus.AreaInSqKm;
            this.DensityPerSqkm = csvStatesCensus.DensityPerSqkm;
        }

        public CensusDAO(CSVStatesPojoClass csvStatesPojoClass) {
            this.State = csvStatesPojoClass.State;
            this.SrNo = csvStatesPojoClass.SrNo;
            this.TIN = csvStatesPojoClass.TIN;
            this.StateCode = csvStatesPojoClass.StateCode;
        }

        public CensusDAO(CSVUSCensus csvusCensus){
            this.StateID = csvusCensus.StateID;
            this.State = csvusCensus.State;
            this.Population = csvusCensus.Population;
            this.AreaInSqKm= csvusCensus.Area;
            this.DensityPerSqkm = csvusCensus.PopulationDensity;
            this.HousingDensity = csvusCensus.HousingDensity;
        }

    public long getPopulation() {
        return Population;
    }

    public void setPopulation(long population) {
        Population = population;
    }

    public long getAreaInSqKm() {
        return AreaInSqKm;
    }

    public void setAreaInSqKm(long areaInSqKm) {
        AreaInSqKm = areaInSqKm;
    }

    public long getDensityPerSqkm() {
        return DensityPerSqkm;
    }

    public void setDensityPerSqkm(long densityPerSqkm) {
        DensityPerSqkm = densityPerSqkm;
    }

    public Object getCensusDTO(CensusAnalyser.Country country) {
        if (country.equals(CensusAnalyser.Country.INDIA))
            return new CsvStatesCensus(State, Population, AreaInSqKm, DensityPerSqkm);
        if (country.equals(CensusAnalyser.Country.US))
            return new CSVUSCensus(StateCode, State, Population, AreaInSqKm, DensityPerSqkm);
        return null;
    }
}