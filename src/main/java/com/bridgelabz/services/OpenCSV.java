package com.bridgelabz.services;

import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSV extends CSV_Interface {
    public static <E> Iterator<E> CSVfileIterator(Reader reader, Class<E> csvClass) throws StatesCensusAnalyserException {
        return getCSVToBeen(reader,csvClass).iterator();
    }

    public static <E> CsvToBean<E> getCSVToBeen(Reader reader, Class<E> csvClass) throws StatesCensusAnalyserException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean;
        } catch (RuntimeException e) {
            throw new StatesCensusAnalyserException("Check delimiters and header", StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
        }
    }
}
