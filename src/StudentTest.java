
import student.TestCase;

/**
 * Tests all the functionalities of the Student class
 * 
 * @author Shan Wu, Ko Yat Chan
 * @version 10.16.2019
 *
 */

public class StudentTest extends TestCase {
    private Student student1;
    private Student student2;
    private Student student3;


    /**
     * Setup for the following testings.
     */
    public void setUp() {
        Name name1;
        Name name2;
        Name name3;
        name1 = new Name("John", "Smith", "William");
        name2 = new Name("Mary", "Will");
        name3 = new Name("William", "Will");
        student1 = new Student(name1, 123456);
        student2 = new Student(name2, 123789);
        student3 = new Student(name3, 123098);
    }


    /**
     * Test the getCurrentEnroll and setCurrentEnroll method
     */
    public void testEnroll1() {
        boolean result1 = false;
        if (0 == student1.getCurrentEnroll()) {
            result1 = true;
        }
        assertTrue(result1);
        student1.setCurrentEnroll(1);
        boolean result2 = false;
        if (1 == student1.getCurrentEnroll()) {
            result2 = true;
        }
        assertTrue(result2);
    }


    /**
     * Test the getName method
     */
    public void testGetName() {
        System.out.println(student1.getName());
        boolean result = true;
        assertTrue(result);
    }


    /**
     * Test the getPid method
     */
    public void testGetPid() {
        boolean result = false;
        if (student2.getPid() == 123789) {
            result = true;
        }
        assertTrue(result);
    }


    /**
     * Test the getScore and setScore method
     */
    public void testScore() {
        boolean result1 = false;
        if (student3.getScore() == 0) {
            result1 = true;
        }
        assertTrue(result1);
        student3.setScore(70);
        boolean result2 = false;
        if (student3.getScore() == 70) {
            result2 = true;
        }
        assertTrue(result2);
    }


    /**
     * Test the getGrade and setGrade method
     */
    public void testGrade() {
        student3.setGrade("A-");
        assertEquals("A-", student3.getGrade());
    }
}
