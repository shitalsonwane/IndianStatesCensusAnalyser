package com.bridgelabz.exception;

public class CSVBuilderException extends Exception{
    public enum ExceptionType{
        FILE_NOT_FOUND,
        DELIMITER_AND_HEADER_INCORRECT
    }
    public ExceptionType exceptionType;
    public CSVBuilderException(String message,ExceptionType exceptionType){
        super(message);
        this.exceptionType = exceptionType;
    }
}
