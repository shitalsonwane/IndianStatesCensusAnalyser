package com.bridgelabz.model;
import com.opencsv.bean.CsvBindByName;

public class CSVStatesPojoClass {
    @CsvBindByName(column = "SrNo")
    public  int SrNo;

    @CsvBindByName(column = "StateName")
    public String StateName;

    @CsvBindByName(column = "StateCode")
    public static String StateCode;

    @CsvBindByName(column = "TIN")
    public int TIN;
}
