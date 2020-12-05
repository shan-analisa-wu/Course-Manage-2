import student.TestCase;

/**
 * Test the StudentManager class
 * 
 * @author Shan Wu, ko yat chan
 * @version 10.20.2019
 */
public class StudentManagerTest extends TestCase {
    private StudentManager sm;


    /**
     * Set up the test
     */
    public void setUp() throws Exception {
        sm = new StudentManager();
    }


    /**
     * Test the student manager
     */
    public void test() {
        sm.addStudent((new Student(new Name("David:", "Allen"), 1233)));
        assertFalse(sm.isEmpty());
        sm.clear();
    }

}
