package com.bridgelabz.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

//csvbuild is seperated from analyser
public class OpenCSV implements CSV_Interface {
    @Override
    public <E> Iterator getIterator(Reader reader, Class<E> csvClass) {
        CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
                .withType(csvClass)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        Iterator<E> csvUserIterator = csvToBean.iterator();
        return csvUserIterator;
    }

    @Override
    public <E> List getList(Reader reader, Class<E> csvClass) {
        CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader)
                .withType(csvClass)
                .withIgnoreLeadingWhiteSpace(true);
        return csvToBeanBuilder.build().parse();
    }
}
