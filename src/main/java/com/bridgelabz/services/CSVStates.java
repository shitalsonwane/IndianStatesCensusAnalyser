package com.bridgelabz.services;

import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.bridgelabz.model.CSVStatesPojoClass;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
public class CSVStates {
    private static String FILE_PATH = "src/test/resources/StateCode.csv";
    int count = 0;
    public CSVStates(String Path) {
        FILE_PATH = Path;
    }

    public int LoadCSVData() throws StatesCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));) {
            CsvToBean<CSVStatesPojoClass> csvStateCensuses = new CsvToBeanBuilder(reader)
                    .withType(CSVStatesPojoClass.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CSVStatesPojoClass> csvStatesPojoClassIterator = csvStateCensuses.iterator();
            while (csvStatesPojoClassIterator.hasNext()) {
                CSVStatesPojoClass csvStatesPojoClass = csvStatesPojoClassIterator.next();
                System.out.println("SrNo :" + csvStatesPojoClass.getSrNo());
                System.out.println("StateName :" + csvStatesPojoClass.getStateName());
                System.out.println("StateCode :" + csvStatesPojoClass.getStateCode());
                System.out.println("TIN :" + csvStatesPojoClass.getTIN());
                System.out.println("=========================");
                count++;

            }
        }
        catch (NoSuchFileException e) {
                 throw new StatesCensusAnalyserException(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        }
        catch (IOException e) {
                    e.printStackTrace();
        }
        return count;
    }
}