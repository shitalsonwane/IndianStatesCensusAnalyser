package com.bridgelabz.services;

import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.bridgelabz.model.CSVStatesPojoClass;
import com.bridgelabz.model.CSVUSCensus;
import com.bridgelabz.model.CsvStatesCensus;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.nio.file.Files.newBufferedReader;

public class StatesCensusAnalyser <E>{
    List<CensusDAO> list = null;
    Map<String, CensusDAO> map = null;

    //Constructor to set pass and class
    public StatesCensusAnalyser() {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
    }
    //METHOD TO LOAD RECORDS OF CSV FILE
    public  int loadRecords(String path) throws StatesCensusAnalyserException {
        int numberOfRecords = 0;
        String extension = path.substring(path.lastIndexOf(".") + 1);
        if (!extension.equals("csv")) {
            throw new StatesCensusAnalyserException("Incorrect file type", StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        }
        try (Reader reader = newBufferedReader(Paths.get(path))) {
            OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<CsvStatesCensus> csvStatesCensusIterator = csvBuilder.getIterator(reader, CsvStatesCensus.class);
            Iterable<CsvStatesCensus> stateCensuses = () -> csvStatesCensusIterator;
            StreamSupport.stream(stateCensuses.spliterator(), false)
                    .forEach(csvStatesCensus -> map.put(CsvStatesCensus.State, new CensusDAO(csvStatesCensus)));
            list = map.values().stream().collect(Collectors.toList());
            numberOfRecords = map.size();
        }
        catch (NoSuchFileException e) {
            throw new StatesCensusAnalyserException("Give proper file name and path", StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        }
        catch (RuntimeException e) {
            throw new StatesCensusAnalyserException("Check delimiters and headers", StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
        }
        catch (IOException e) {
            e.getStackTrace();
        }
        catch (CSVBuilderException e){
            e.printStackTrace();
        }
        return numberOfRecords;
    }
    //METHOD TO LOAD RECORDS OF STATE CODE
    public int loadData(String path) throws StatesCensusAnalyserException {
        int numberOfRecords = 0;
        String extension = path.substring(path.lastIndexOf(".") + 1);
        if (!extension.equals("csv")) {
            throw new StatesCensusAnalyserException( "Incorrect file type",StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        }
        try (Reader reader = newBufferedReader(Paths.get(path))) {
            OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<CSVStatesPojoClass> csvStatesPojoClassIterator = csvBuilder.getIterator(reader, CSVStatesPojoClass.class);
            Iterable<CSVStatesPojoClass> stateCensuses = () -> csvStatesPojoClassIterator;
            StreamSupport.stream(stateCensuses.spliterator(), false)
                    .forEach(CSVStatesPojoClass -> map.put(CSVStatesPojoClass.State, new CensusDAO(CSVStatesPojoClass)));
            list = map.values().stream().collect(Collectors.toList());
            numberOfRecords = map.size();
        }
        catch (NoSuchFileException e) {
            throw new StatesCensusAnalyserException("Give proper file name and path", StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        }
        catch (RuntimeException e) {
            throw new StatesCensusAnalyserException("Check delimiters and headers", StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (CSVBuilderException e){
            e.printStackTrace();
        }
        return numberOfRecords;
    }
    //METHOD TO LOAD STATE CODE CSV DATA AND COUNT NUMBER OF RECORD IN CSV FILE
    public int loadUSCensusData(String path) throws StatesCensusAnalyserException {
        int count = 0;
        try (Reader reader = newBufferedReader(Paths.get(path))
        ) {
            CsvToBean<CSVUSCensus> csvusCensus = new CsvToBeanBuilder(reader)
                    .withType(CSVUSCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CSVUSCensus> csvusCensusIterator = csvusCensus.iterator();
            while (csvusCensusIterator.hasNext()) {
                CSVUSCensus csvUSCensus = csvusCensusIterator.next();
                System.out.println("State ID: " + csvUSCensus.StateID);
                System.out.println("State: " + csvUSCensus.State);
                System.out.println("Area: " + csvUSCensus.Area);
                System.out.println("Housing units: " + csvUSCensus.HousingUnits);
                System.out.println("Water area: " + csvUSCensus.WaterArea);
                System.out.println("Land Area: " + csvUSCensus.LandArea);
                System.out.println("Density: " + csvUSCensus.PopulationDensity);
                System.out.println("Population: " + csvUSCensus.Population);
                System.out.println("Housing Density: " + csvUSCensus.HousingDensity);
                System.out.println("==============================================");
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    // Sorting the State Data
    public String SortedStateCensusData() throws StatesCensusAnalyserException{
        if (list == null || list.size() == 0) {
            throw new StatesCensusAnalyserException( "No census data", StatesCensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> comparator = Comparator.comparing(CensusDAO ->CensusDAO.State);
        this.sortData(comparator);
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }
    //METHOD TO SORT STATE CENSUS DATA BY POPULATION
    public String getPopulationWiseSortedCensusData() throws StatesCensusAnalyserException {
        if (list == null || list.size() == 0) {
            throw new StatesCensusAnalyserException("No census data",StatesCensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.Population);
        this.sortData(censusComparator);
        Collections.reverse(list);
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }
    //METHOD TO SORT STATE CENSUS DATA BY DENSITY WISE
    public String getDensityWiseSortedCensusData() throws StatesCensusAnalyserException {
        if (list == null || list.size() == 0) {
            throw new StatesCensusAnalyserException( "No census data", StatesCensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.DensityPerSqkm);
        this.sortData(censusComparator);
        Collections.reverse(list);
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }
    //METHOD TO SORT STATE CENSUS DATA BY AREA
    public String getAreaWiseSortedCensusData() throws StatesCensusAnalyserException {
        if (list == null || list.size() == 0) {
            throw new StatesCensusAnalyserException("No census data", StatesCensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.AreaInSqKm);
        this.sortData(censusComparator);
        Collections.reverse(list);
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }
    // sorting function which used by above function for sorting data
    private void sortData(Comparator<CensusDAO> csvComparator) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                CensusDAO census1 = list.get(j);
                CensusDAO census2 = list.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    list.set(j, census2);
                    list.set(j + 1, census1);
                }
            }
        }
    }
}
