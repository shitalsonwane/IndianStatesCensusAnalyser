package com.bridgelabz.exception;

public class CSVBuilderException extends Exception{
    public enum ExceptionType{
        UNABLE_TO_PARSE
    }
    public ExceptionType exceptionType;
    public CSVBuilderException(String message,ExceptionType exceptionType){
        super(message);
        this.exceptionType = exceptionType;
    }
}
