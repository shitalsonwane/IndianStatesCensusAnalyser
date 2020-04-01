package com.bridgelabz.services;

import com.bridgelabz.exception.StatesCensusAnalyserException;
import java.io.Reader;
import java.util.Iterator;

public class CSV_Interface {
    public <E> Iterator<E> getCSVfileIterator(Reader reader, Class<E> csvClass) throws StatesCensusAnalyserException {
        return null;
    }
}
