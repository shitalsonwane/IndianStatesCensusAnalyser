import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.bridgelabz.services.CSVStates;
import com.bridgelabz.services.StatesCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

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
    //Test cases for CSVSTATES Csv Files
    @Test   //Check Record should match
    public void givenStateCode_WhenTrue_NumberOfRecordShouldMatch() throws StatesCensusAnalyserException {
        FILE_PATH = "src/test/resources/StateCode.csv";
        CSVStates csvStates = new CSVStates(FILE_PATH);
        int count = csvStates.LoadCSVData();
        Assert.assertEquals(37, count);
    }
    @Test   //check exception if file is incurrect
    public void givenStateCode_WhenImproperFileName_ReturnException() {
        FILE_PATH = "src/test/resources/StateCod.csv";
        CSVStates csvStates = new CSVStates(FILE_PATH);
        try {
            csvStates.LoadCSVData();
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   //Check exception if file type wrong
    public void givenStateCode_WhenImproperFileType_ReturnException(){
        FILE_PATH = "/home/admin1/Desktop/CSVProgram/src/test/resources/StateCode.txt";
        CSVStates csvStates = new CSVStates(FILE_PATH);
        try {
            csvStates.LoadCSVData();
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test // handle delimiter exception
    public void givenStateCode_WhenImproperDelimiter_ReturnException(){
        FILE_PATH = "src/test/resources/StateCode1.csv";
        CSVStates csvStates = new CSVStates(FILE_PATH);
        try {
            csvStates.LoadCSVData();
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test   //handle incorrect Header Exception
    public void givenStateCode_WhenImproperHeader_ReturnException(){
        FILE_PATH = "src/test/resources/StateCode2.csv";
        CSVStates csvStates = new CSVStates(FILE_PATH);
        try {
            csvStates.LoadCSVData();
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }
}
