import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.bridgelabz.services.StatesCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestMethod {
    private static  String CSV_FILE_PATH ="src/test/resources/StateCensusData1.csv" ;

    @Test
        public void givenStateCensusAnalyserFile_WhenTrue_ReturnNumberOfRecordShouldMatch() throws IOException,StatesCensusAnalyserException{
            StatesCensusAnalyser censusAnalyser = new StatesCensusAnalyser( CSV_FILE_PATH);
            int count=censusAnalyser.loadData();
            Assert.assertEquals(29,count);
        }
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException() throws IOException{
        try {
            CSV_FILE_PATH ="src/test/resources/stateCensusData.csv";
            StatesCensusAnalyser censusAnalyser = new StatesCensusAnalyser(CSV_FILE_PATH);
            censusAnalyser.loadData();
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND,e.exceptionType);
        }
    }
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileExtension_ReturnsException() throws IOException {
        CSV_FILE_PATH ="src/test/resources/StateCensusData.txt";
        StatesCensusAnalyser censusAnalyser = new StatesCensusAnalyser(CSV_FILE_PATH);
        try {
            censusAnalyser.loadData();
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND,e.exceptionType);
        }
    }
    @Test
    public void givenStateCensusAnalyserFile_WhenIncorrectDelimiters_ReturnsException() throws IOException {
        CSV_FILE_PATH ="src/test/resources/StateCensusData1.csv";
        StatesCensusAnalyser censusAnalyser = new StatesCensusAnalyser(CSV_FILE_PATH);
        try {
            censusAnalyser.loadData();
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_INCORRECT,e.exceptionType);
        }
    }
}
