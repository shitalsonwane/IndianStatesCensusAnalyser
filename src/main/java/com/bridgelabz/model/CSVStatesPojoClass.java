package com.bridgelabz.model;
import com.opencsv.bean.CsvBindByName;

public class CSVStatesPojoClass {
    @CsvBindByName(column = "SrNo")
    private String SrNo;

    @CsvBindByName(column = "StateName")
    private String StateName;

    @CsvBindByName(column = "StateCode")
    private String StateCode;

    @CsvBindByName(column = "TIN")
    private String TIN;
}
