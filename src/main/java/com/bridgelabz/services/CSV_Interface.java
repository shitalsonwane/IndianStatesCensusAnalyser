package com.bridgelabz.services;

import com.bridgelabz.exception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

// Interface used for abstraction And overriding
public interface CSV_Interface {
    public <E> Iterator<E> CSVfileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;
    public <E> List<E> getCSVfileList(Reader reader, Class<E> csvClass) throws CSVBuilderException;
}
