package com.bridgelabz.dto;
import com.opencsv.bean.CsvBindByName;

public class CSVStatesPojoClass {
    @CsvBindByName(column = "SrNo")
    public  int SrNo;
    @CsvBindByName(column = "State")
    public String State;
    @CsvBindByName(column = "StateCode")
    public static String StateCode;
    @CsvBindByName(column = "TIN")
    public int TIN;

    public CSVStatesPojoClass(int srNo, String state, String stateCode, int TIN) {
        SrNo = srNo;
        State = state;
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
