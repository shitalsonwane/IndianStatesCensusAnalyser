package com.bridgelabz.services;

import com.bridgelabz.exception.CSVBuilderException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

//csvbuild is seperated from analyser
public class OpenCSV implements CSV_Interface {
    public <E> Iterator<E>  CSVfileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        return this.getCSVToBeen(reader,csvClass).iterator();
    }

    public <E> List<E> getCSVfileList(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        return this.getCSVToBeen(reader,csvClass).parse();
    }

    private  <E> CsvToBean<E> getCSVToBeen(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            CsvToBean<E> csvToBean = csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
            return csvToBean;
        } catch (RuntimeException e) {
            throw new CSVBuilderException("Check delimiters and header", CSVBuilderException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
        } catch (Exception e){
            e.printStackTrace();
        }
        return (null);
    }
}
