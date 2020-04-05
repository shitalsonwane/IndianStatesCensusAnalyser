package com.bridgelabz.dao;

import com.bridgelabz.dto.CSVStatesPojoClass;
import com.bridgelabz.dto.CsvStatesCensus;

public class CensusDAO {
    public String State;
    public long Population;
    public long AreaInSqKm;
    public int DensityPerSqkm;
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
}
