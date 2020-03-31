package com.bridgelabz.services;
import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.bridgelabz.model.CsvStatesCensus;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import static java.nio.file.Files.newBufferedReader;

public class StatesCensusAnalyser {
    public static void main(String args[]){
        System.out.println("---Welcome To Indian States Census Analyser---");
    }
    public static String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    int count=0;
    public StatesCensusAnalyser(String path) {
        CSV_FILE_PATH = path;
    }
    public int loadData() throws StatesCensusAnalyserException{
        try(Reader reader = newBufferedReader(Paths.get(CSV_FILE_PATH)); ){
            CsvToBean<CsvStatesCensus> csvStateCensuses = new CsvToBeanBuilder(reader)
                    .withType(CsvStatesCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CsvStatesCensus> csvStateCensusIterator = csvStateCensuses.iterator();
            while (csvStateCensusIterator.hasNext()) {
                CsvStatesCensus csvStateCensus = csvStateCensusIterator.next();
                System.out.println("State: " + csvStateCensus.getState());
                System.out.println("Area: " + csvStateCensus.getAreaInSqKm());
                System.out.println("Density: " + csvStateCensus.getDensityPerSqKm());
                System.out.println("Population: " + csvStateCensus.getPopulation());
                count++;
            }
        }
        catch (NoSuchFileException e) {
            throw new StatesCensusAnalyserException(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        }
        catch(RuntimeException e) {
            throw new StatesCensusAnalyserException(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
        }
        catch (IOException e){
            e.getStackTrace();
        }
        return count;
    }
}
