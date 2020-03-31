import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.bridgelabz.services.StatesCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class TestMethod {
    StatesCensusAnalyser censusAnalyser = new StatesCensusAnalyser();

    @Test   //check records match
        public void givenStateCensusAnalyserFile_WhenTrue_ReturnNumberOfRecordShouldMatch() throws StatesCensusAnalyserException {
        Integer record = censusAnalyser.readFile("src/test/resources/StateCensusData.csv");
        Assert.assertEquals((Integer) 29, record);
    }
    @Test   //check exception if file is incorrect
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException() {
        try {
            censusAnalyser.readFile("src/test/resources/StateCensusData.csv");
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND,e.exceptionType);
        }
    }
    @Test   //Check exception if file type wrong
    public void givenStateCensusAnalyserFile_WhenImproperFileExtension_ReturnsException() {
        try {
            censusAnalyser.readFile("src/test/resources/StateCensusData.csv");
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND,e.exceptionType);
        }
    }
    @Test   // handle delimiter exception
    public void givenStateCensusAnalyserFile_WhenIncorrectDelimiters_ReturnsException() {
        try {
            censusAnalyser.readFile("src/test/resources/StateCensusData.csv");
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT,e.exceptionType);
        }
    }
    @Test   //handle incorrect Header Exception
    public void givenStateCensusAnalyserFile_WhenIncorrectHeader_ReturnsException()  {
        try {
            censusAnalyser.readFile("src/test/resources/StateCensusData.csv");
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT,e.exceptionType);
        }
    }
    //Test For State Code Csv File
    @Test   //check records match
    public void givenStateCode_WhenTrue_ReturnNumberOfRecordShouldMatch() throws StatesCensusAnalyserException {
        Integer result = censusAnalyser.loadIndianStateCodeData("src/test/resources/StateCode.csv");
        Assert.assertEquals((Integer) 37, result);
    }
    @Test   //check exception if file is incorrect
    public void givenStateCode_WhenImproperFileName_ReturnException() {
        try {
            censusAnalyser.loadIndianStateCodeData("src/test/resources/StateCode.csv");
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   //Check exception if file type wrong
    public void givenStateCode_WhenImproperFileType_ReturnException(){
        try {
            censusAnalyser.loadIndianStateCodeData("src/test/resources/StateCode.csv");
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }
    @Test   // handle delimiter exception
    public void givenStateCode_WhenImproperDelimiter_ReturnException(){
        try {
            censusAnalyser.loadIndianStateCodeData("src/test/resources/StateCode.csv");
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }
    @Test   //handle incorrect Header Exception
    public void givenStateCode_WhenImproperHeader_ReturnException(){
        try {
            censusAnalyser.loadIndianStateCodeData("src/test/resources/StateCode.csv");
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }
}
