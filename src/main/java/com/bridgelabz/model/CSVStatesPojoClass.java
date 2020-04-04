package com.bridgelabz.model;
import com.opencsv.bean.CsvBindByName;

public class CSVStatesPojoClass {
    @CsvBindByName(column = "SrNo")
    public  int SrNo;

    @CsvBindByName(column = "StateName")
    public String State;

    @CsvBindByName(column = "StateCode")
    public static String StateCode;

    @CsvBindByName(column = "TIN")
    public int TIN;
    public CSVStatesPojoClass(int srNo, String stateName, String stateCode, int TIN) {
        SrNo = srNo;
        State = stateName;
        StateCode = stateCode;
        this.TIN = TIN;
    }

    @Override
    public String toString() {
        return "CSVStatesPojoClass{" +
                "SrNo=" + SrNo +
                ", State='" + State + '\'' +
                ", StateCode='" + StateCode + '\'' +
                ", TIN=" + TIN +
                '}';
    }
}
