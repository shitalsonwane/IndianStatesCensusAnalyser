package com.bridgelabz.services;

import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.model.CSVStatesPojoClass;
import com.bridgelabz.model.CsvStatesCensus;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class StatesCensusAnalyser <E>{
    //VARIABLES
    private static String CSV_FILE_PATH;
    //private final Class<E> csvClass;
    //List<E> csvUserList = null;

    List<CsvStatesCensus> csvStatesCensusList = null;
    List<CSVStatesPojoClass> csvStatesPojoClassList = null;
    Map<String, CsvStatesCensus> csvStatesCensusMap = null;
    Map<String, CSVStatesPojoClass> csvStatesPojoClassMap = null;

    //Constructor to set pass and class
    public StatesCensusAnalyser() {
        //CSV_FILE_PATH = path;
        //csvClass = csvClss;
        this.csvStatesCensusMap = new HashMap<>();
        this.csvStatesPojoClassMap = new HashMap<>();
    }
    //METHOD TO LOAD RECORDS OF CSV FILE
    public  int loadRecords(String path) throws CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
            //csvUserList = csvBuilder.getList(reader, csvClass);
            //return csvUserList.size();
            Iterator<CsvStatesCensus> csvStatesCensusIterator = csvBuilder.getIterator(reader, CsvStatesCensus.class);
            while (csvStatesCensusIterator.hasNext()) {
                CsvStatesCensus csvStatesCensus = csvStatesCensusIterator.next();
                this.csvStatesCensusMap.put(csvStatesCensus.state, csvStatesCensus);
                csvStatesCensusList = csvStatesCensusMap.values().stream().collect(Collectors.toList());
            }
            int numberOfRecords = csvStatesCensusMap.size();
            return numberOfRecords;
        }
        catch (NoSuchFileException e) {
            throw new CSVBuilderException("Give proper file name and path", CSVBuilderException.ExceptionType.FILE_NOT_FOUND);
        }
        catch (RuntimeException e) {
            throw new CSVBuilderException("Check delimiters and headers", CSVBuilderException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        return 0;
    }
         //METHOD TO LOAD RECORDS OF STATE CODE
    public int loadData(String path) throws CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
            //csvUserList = csvBuilder.getList(reader, csvClass);
            //return csvUserList.size();
            Iterator<CSVStatesPojoClass> csvStatesCensusIterator = csvBuilder.getIterator(reader, CsvStatesCensus.class);
            while (csvStatesCensusIterator.hasNext()) {
                CSVStatesPojoClass csvStatesPojoClass = csvStatesCensusIterator.next();
                this.csvStatesPojoClassMap.put(csvStatesPojoClass.StateCode, csvStatesPojoClass);
                csvStatesPojoClassList= csvStatesPojoClassMap.values().stream().collect(Collectors.toList());
            }
            int numberOfRecords = csvStatesPojoClassMap.size();
            return numberOfRecords;
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("Give proper file name and path", CSVBuilderException.ExceptionType.FILE_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("Check delimiters and headers", CSVBuilderException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    // Sorting the StateCensus Data
    public String SortedCensusData() throws CSVBuilderException{
        //Comparator<CsvStatesCensus> CSVComparator = Comparator.comparing(census -> census.state);
        //this.sort((Comparator<E>) CSVComparator);
        //String SortedCSVJson = new Gson().toJson(csvUserList);
        //return SortedCSVJson;
        if (csvStatesCensusList == null || csvStatesCensusList.size() == 0) {
            throw new CSVBuilderException( "No census data", CSVBuilderException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CsvStatesCensus> comparator = Comparator.comparing(csvStatesCensus -> csvStatesCensus.state);
        this.sortData(comparator, csvStatesCensusList);
        String sortedStateCensusJson = new Gson().toJson(csvStatesCensusList);
        return sortedStateCensusJson;
    }
    // Sorting the State Code data
    public String SortedStateCodeData() throws CSVBuilderException {
        //Comparator<CSVStatesPojoClass> CodeComparator = Comparator.comparing(code -> code.StateCode);
        //this.sort((Comparator<E>) CodeComparator);
        //String SortedCodeJson = new Gson().toJson(csvUserList);
        //return SortedCodeJson;
        if (csvStatesPojoClassList == null || csvStatesPojoClassList.size() == 0) {
            throw new CSVBuilderException( "No census data", CSVBuilderException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStatesPojoClass> comparator = Comparator.comparing(csvStatesPojoClass -> csvStatesPojoClass.StateCode);
        this.sortData(comparator, csvStatesPojoClassList);
        String sortedStateCodeJson = new Gson().toJson(csvStatesPojoClassList);
        return sortedStateCodeJson;
    }
    // sorting function which used by above function for sorting data
    private<E> void sortData(Comparator<E> csvComparator, List<E> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                E census1 = list.get(j);
                E census2 = list.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    list.set(j, census2);
                    list.set(j + 1, census1);
                }
            }
        }
    }
}
