import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.services.StatesCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class TestMethod {
    StatesCensusAnalyser censusAnalyser = new StatesCensusAnalyser();
    @Test   //check records match
    public void givenStateCensusAnalyserFile_WhenTrue_ReturnNumberOfRecordShouldMatch() throws Exception {
        Integer record = censusAnalyser.readFile("src/test/resources/StateCensusData.csv");
        Assert.assertEquals((Integer) 29, record);
    }
    @Test   //check exception if file is incorrect
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException() throws Exception {
        try {
            censusAnalyser.readFile("src/test/resources/StateCensus.csv");
        }
        catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND,e.exceptionType);
        }
    }
    @Test   //Check exception if file type wrong
    public void givenStateCensusAnalyserFile_WhenImproperFileExtension_ReturnsException() throws Exception {
        try {
            censusAnalyser.readFile("src/test/resources/StateCensusData.txt");
        }
        catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND,e.exceptionType);
        }
    }
    @Test   // handle delimiter exception
    public void givenStateCensusAnalyserFile_WhenIncorrectDelimiters_ReturnsException()throws Exception {
        try {
            censusAnalyser.readFile("src/test/resources/StateCensusData1.csv");
        }
        catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT,e.exceptionType);
        }
    }
    @Test   //handle incorrect Header Exception
    public void givenStateCensusAnalyserFile_WhenIncorrectHeader_ReturnsException() throws Exception {
        try {
            censusAnalyser.readFile("src/test/resources/StateCensusData1.csv");
        }
        catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT,e.exceptionType);
        }
    }
    //Test For State Code Csv File
    @Test   //check records match
    public void givenStateCode_WhenTrue_ReturnNumberOfRecordShouldMatch() throws Exception {
        Integer result = censusAnalyser.loadIndianStateCodeData("src/test/resources/StateCode.csv");
        Assert.assertEquals((Integer) 37, result);
    }
    @Test   //check exception if file is incorrect
    public void givenStateCode_WhenImproperFileName_ReturnException() throws Exception {
        try {
            censusAnalyser.loadIndianStateCodeData("src/test/resources/Statecode.csv");
        }
        catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   //Check exception if file type wrong
    public void givenStateCode_WhenImproperFileType_ReturnException() throws Exception {
        try {
            censusAnalyser.loadIndianStateCodeData("src/test/resources/StateCode.txt");
        }
        catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   // handle delimiter exception
    public void givenStateCode_WhenImproperDelimiter_ReturnException() throws Exception {
        try {
            censusAnalyser.loadIndianStateCodeData("src/test/resources/StateCode1.csv");
        }
        catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }
    @Test   //handle incorrect Header Exception
    public void givenStateCode_WhenImproperHeader_ReturnException() throws Exception {
        try {
            censusAnalyser.readFile("src/test/resources/StateCode2.csv");
        }
        catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }
}
