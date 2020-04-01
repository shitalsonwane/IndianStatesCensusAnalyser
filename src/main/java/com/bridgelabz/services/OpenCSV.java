package com.bridgelabz.services;

import com.bridgelabz.exception.CSVBuilderException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
//csvbuld is seprated from analyser
public class OpenCSV extends CSV_Interface {
    public static <E> Iterator<E> CSVfileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        return getCSVToBeen(reader,csvClass).iterator();
    }

    public static <E> CsvToBean<E> getCSVToBeen(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean;
        } catch (RuntimeException e) {
            throw new CSVBuilderException("Check delimiters and header", CSVBuilderException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
        }
    }
}
