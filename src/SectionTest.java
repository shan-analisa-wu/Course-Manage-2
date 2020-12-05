import student.TestCase;

/**
 * Tests all the functionalities of the Student class
 * 
 * @author Shan Wu, Ko Yat Chan
 * @version 10.16.2019
 *
 */
public class SectionTest extends TestCase {
    private Section section1;
    private Section section2;
    private Name name1;
    private Name name2;
    private Name name3;
    private StudentManager manager;


    /**
     * Setup for the following testings.
     */
    public void setUp() {
        name1 = new Name("John", "Smith", "William");
        name2 = new Name("Mary", "Will");
        name3 = new Name("William", "Will");
        Student student1;
        Student student2;
        Student student3;
        student1 = new Student(name1, 123456);
        student2 = new Student(name2, 123789);
        student3 = new Student(name3, 123098);
        manager = new StudentManager();
        section1 = new Section(10, 1, manager);
        section2 = new Section(10, 2, manager);
        manager.addStudent(student1);
        manager.addStudent(student2);
        manager.addStudent(student3);
    }


    /**
     * Test the insert method
     */
    public void testInsert() {
        section1.insertStudent(123456, name1);
        section1.insertStudent(123789, name2);
        section1.insertStudent(123098, name3);
        boolean result = false;
        if (3 == section1.getNumberOfStudent()) {
            result = true;
        }
        assertTrue(result);
        Name name4 = new Name("Mary", "William");
        section1.insertStudent(123789, name4);
        section1.insertStudent(123789, name2);
        section2.insertStudent(123789, name2);
    }


    /**
     * Test the searchid method
     */
    public void testSearchid() {
        section1.insertStudent(123456, name1);
        section1.insertStudent(123789, name2);
        section1.insertStudent(123098, name3);
        section1.searchPid(123466);
        boolean result1 = false;
        if (1 == section1.searchPid(123456)) {
            result1 = true;
        }
        assertTrue(result1);
        boolean result2 = false;
        if (0 == section1.searchPid(123466)) {
            result2 = true;
        }
        assertTrue(result2);
    }


    /**
     * Test the searchStudent method
     */
    public void testSearchStudent() {
        section1.insertStudent(123456, name1);
        section1.insertStudent(123789, name2);
        Student student = new Student(name1, 123466);
        manager.addStudent(student);
        section1.insertStudent(123466, name1);
        boolean result1 = false;
        if (2 == section1.searchStudent(name1)) {
            result1 = true;
        }
        assertTrue(result1);
        boolean result2 = false;
        if (1 == section1.searchStudent(name2)) {
            result2 = true;
        }
        assertTrue(result2);
        boolean result3 = false;
        if (0 == section1.searchStudent(name3)) {
            result3 = true;
        }
        assertTrue(result3);
    }


    /**
     * Test the searchStudentByOneName method
     */
    public void testSearchStudentByName() {
        section1.insertStudent(123456, name1);
        section1.insertStudent(123789, name2);
        section1.insertStudent(123098, name3);
        boolean result1 = false;
        if (0 == section1.searchStudentByOneName("jack")) {
            result1 = true;
        }
        assertTrue(result1);
        boolean result2 = false;
        if (2 == section1.searchStudentByOneName("will")) {
            result2 = true;
        }
        assertTrue(result2);
        boolean result3 = false;
        if (2 == section1.searchStudentByOneName("Will")) {
            result3 = true;
        }
        assertTrue(result3);
        boolean result4 = false;
        if (1 == section1.searchStudentByOneName("john")) {
            result4 = true;
        }
        assertTrue(result4);
    }


    /**
     * Test the score method
     */
    public void testScore() {
        section1.insertStudent(123456, name1);
        section1.insertStudent(123789, name2);
        section1.insertStudent(123098, name3);
        section1.searchPid(123789);
        section1.score(50);
        System.out.println(section1.getScoreBST().getSize());
        System.out.println(section1.getScoreBST().toString());
        boolean result = true;
        assertTrue(result);
    }


