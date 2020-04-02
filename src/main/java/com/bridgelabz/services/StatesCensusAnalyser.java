package com.bridgelabz.services;

import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.model.CSVStatesPojoClass;
import com.bridgelabz.model.CsvStatesCensus;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class StatesCensusAnalyser {
    OpenCSV openCSV = new OpenCSV();
    public Integer readFile(String filePath) throws CSVBuilderException{
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))
        ) {
            List<CsvStatesCensus> listCSVfile = openCSV.getCSVfileList(reader,CsvStatesCensus.class);
            return listCSVfile.size();

        }
        catch (NoSuchFileException e) {
            throw new CSVBuilderException("Enter a right file name and type", CSVBuilderException.ExceptionType.FILE_NOT_FOUND);
        }
        catch (RuntimeException e) {
            throw new CSVBuilderException("Check delimiter and header", CSVBuilderException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        return (null);
    }
    public Integer loadIndianStateCodeData (String csvFilePath) throws CSVBuilderException{
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            List<CSVStatesPojoClass> listCSVfile = openCSV.getCSVfileList(reader,CSVStatesPojoClass.class);
            return listCSVfile.size();
        }
        catch (NoSuchFileException e) {
            throw new CSVBuilderException("Enter a right file name and type", CSVBuilderException.ExceptionType.FILE_NOT_FOUND);
        }
        catch (RuntimeException e) {
            throw new CSVBuilderException("Check delimiter and header", CSVBuilderException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        return (null);
    }

}
