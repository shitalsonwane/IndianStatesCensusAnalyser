package com.bridgelabz.exception;

public class StatesCensusAnalyserException extends Exception{
    public enum ExceptionType{
        FILE_NOT_FOUND
    }
    public ExceptionType exceptionType;

    public StatesCensusAnalyserException(ExceptionType exceptionType){
        this.exceptionType = exceptionType;
    }
}