    /**
     * Test the remove method
     */
    public void testRemove() {
        section1.insertStudent(123456, name1);
        section1.insertStudent(123789, name2);
        section1.insertStudent(123098, name3);
        section1.remove(123456);
        System.out.println(section1.getScoreBST().getSize());
        System.out.println(section1.getScoreBST().toString());
        System.out.println(section1.getPidBST().getSize());
        System.out.println(section1.getPidBST().toString());
        System.out.println(section1.getNameBST().getSize());
        System.out.println(section1.getNameBST().toString());
        boolean result = true;
        assertTrue(result);
    }


    /**
     * Test the removeStudent method
     */
    public void testRemoveStudent() {
        section1.insertStudent(123456, name1);
        section1.insertStudent(123789, name2);
        section1.insertStudent(123098, name3);
        section1.removeStudent(name2);
        System.out.println(section1.getScoreBST().getSize());
        System.out.println(section1.getScoreBST().toString());
        System.out.println(section1.getPidBST().getSize());
        System.out.println(section1.getPidBST().toString());
        System.out.println(section1.getNameBST().getSize());
        System.out.println(section1.getNameBST().toString());
        Name name4 = new Name("Mary", "William");
        section1.removeStudent(name4);
        boolean result = true;
        assertTrue(result);
    }


    /**
     * Test the clear section method
     */
    public void testClearSection() {
        section1.clearSection();
        boolean result1 = false;
        if (0 == section1.getPidBST().getSize()) {
            result1 = true;
        }
        assertTrue(result1);
        boolean result2 = false;
        if (0 == section1.getScoreBST().getSize()) {
            result2 = true;
        }
        assertTrue(result2);
        boolean result3 = false;
        if (0 == section1.getNameBST().getSize()) {
            result3 = true;
        }
        assertTrue(result3);
        boolean result4 = false;
        if (0 == section1.courseInfo().size()) {
            result4 = true;
        }
        assertTrue(result4);
    }


    /**
     * Test the dumpSection method
     */
    public void testDumpSection() {
        section1.insertStudent(123456, name1);
        section1.insertStudent(123789, name2);
        section1.insertStudent(123098, name3);
        section1.dumpSection();
        boolean result = true;
        assertTrue(result);
    }


    /**
     * Test the grade and stat methods
     */
    public void testGrading() {
        section1.insertStudent(123456, name1);
        section1.score(95);
        section1.insertStudent(123789, name2);
        section1.score(80);
        section1.insertStudent(123098, name3);
        section1.score(65);
        section1.grade();
        section1.stat();
        boolean result = true;
        assertTrue(result);
    }


    /**
     * Test the list method
     */
    public void testList() {
        section1.insertStudent(123456, name1);
        section1.score(95);
        section1.insertStudent(123789, name2);
        section1.score(87);
        section1.insertStudent(123098, name3);
        section1.score(65);
        section1.grade();
        section1.list("A*");
        boolean result = true;
        assertTrue(result);
    }


    /**
     * Test the findPair method
     */
    public void testFindPair() {
        section1.insertStudent(123456, name1);
        section1.score(95);
        section1.insertStudent(123789, name2);
        section1.score(87);
        section1.insertStudent(123098, name3);
        section1.score(95);
        section1.findPair("0");
        section1.findPair("8");
        boolean result = true;
        assertTrue(result);
    }


    /**
     * Test the courseInsert method
     */
    public void testCourseInsert() {
        Name name4 = new Name("Mary", "William");
        section1.courseInsert(123789, name4, 3, "F");
        section1.courseInsert(123567, name1, 50, "D");
        section1.courseInsert(123456, name1, 100, "A");
        section1.courseInsert(123789, name2, 85, "B+");
        section1.courseInsert(123098, name3, 78, "C-");
        boolean result = false;
        if (3 == section1.getNumberOfStudent()) {
            result = true;
        }
        assertTrue(result);
        section1.courseInsert(123098, name3, 90, "A");
        section2.courseInsert(123789, name2, 85, "B+");
    }
}
