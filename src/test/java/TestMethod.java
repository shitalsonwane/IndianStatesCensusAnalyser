import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.bridgelabz.services.StatesCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestMethod {
    private static final String CSV_FILE_PATH ="src/test/resources/StateCensusData.csv" ;

    @Test
        public void givenStateCensusAnalyserFile_WhenTrue_ReturnNumberOfRecordShouldMatch() throws IOException,StatesCensusAnalyserException{
            StatesCensusAnalyser censusAnalyser = new StatesCensusAnalyser( CSV_FILE_PATH);
            int count=censusAnalyser.loadData();
            Assert.assertEquals(29,count);
        }
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException() throws IOException{
        try {
            StatesCensusAnalyser censusAnalyser = new StatesCensusAnalyser(CSV_FILE_PATH);
            censusAnalyser.loadData();
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND,e.exceptionType);
        }
    }
}
