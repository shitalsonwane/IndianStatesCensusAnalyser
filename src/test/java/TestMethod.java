import com.bridgelabz.services.StatesCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class TestMethod {
    @Test
    public void givenStateCensusAnalyserFile_WhenTrue_ReturnNumberOfRecordShouldMatch() throws IOException{
        StatesCensusAnalyser censusAnalyser=new StatesCensusAnalyser();
        int count=censusAnalyser.loadData();
        Assert.assertEquals(29,count);
    }
}
