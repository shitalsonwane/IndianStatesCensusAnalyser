package com.bridgelabz.services;

import com.bridgelabz.exception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

// Interface used for abstraction And overriding
public interface CSV_Interface {
    <E> Iterator <E> getIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;
     <E> List <E>getList(Reader reader, Class<E> csvClass) throws CSVBuilderException;
}
