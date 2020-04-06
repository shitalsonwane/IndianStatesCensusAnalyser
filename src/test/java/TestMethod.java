import com.bridgelabz.dto.CSVStatesPojoClass;
import com.bridgelabz.dto.CSVUSCensus;
import com.bridgelabz.dto.CsvStatesCensus;
import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.bridgelabz.services.CensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static com.bridgelabz.services.CensusAnalyser.Country.INDIA;

public class TestMethod {
    CensusAnalyser stateCensusAnalyzer=new CensusAnalyser(INDIA);

    @Test   //check records match
    public void givenNumberOfRecords_WhenMatched_ShouldReturnTrue() throws IOException {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            int numberOfRecords = stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        } catch (StatesCensusAnalyserException e) {
        }
    }
    @Test   //check exception if file is incorrect
    public void givenFileName_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/stateCensus.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   //Check exception if file type wrong
    public void givenFileType_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.txt";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   // handle delimiter exception
    public void givenFile_WhenDelimiterIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData1.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test   //handle incorrect Header Exception
    public void givenFile_WhenHeaderIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData1.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }
    //Test For State Code Csv File
    @Test   //check records match
    public void givenNumberOfRecordsOfStateCode_WhenMatched_ShouldReturnTrue() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            int numberOfRecords = stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfRecords);
        } catch (StatesCensusAnalyserException e) {
        }
    }
    @Test   //check exception if file is incorrect
    public void givenFileNameOfStateCode_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/stateCode.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   //Check exception if file type wrong
    public void givenFileTypeOfStateCode_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.txt";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   // handle delimiter exception
    public void givenFileOfStateCode_WhenDelimiterIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode1.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }
    @Test   //handle incorrect Header Exception
    public void givenFileOfStateCode_WhenHeadersIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode1.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test   //sort state
    public void givenCensusData_WhenSorted_ShouldReturnSortedList() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
        stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        String sortedCensusData = stateCensusAnalyzer.getSortedStateCensusData(CensusAnalyser.SortingMode.STATE);
        CsvStatesCensus[] csvStatesCensus = new Gson().fromJson(sortedCensusData, CsvStatesCensus[].class);
        Assert.assertEquals("Andhra Pradesh", csvStatesCensus[0].State);
    } catch (StatesCensusAnalyserException e) {
        e.getStackTrace();
    }
    }
    @Test   //sort state code
    public void givenStateCodeData_WhenSorted_ShouldReturnSortedList() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.getSortedStateCensusData(CensusAnalyser.SortingMode.STATE);
            CSVStatesPojoClass[] StateCodes = new Gson().fromJson(sortedCensusData, CSVStatesPojoClass[].class);
            Assert.assertEquals("AD", CSVStatesPojoClass.StateCode);
        } catch (StatesCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test   //Population Wise Sorting of India
    public void givenTheStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
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
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.getSortedStateCensusData(CensusAnalyser.SortingMode.DENSITY);
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
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.getSortedStateCensusData(CensusAnalyser.SortingMode.AREA);
            CsvStatesCensus[] csvStateCensuses = new Gson().fromJson(sortedCensusData, CsvStatesCensus[].class);
            Assert.assertEquals(342239, csvStateCensuses[0].AreaInSqKm);
        } catch (StatesCensusAnalyserException e) {
            e.getStackTrace();
        }
    }
    @Test   //Record of UsState
    public void givenUSCensusAnalyserFile_WhenTrue_NumberOfRecordShouldMatch() throws IOException {
        final String CSV_FILE_PATH = "src/test/resources/USCensusData.csv";
        try {
            int numberOfRecords = stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.US, CSV_FILE_PATH);
            Assert.assertEquals(51, numberOfRecords);
        } catch (StatesCensusAnalyserException e) {
        }
    }
    @Test   //Population Wise Sorting of US
    public void givenTheUSCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/USCensusData.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.US, CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.getPopulationWiseSortedCensusData();
            CSVUSCensus[] csvUsCensus = new Gson().fromJson(sortedCensusData,CSVUSCensus[].class);
            Assert.assertEquals("California",csvUsCensus[0].State);
        } catch (StatesCensusAnalyserException e) {
            e.getStackTrace();
        }
    }
    @Test
    public void givenTheUSCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/USCensusData.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.US, CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.getSortedStateCensusData(CensusAnalyser.SortingMode.DENSITY);
            CSVUSCensus[] csvUsCensus = new Gson().fromJson(sortedCensusData,CSVUSCensus[].class);
            Assert.assertEquals(3805,csvUsCensus[0].PopulationDensity);
        } catch (StatesCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTheUSCensusData_WhenSortedOnArea_ShouldReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/USCensusData.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.US, CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.getSortedStateCensusData(CensusAnalyser.SortingMode.AREA);
            CSVUSCensus[] csvusCensus = new Gson().fromJson(sortedCensusData, CSVUSCensus[].class);
            Assert.assertEquals(1723338, csvusCensus[0].Area);
        } catch (StatesCensusAnalyserException e) {
            e.getStackTrace();
        }
    }


}

