package com.bridgelabz.services;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

// Interface used for abstraction And overriding
public interface CSV_Interface {
    <E> Iterator getIterator(Reader reader, Class<E> csvClass) ;
     <E> List getList(Reader reader, Class<E> csvClass) ;
}
