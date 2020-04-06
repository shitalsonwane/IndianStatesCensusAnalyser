package com.bridgelabz.services;

import com.bridgelabz.adapter.CensusAdapterFactory;
import com.bridgelabz.dao.CensusDAO;
import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser <E>{
    private Country country;
    List<CensusDAO> censusList = null;
    Map<String, CensusDAO> censusDAOMap = null;
    //Constructor to set pass and class
    public CensusAnalyser(Country country) {
        this.country = country;
    }

    public int loadStateCensusCSVData(Country country, String... csvFilePath) throws StatesCensusAnalyserException {
        censusDAOMap = CensusAdapterFactory.getCensusData(country, csvFilePath);
        censusList = censusDAOMap.values().stream().collect(Collectors.toList());
        return censusDAOMap.size();
    }
    // METHOD TO SORT STATE
    public String getSortedStateCensusData(SortingMode mode) throws StatesCensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
        throw new StatesCensusAnalyserException("No census data",StatesCensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        ArrayList arrayList = censusDAOMap.values().stream()
                .sorted(CensusDAO.getSortComparator(mode))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(arrayList);

    }
    //METHOD TO SORT STATE CENSUS DATA BY POPULATION
    public String getPopulationWiseSortedCensusData() throws StatesCensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new StatesCensusAnalyserException("No census data",StatesCensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.Population);
        this.sortData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }/*
    //METHOD TO SORT STATE CENSUS DATA BY DENSITY WISE
    public String getDensityWiseSortedCensusData() throws StatesCensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new StatesCensusAnalyserException( "No census data", StatesCensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.DensityPerSqkm);
        this.sortData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }
    //METHOD TO SORT STATE CENSUS DATA BY AREA
    public String getAreaWiseSortedCensusData() throws StatesCensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new StatesCensusAnalyserException("No census data", StatesCensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.AreaInSqKm);
        this.sortData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }*/
    // sorting function which used by above function for sorting data
    private void sortData(Comparator<CensusDAO> csvComparator) {
        for (int i = 0; i < censusList.size() - 1; i++) {
            for (int j = 0; j < censusList.size() - i - 1; j++) {
                CensusDAO census1 = censusList.get(j);
                CensusDAO census2 = censusList.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    censusList.set(j, census2);
                    censusList.set(j + 1, census1);
                }
            }
        }
    }
    public enum SortingMode {STATE, POPULATION, DENSITY, AREA}
    public enum Country {INDIA, US}
}
