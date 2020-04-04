import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.bridgelabz.model.CSVStatesPojoClass;
import com.bridgelabz.model.CsvStatesCensus;
import com.bridgelabz.services.StatesCensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class TestMethod {
    StatesCensusAnalyser stateCensusAnalyzer=new StatesCensusAnalyser();

    @Test   //check records match
    public void givenNumberOfRecords_WhenMatched_ShouldReturnTrue() throws StatesCensusAnalyserException {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            int numberOfRecords = stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        } catch (StatesCensusAnalyserException e) {
        }
    }
    @Test   //check exception if file is incorrect
    public void givenFileName_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/stateCensus.csv";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   //Check exception if file type wrong
    public void givenFileType_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.txt";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   // handle delimiter exception
    public void givenFile_WhenDelimiterIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData1.csv";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test   //handle incorrect Header Exception
    public void givenFile_WhenHeaderIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData1.csv";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }
    //Test For State Code Csv File
    @Test   //check records match
    public void givenNumberOfRecordsOfStateCode_WhenMatched_ShouldReturnTrue() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            int numberOfRecords = stateCensusAnalyzer.loadData(CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfRecords);
        } catch (StatesCensusAnalyserException e) {
        }
    }
    @Test   //check exception if file is incorrect
    public void givenFileNameOfStateCode_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/stateCode.csv";
        try {
            stateCensusAnalyzer.loadData(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   //Check exception if file type wrong
    public void givenFileTypeOfStateCode_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.txt";
        try {
            stateCensusAnalyzer.loadData(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   // handle delimiter exception
    public void givenFileOfStateCode_WhenDelimiterIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode1.csv";
        try {
            stateCensusAnalyzer.loadData(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }
    @Test   //handle incorrect Header Exception
    public void givenFileOfStateCode_WhenHeadersIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode1.csv";
        try {
            stateCensusAnalyzer.loadData(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }
    @Test   //sort state
    public void givenCensusData_WhenSorted_ShouldReturnSortedList() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
            String SortedData = stateCensusAnalyzer.SortedStateCensusData();
            CsvStatesCensus[] censusCSV = new Gson().fromJson(SortedData, CsvStatesCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].State);
        } catch (StatesCensusAnalyserException e) {
        }
    }
    @Test   //sort state code
    public void givenStateCodeData_WhenSorted_ShouldReturnSortedList(){
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            stateCensusAnalyzer.loadData(CSV_FILE_PATH);
            String SortedData = stateCensusAnalyzer.SortedStateCensusData();
            CSVStatesPojoClass[] StateCodes = new Gson().fromJson(SortedData, CSVStatesPojoClass[].class);
            Assert.assertEquals("AD", StateCodes[0].StateCode);
        } catch (StatesCensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    @Test   //Population Wise Sorting
    public void givenTheStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.getPopulationWiseSortedCensusData();
            CsvStatesCensus[] csvStatesCensus = new Gson().fromJson(sortedCensusData, CsvStatesCensus[].class);
            Assert.assertEquals(199812341, csvStatesCensus[0].Population);
        } catch (StatesCensusAnalyserException e) {
            e.getStackTrace();
        }
    }
    @Test   //DensityWise Sorting
    public void givenTheStateCensusData_WhenSortedOnDensityPerSqKm_ShouldReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.getDensityWiseSortedCensusData();
            CsvStatesCensus[] csvStateCensuses = new Gson().fromJson(sortedCensusData, CsvStatesCensus[].class);
            Assert.assertEquals(1102, csvStateCensuses[0].DensityPerSqkm);
        } catch (StatesCensusAnalyserException e) {
            e.getStackTrace();
        }
    }
    @Test   //Area wise Sorting
    public void givenTheStateCensusData_WhenSortedOnAreaInPerSqKm_ShouldReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.getAreaWiseSortedCensusData();
            CsvStatesCensus[] csvStateCensuses = new Gson().fromJson(sortedCensusData, CsvStatesCensus[].class);
            Assert.assertEquals(342239, csvStateCensuses[0].AreaInSqKm);
        } catch (StatesCensusAnalyserException e) {
            e.getStackTrace();
        }
    }
}
