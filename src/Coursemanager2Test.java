import java.io.IOException;
import student.TestCase;

/**
 * This class tests the Coursemanager class and the
 * FileParser2 class.
 * 
 * @author Shan Wu, Ko Yat Chan
 * @version 10.20.2019
 */
public class Coursemanager2Test extends TestCase {

    /**
     * Test the main method
     * 
     * @throws IOException
     *             Throw exception if problem occur
     *             while reading file
     */
    public void testMain() throws IOException {
        Coursemanager2 manager = new Coursemanager2();
        assertNotNull(manager);
        String[] input = new String[1];
        input[0] = "SampleInput3.txt";
        Coursemanager2.main(input);
    }

}
