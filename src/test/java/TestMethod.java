import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.bridgelabz.services.CSVStates;
import com.bridgelabz.services.StatesCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestMethod {
    public static  String CSV_FILE_PATH ="src/test/resources/StateCensusData.csv" ;
    public static String FILE_PATH = "src/test/resources/StateCode.csv";

    @Test
        public void givenStateCensusAnalyserFile_WhenTrue_ReturnNumberOfRecordShouldMatch() throws StatesCensusAnalyserException {
        CSV_FILE_PATH ="src/test/resources/StateCensusData.csv" ;
        StatesCensusAnalyser censusAnalyser = new StatesCensusAnalyser( CSV_FILE_PATH);
            int count=censusAnalyser.loadData();
            Assert.assertEquals(29,count);
        }
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException() {
        try {
            CSV_FILE_PATH ="src/test/resources/stateCensusData.csv";
            StatesCensusAnalyser censusAnalyser = new StatesCensusAnalyser(CSV_FILE_PATH);
            censusAnalyser.loadData();
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND,e.exceptionType);
        }
    }
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileExtension_ReturnsException() {
        CSV_FILE_PATH ="src/test/resources/StateCensusData.txt";
        StatesCensusAnalyser censusAnalyser = new StatesCensusAnalyser(CSV_FILE_PATH);
        try {
            censusAnalyser.loadData();
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND,e.exceptionType);
        }
    }
    @Test
    public void givenStateCensusAnalyserFile_WhenIncorrectDelimiters_ReturnsException() {
        CSV_FILE_PATH ="src/test/resources/StateCensusData1.csv";
        StatesCensusAnalyser censusAnalyser = new StatesCensusAnalyser(CSV_FILE_PATH);
        try {
            censusAnalyser.loadData();
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT,e.exceptionType);
        }
    }
    @Test
    public void givenStateCensusAnalyserFile_WhenIncorrectHeader_ReturnsException()  {
        CSV_FILE_PATH = "src/test/resources/StateCensusData1.csv";
        StatesCensusAnalyser censusAnalyser = new StatesCensusAnalyser(CSV_FILE_PATH);
        try {
            censusAnalyser.loadData();
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT,e.exceptionType);
        }
    }
    @Test
    public void givenStateCode_WhenTrue_NumberOfRecordShouldMatch() throws IOException {
        FILE_PATH = "src/test/resources/StateCode.csv";
        CSVStates csvStates = new CSVStates(FILE_PATH);
        int count = csvStates.LoadCSVData();
        Assert.assertEquals(37, count);
    }
}
