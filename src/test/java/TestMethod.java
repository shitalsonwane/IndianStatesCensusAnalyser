import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.model.CSVStatesPojoClass;
import com.bridgelabz.model.CsvStatesCensus;
import com.bridgelabz.services.StatesCensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class TestMethod {

    @Test   //check records match
    public void givenNumberOfRecords_WhenMatched_ShouldReturnTrue() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            StatesCensusAnalyser stateCensusAnalyzer = new StatesCensusAnalyser(CSV_FILE_PATH, CsvStatesCensus.class);
            int numberOfRecords = stateCensusAnalyzer.loadRecords();
            Assert.assertEquals(29, numberOfRecords);
        }catch(CSVBuilderException e){
        }
    }
    @Test   //check exception if file is incorrect
    public void givenFileName_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/stateCensus.csv";
        StatesCensusAnalyser stateCensusAnalyzer = new StatesCensusAnalyser(CSV_FILE_PATH, CsvStatesCensus.class);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   //Check exception if file type wrong
    public void givenFileType_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.txt";
        StatesCensusAnalyser stateCensusAnalyzer = new StatesCensusAnalyser(CSV_FILE_PATH, CsvStatesCensus.class);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   // handle delimiter exception
    public void givenFile_WhenDelimiterIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData1.csv";
        StatesCensusAnalyser stateCensusAnalyzer = new StatesCensusAnalyser(CSV_FILE_PATH, CsvStatesCensus.class);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test   //handle incorrect Header Exception
    public void givenFile_WhenHeaderIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData1.csv";
        StatesCensusAnalyser stateCensusAnalyzer = new StatesCensusAnalyser(CSV_FILE_PATH, CsvStatesCensus.class);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }
    //Test For State Code Csv File
    @Test   //check records match
    public void givenNumberOfRecordsOfStateCode_WhenMatched_ShouldReturnTrue() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            StatesCensusAnalyser stateCensusAnalyzer = new StatesCensusAnalyser(CSV_FILE_PATH, CSVStatesPojoClass.class);
            int numberOfRecords = stateCensusAnalyzer.loadData();
            Assert.assertEquals(37, numberOfRecords);
        }catch(CSVBuilderException e){
        }
    }
    @Test   //check exception if file is incorrect
    public void givenFileNameOfStateCode_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/stateCode.csv";
        StatesCensusAnalyser stateCensusAnalyzer = new StatesCensusAnalyser(CSV_FILE_PATH, CSVStatesPojoClass.class);
        try {
            stateCensusAnalyzer.loadData();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   //Check exception if file type wrong
    public void givenFileTypeOfStateCode_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.txt";
        StatesCensusAnalyser stateCensusAnalyzer = new StatesCensusAnalyser(CSV_FILE_PATH, CSVStatesPojoClass.class);
        try {
            stateCensusAnalyzer.loadData();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   // handle delimiter exception
    public void givenFileOfStateCode_WhenDelimiterIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode1.csv";
        StatesCensusAnalyser stateCensusAnalyzer = new StatesCensusAnalyser(CSV_FILE_PATH, CSVStatesPojoClass.class);
        try {
            stateCensusAnalyzer.loadData();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }
    @Test   //handle incorrect Header Exception
    public void givenFileOfStateCode_WhenHeadersIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode1.csv";
        StatesCensusAnalyser stateCensusAnalyzer = new StatesCensusAnalyser(CSV_FILE_PATH,CSVStatesPojoClass.class);
        try {
            stateCensusAnalyzer.loadData();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }
    @Test
    public void givenCensusData_WhenSorted_ShouldReturnSortedList() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            StatesCensusAnalyser stateCensusAnalyzer = new StatesCensusAnalyser(CSV_FILE_PATH, CsvStatesCensus.class);
            stateCensusAnalyzer.loadRecords();
            String SortedData = stateCensusAnalyzer.SortedCensusData();
            CsvStatesCensus[] censusCSV = new Gson().fromJson(SortedData, CsvStatesCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CSVBuilderException e){
        }
    }
    @Test
    public void givenStateCodeData_WhenSorted_ShouldReturnSortedList(){
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            StatesCensusAnalyser stateCensusAnalyzer = new StatesCensusAnalyser(CSV_FILE_PATH, CSVStatesPojoClass.class);
            stateCensusAnalyzer.loadData();
            String SortedData = stateCensusAnalyzer.SortedStateCodeData();
            CSVStatesPojoClass[] StateCodes = new Gson().fromJson(SortedData, CSVStatesPojoClass[].class);
            Assert.assertEquals("AD", StateCodes[0].StateCode);
        }catch(CSVBuilderException e){
        }
    }
}
