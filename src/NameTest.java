
import student.TestCase;

/**
 * Tests all the functionalities of the Name class
 * 
 * @author Shan Wu, Ko Yat Chan
 * @version 09.20.2019
 *
 */
public class NameTest extends TestCase {
    /**
     * First name to be tested
     */
    private Name name1;
    /**
     * Second name to be tested
     */
    private Name name2;
    /**
     * Third name to be tested
     */
    private Name name3;

    private Name name4;


    /**
     * Setup for the following testings.
     */
    public void setUp() {
        name1 = new Name("A", "B");
        name2 = new Name("John", "Smith", "William");
        name3 = new Name("Mary", "Will");
        name4 = new Name("William", "Will");
    }


    /**
     * testing all the getters and setters.
     */
    public void testers() {
        assertEquals("A", name1.getFirstName());
        assertEquals("B", name1.getLastName());
        assertEquals("William", name2.getMiddleName());
        name2.setFirstName("Luke");
        assertEquals("Luke", name2.getFirstName());
        name3.setLastName("Smith");
        assertEquals("Smith", name3.getLastName());
        name2.setMiddleName("Mike");
        assertEquals("Mike", name2.getMiddleName());
        boolean result1 = false;
        if (0 == name1.compareTo(name1)) {
            result1 = true;
        }
        assertTrue(result1);
        boolean result2 = false;
        if (name1.compareTo(name2) == -1) {
            result2 = true;
        }
        assertTrue(result2);
        boolean result3 = false;
        if (name2.compareTo(name1) == 1) {
            result3 = true;
        }
        assertTrue(result3);
        boolean result4 = false;
        if (name3.compareTo(name4) == -1) {
            result4 = true;
        }
        assertTrue(result4);

        boolean result5 = false;
        if (name4.compareTo(name3) == 1) {
            result5 = true;
        }
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
        assertTrue(result4);
        assertTrue(result5);
    }

}
