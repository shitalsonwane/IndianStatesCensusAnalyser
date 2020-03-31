package com.bridgelabz.services;

import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.bridgelabz.model.CSVStatesPojoClass;
import com.bridgelabz.model.CsvStatesCensus;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class StatesCensusAnalyser <E> {
    public int readFile(String filePath) throws StatesCensusAnalyserException {
        int count = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
        ) {
            Iterator<CsvStatesCensus> stateCSVIterator = this.getCSVfileIterator(reader, CsvStatesCensus.class);
            Iterable<CsvStatesCensus> csvIterable = () -> stateCSVIterator;
            int numOfRecords = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            int numOfrecords;
            return numOfRecords;
        } catch (NoSuchFileException e) {
            throw new StatesCensusAnalyserException("Enter correct file name and type", StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new StatesCensusAnalyserException("Check delimiters and header", StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return count;
    }
        public Integer loadIndianStateCodeData (String csvFilePath) throws StatesCensusAnalyserException {
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                Iterator<CSVStatesPojoClass> statesCSVIterator = this.getCSVfileIterator(reader, CSVStatesPojoClass.class);
                Iterable<CSVStatesPojoClass> iterableStateCode = () -> statesCSVIterator;
                int countRecord = (int) StreamSupport.stream(iterableStateCode.spliterator(), false).count();
                return countRecord;
            } catch (NoSuchFileException e) {
                throw new StatesCensusAnalyserException("Enter correct file name and type", StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
            } catch (RuntimeException e) {
                throw new StatesCensusAnalyserException("Check delimiters and header", StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return (null);
        }

        private <E > Iterator < E > getCSVfileIterator(Reader reader, Class < E > csvClass) throws
        StatesCensusAnalyserException {
            try {
                CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
                csvToBeanBuilder.withType(csvClass);
                csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                CsvToBean<E> csvToBean = csvToBeanBuilder.build();
                return csvToBean.iterator();
            } catch (RuntimeException e) {
                throw new StatesCensusAnalyserException("Check delimiter and header", StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return (null);
        }
}
