
import java.util.ArrayList;

/**
 * Section Class that contains 3 BSTs, total number of students, a student
 * manager, an array list to simply store all the student data, and etc.
 * 
 * @author Shan Wu, Ko Yat Chan
 * @version 10.16.2019
 *
 */
public class Section {
    /**
     * pidBst takes a pid as a key, and an index as an element.
     */
    private BinarySearchTree<Long, Integer> pidBst;
    /**
     * scoreBst takes a score as a key, and an index as an element.
     */
    private BinarySearchTree<Integer, Integer> scoreBst;
    /**
     * nameBst takes a name as a key, and an index as an element.
     */
    private BinarySearchTree<Name, Integer> nameBst;
    /**
     * An array list that stores the student data.
     */
    private ArrayList<Student> studentData;
    /**
     * A manager that can access the student pid and name data
     */
    private StudentManager manager;
    /**
     * counts the current number of student of this section.
     */
    private int numOfStudents = 0;
    /**
     * represents the latest student we handled with.
     */
    private Student lastestStudent;
    /**
     * represents the section number. The default section number is 1.
     */
    private int sectionNumber = 1;
    /**
     * Represents the score records in an array.
     */
    private int[] scoreRecords = new int[12];
    /**
     * Represents the total number of student in history.
     */
    private int stdNumInHistory = 0;
    /**
     * Represents if this section is merged by other sections.
     */
    private boolean isMerged = false;


    /**
     * Constructor, create a new Section object
     * 
     * @param sectionSize
     *            size of section
     * @param sectionNumber
     *            section number
     * @param sm
     *            The student manager
     */
    public Section(int sectionSize, int sectionNumber, StudentManager sm) {
        pidBst = new BinarySearchTree<>();
        scoreBst = new BinarySearchTree<>();
        nameBst = new BinarySearchTree<>();
        studentData = new ArrayList<Student>();
        manager = sm;
        this.sectionNumber = sectionNumber;
    }


    /**
     * This method assign a score to a specific
     * student if the student was in the section.
     * 
     * @param score
     *            The score to be assigned to
     *            the student.
     */
    public void score(int score) {
        int removeStudent = -1;
        for (Integer index : scoreBst) {
            Student current = studentData.get(index);
            if (current.getPid().equals(lastestStudent.getPid())) {
                removeStudent = index;
            }
        }
        if (removeStudent != -1) {
            scoreBst.remove(scoreBst.getRoot(), lastestStudent.getScore(),
                removeStudent);
            lastestStudent.setScore(score);
            scoreBst.insert(score, removeStudent);
        }
        System.out.println("Update " + lastestStudent.getName()
            + " record, score = " + score);
    }


    /**
     * The method searches whether a student with
     * given name(can be first nem or last name) is
     * in the current section
     * 
     * @param name
     *            the name of the student
     * @return the number of students with the
     *         given name
     */
    public int searchStudentByOneName(String name) {
        String formalName = name.substring(0, 1).toUpperCase() + name.substring(
            1).toLowerCase();
        int numStudent = 0;
        Student lastStudent = null;
        System.out.println("search results for " + name + ":");
        for (Integer stdIndex : nameBst) {
            Student findStudent = studentData.get(stdIndex);
            if (findStudent.getName().getFirstName().equals(formalName)
                || findStudent.getName().getLastName().equals(formalName)) {
                System.out.println(findStudent);
                lastStudent = findStudent;
                numStudent++;
            }
        }
        if (numStudent == 1) {
            lastestStudent = lastStudent;
        }

        System.out.println(name + " was found in " + numStudent
            + " records in section " + this.getSectionNumber());
        return numStudent;

    }


    /**
     * a method to search student with given
     * first name and last name in the current
     * section
     * 
     * @param targetName
     *            the name to be searched(a name
     *            object created according to the
     *            given first name and last name
     * @return return 1 if the target student was found, otherwise
     *         return 0.
     */
    public int searchStudent(Name targetName) {
        int numStudent = 0;
        Student lastStudent = null;
        System.out.println("search results for " + targetName.toString() + ":");
        for (Integer stdIndex : nameBst) {
            Student findStudent = studentData.get(stdIndex);
            if (findStudent.getName().getFirstName().equals(targetName
                .getFirstName()) && findStudent.getName().getLastName().equals(
                    targetName.getLastName())) {
                System.out.println(findStudent.toString());
                lastStudent = findStudent;
                numStudent++;
            }

        }
        if (numStudent == 1) {
            lastestStudent = lastStudent;
        }
        System.out.println(targetName + " was found in " + numStudent
            + " records in section " + this.getSectionNumber());
        return numStudent;
    }


    /**
     * The method searches a specific student with a pid provided
     * 
     * @param pid
     *            The pid to be searched
     * @return return 0 if the student with the given pid is not found,
     *         otherwise return 1
     */
    public int searchPid(long pid) {
        int dataIndex = -1;
        for (Integer index : pidBst) {
            Student current = studentData.get(index);
            if (current.getPid().equals(pid)) {
                dataIndex = index;
            }
        }
        if (dataIndex == -1) {
            StringBuilder str = new StringBuilder();
            if (pid < 100000000) {
                String pidStr = String.valueOf(pid);
                int flag = 9 - pidStr.length();
                for (int i = 0; i < flag; i++) {
                    str.append("0");
                }
                str.append(pid);
                System.out.println(
                    "Search Failed. Couldn't find any student with ID " + str
                        .toString());
            }
            else {
                System.out.println(
                    "Search Failed. Couldn't find any student with ID " + pid);
            }
            return 0;
        }
        else {
            Student stdFound = studentData.get(dataIndex);
            System.out.println("Found " + stdFound.toString());
            lastestStudent = stdFound;
            return 1;
        }
    }


    /**
     * This method insert the student read in the cvs file
     * into a specific section.
     * 
     * @param pid
     *            The pid of the student to be inserted
     * @param name
     *            The name of the student to be inserted
     * @param score
     *            The score of the student
     * @param grade
     *            The grade of the student
     * @return return 0 if the student is not successfully inserted, otherwise,
     *         return 1
     */
    public int courseInsert(long pid, Name name, int score, String grade) {
        Student tmpStudent = new Student(name, pid);
        Student found = manager.findStudent(tmpStudent);
        if (found == null) {
            System.out.println("Warning: Student " + tmpStudent.getName()
                + " is not loaded to section " + sectionNumber
                + " since he/she doesn't exist in the loaded student records.");
            return 0;
        }
        if (found.getName().compareTo(tmpStudent.getName()) != 0) {
            System.out.println("Warning: Student " + tmpStudent.getName()
                + " is not loaded to section " + sectionNumber
                + " since the corresponding pid belongs to another student.");
            return 0;
        }
        if (found.getCurrentEnroll() == this.getSectionNumber()) {
            int dataIndex = 0;
            for (Integer index : pidBst) {
                Student current = studentData.get(index);
                if (current.getPid().equals(pid)) {
                    dataIndex = index;
                }
            }
            scoreBst.remove(scoreBst.getRoot(), found.getScore(), dataIndex);
            found.setScore(score);
            found.setGrade(grade);
            scoreBst.insert(found.getScore(), dataIndex);
            return 1;
        }
        else if (found.getCurrentEnroll() != 0) {
            System.out.println("Warning: Student " + tmpStudent.getName()
                + " is not loaded to section " + sectionNumber
                + " since he/she is already enrolled in section " + found
                    .getCurrentEnroll());
            return 0;
        }
        if (score != -1) {
            found.setScore(score);
        }
        else {
            found.setScore(0);
        }
        if (grade != null) {
            found.setGrade(grade);
        }
        else {
            found.setGrade("F");
        }
        found.setCurrentEnroll(this.getSectionNumber());
        lastestStudent = found;
        pidBst.insert(found.getPid(), this.getStdInHistory());
        nameBst.insert(found.getName(), this.getStdInHistory());
        scoreBst.insert(found.getScore(), this.getStdInHistory());
        studentData.add(found);
        stdNumInHistory++;
        numOfStudents++;
        return 1;

    }


    /**
     * Insert a student with given name into
     * the current section
     * 
     * @param name
     *            name of student
     * @param pid
     *            The pid of the student
     * @return Return 1 if the student was successfully
     *         inserted. Return 0 if the student was already
     *         in the section.
     */
    public int insertStudent(long pid, Name name) {
        Student tmpStudent = new Student(name, pid);
        Student found = manager.findStudent(tmpStudent);
        if (found == null) {
            System.out.println(tmpStudent.getName()
                + " insertion failed. Wrong student "
                + "information. ID doesn't exist");
            return 0;
        }
        if (found.getName().compareTo(tmpStudent.getName()) != 0) {
            System.out.println(tmpStudent.getName()
                + " insertion failed. Wrong student information. "
                + "ID belongs to another student");
            return 0;
        }
        if (found.getCurrentEnroll() == this.getSectionNumber()) {
            System.out.println(tmpStudent.getName() + " is already in section "
                + this.getSectionNumber());
            return 0;
        }
        else if (found.getCurrentEnroll() != 0) {
            System.out.println(tmpStudent.getName()
                + " is already registered in a different section");
            return 0;
        }
        found.setScore(0);
        found.setGrade("F");
        found.setCurrentEnroll(this.getSectionNumber());
        lastestStudent = found;
        pidBst.insert(found.getPid(), this.getStdInHistory());
        nameBst.insert(found.getName(), this.getStdInHistory());
        scoreBst.insert(found.getScore(), this.getStdInHistory());
        studentData.add(found);
        stdNumInHistory++;
        numOfStudents++;
        System.out.println(name + " inserted.");
        return 1;
    }


    /**
     * The method prints out the information of
     * students that are in the current section.
     */
    public void dumpSection() {
        System.out.println("section " + this.getSectionNumber() + " dump:");
        System.out.println("BST by ID:");
        pidDump(pidBst.getRoot());
        System.out.println("BST by name:");
        nameDump(nameBst.getRoot());
        System.out.println("BST by score:");
        scoreDump(scoreBst.getRoot());
        System.out.println("Size = " + this.getNumberOfStudent());
    }


    /**
     * This method dump the section according to the pid
     */
    private void pidDump(BinaryNode<Long, Integer> root) {
        if (root == null) {
            return;
        }
        pidDump(root.getLeft());
        int index = root.getElement();
        Student current = studentData.get(index);
        System.out.println(current.toString());
        pidDump(root.getRight());
    }


    /**
     * This method dump the section according to the pid
     */
    private void nameDump(BinaryNode<Name, Integer> root) {
        if (root == null) {
            return;
        }
        nameDump(root.getLeft());
        int index = root.getElement();
        Student current = studentData.get(index);
        System.out.println(current.toString());
        nameDump(root.getRight());
    }


    /**
     * This method dump the section according to the pid
     */
    private void scoreDump(BinaryNode<Integer, Integer> root) {
        if (root == null) {
            return;
        }
        scoreDump(root.getLeft());
        int index = root.getElement();
        Student current = studentData.get(index);
        System.out.println(current.toString());
        scoreDump(root.getRight());
    }


    /**
     * a method to get the section number of the
     * current section
     * 
     * @return the section number
     */
    public int getSectionNumber() {
        return sectionNumber;
    }


    /**
     * This method get the number of student
     * that have enrolled in this section,
     * regardless of whether the student
     * have been removed
     * 
     * @return The number of the student in history
     */
    public int getStdInHistory() {
        return stdNumInHistory;
    }


    /**
     * The method reports the number of
     * students with each letter grade in
     * the current section.
     * 
     */
    public void grade() {
        for (int i = 0; i < stdNumInHistory; i++) {
            Student current = studentData.get(i);
            if (current.getCurrentEnroll() != 0) {
                int currentGrade = this.addScoreRecord(current.getScore());
                scoreRecords[currentGrade]++;
                current.setGrade(grader(currentGrade));
            }
        }
        System.out.println("grading completed");
    }


    /**
     * This method get the status of the grade of the class
     */
    public void stat() {
        System.out.println("Statistics of section " + this.getSectionNumber()
            + ":");
        String[] gradeList = { "A", "A-", "B+", "B", "B-", "C+", "C", "C-",
            "D+", "D", "D-", "F" };
        for (int i = 0; i < scoreRecords.length; i++) {
            if (scoreRecords[i] != 0) {
                System.out.println(scoreRecords[i] + " students with grade "
                    + gradeList[i]);
            }
        }
    }


    /**
     * This method list the grade that is specified
     * 
     * @param grade
     *            The specific grade that need to be
     *            listed
     */
    public void list(String grade) {
        String formalGrade = grade.toUpperCase();
        System.out.println("Students with grade " + formalGrade + " are:");
        int count = 0;
        if (grade.length() == 2) {
            char flag = formalGrade.charAt(1);
            if (flag == '*') {
                String target1 = new StringBuilder().append(formalGrade.charAt(
                    0)).append('+').toString();
                String target2 = new StringBuilder().append(formalGrade.charAt(
                    0)).toString();
                String target3 = new StringBuilder().append(formalGrade.charAt(
                    0)).append('-').toString();
                count = listhelp(target1) + listhelp(target2) + listhelp(
                    target3);
            }
            else {
                count = listhelp(formalGrade);
            }
        }
        else {
            count = listhelp(formalGrade);
        }
        System.out.println("Found " + count + " students");
    }


    /**
     * A helper method that iterates every student records in the current
     * section
     * and list students with specified grade
     */
    private int listhelp(String grade) {
        int num = 0;
        for (Integer index : pidBst) {
            Student current = studentData.get(index);
            if (current.getGrade().equals(grade)) {
                System.out.println(current.toString() + ", grade = " + current
                    .getGrade());
                num++;
            }
        }
        return num;
    }


    /**
     * Determine the grade of the student
     */
    private String grader(int grade) {
        if (grade == 0) {
            return "A";
        }
        else if (grade == 1) {
            return "A-";
        }
        else if (grade == 2) {
            return "B+";
        }
        else if (grade == 3) {
            return "B";
        }
        else if (grade == 4) {
            return "B-";
        }
        else if (grade == 5) {
            return "C+";
        }
        else if (grade == 6) {
            return "C";
        }
        else if (grade == 7) {
            return "C-";
        }
        else if (grade == 8) {
            return "D+";
        }
        else if (grade == 9) {
            return "D";
        }
        else if (grade == 10) {
            return "D-";
        }
        else {
            return "F";
        }

    }


    /**
     * a method to remove the whole section
     */
    public void clearSection() {
        if (numOfStudents != 0) {
            pidBst.makeEmpty();
            nameBst.makeEmpty();
            scoreBst.makeEmpty();
            studentData.clear();
            numOfStudents = 0;
            stdNumInHistory = 0;
        }
    }


    /**
     * a method to remove a student with given
     * first name and last name from the current
     * section
     * 
     * @param name
     *            The name of the student to be removed
     */
    public void removeStudent(Name name) {
        int studentCount = 0;
        Student removeStudent = null;
        int removeIndex = -1;
        for (Integer index : nameBst) {
            Student current = studentData.get(index);
            if (current.getName().compareTo(name) == 0) {
                studentCount++;
                removeStudent = current;
                removeIndex = index;
            }
        }
        if (studentCount == 1) {
            removeStudent.setCurrentEnroll(0);
            numOfStudents--;
            pidBst.remove(pidBst.getRoot(), removeStudent.getPid(),
                removeIndex);
            scoreBst.remove(scoreBst.getRoot(), removeStudent.getScore(),
                removeIndex);
            nameBst.remove(nameBst.getRoot(), removeStudent.getName(),
                removeIndex);
            System.out.println("Student " + name + " get removed from section "
                + this.getSectionNumber());
        }
        else {
            System.out.println("Remove failed. Student " + name
                + " doesn't exist in section " + this.getSectionNumber());
        }
    }


    /**
     * This method remove the student from the
     * current section with specified pid
     * 
     * @param pid
     *            The pid of the student that needs to be removed
     */
    public void remove(long pid) {
        int removeIndex = -1;
        Student removeStudent = null;
        for (Integer index : pidBst) {
            Student current = studentData.get(index);
            if (current.getPid().equals(pid)) {
                removeStudent = current;
                removeIndex = index;
            }
        }
        if (removeIndex != -1) {
            removeStudent.setCurrentEnroll(0);
            numOfStudents--;
            pidBst.remove(pidBst.getRoot(), removeStudent.getPid(),
                removeIndex);
            scoreBst.remove(scoreBst.getRoot(), removeStudent.getScore(),
                removeIndex);
            nameBst.remove(nameBst.getRoot(), removeStudent.getName(),
                removeIndex);
            System.out.println("Student " + removeStudent.getName()
                + " get removed from section " + this.getSectionNumber());
        }
        else {
            StringBuilder str = new StringBuilder();
            if (pid < 100000000) {
                String pidStr = String.valueOf(pid);
                int flag = 9 - pidStr.length();
                for (int i = 0; i < flag; i++) {
                    str.append("0");
                }
                str.append(pid);
                System.out.println(
                    "Remove failed: couldn't find any student with id " + str
                        .toString());
            }
            else {
                System.out.println(
                    "Remove failed: couldn't find any student with id " + pid);
            }

        }

    }


    /**
     * get the whole pidBst
     * 
     * @return the pidBst
     */
    public BinarySearchTree<Long, Integer> getPidBST() {
        return pidBst;
    }


    /**
     * get the whole scoreBst
     * 
     * @return the scoreBst
     */
    public BinarySearchTree<Integer, Integer> getScoreBST() {
        return scoreBst;
    }


    /**
     * get the whole nameBst
     * 
     * @return the nameBst
     */
    public BinarySearchTree<Name, Integer> getNameBST() {
        return nameBst;
    }


    /**
     * A method to determine the letter grade of
     * a specific student by the score
     * 
     * @param score
     *            the number score of a student
     * @return the indicator of a specific letter grade
     */
    private int addScoreRecord(int score) {
        if (score >= 90 && score <= 100) {
            return 0; // A
        }
        else if (score >= 85 && score < 90) {
            return 1; // A-
        }
        else if (score >= 80 && score < 85) {
            return 2; // B+
        }
        else if (score >= 75 && score < 80) {
            return 3; // B
        }
        else if (score >= 70 && score < 75) {
            return 4; // B-
        }
        else if (score >= 65 && score < 70) {
            return 5; // C+
        }
        else if (score >= 60 && score < 65) {
            return 6; // C
        }
        else if (score >= 57 && score < 60) {
            return 7; // C-
        }
        else if (score >= 55 && score < 57) {
            return 8; // D+
        }
        else if (score >= 52 && score < 55) {
            return 9; // D
        }
        else if (score >= 50 && score < 52) {
            return 10; // D-
        }
        else {
            return 11; // F
        }
    }


    /**
     * The method iterates the current
     * section and find the number of
     * pairs of students that the difference
     * of their scores are within the given
     * range. If no difference of scores
     * are specified, it is regarded as 0.
     * 
     * @param score
     *            the expected difference of
     *            two students' score
     * @return The number of pairs of students
     *         whose score are within the specific
     *         range of difference
     */
    public int findPair(String score) {
        int numScore;
        int numPairs = 0;
        if (score == null) {
            numScore = 0;
        }
        else {
            numScore = Integer.parseInt(score);
        }
        for (Integer index1 : scoreBst) {
            for (Integer index2 : scoreBst) {
                Student student1 = studentData.get(index1);
                Student student2 = studentData.get(index2);
                int difference = student1.getScore() - student2.getScore();
                if (Math.abs(difference) <= numScore && student1.getName()
                    .compareTo(student2.getName()) <= 0 && !student1.getPid()
                        .equals(student2.getPid())) {
                    System.out.println(student2.getName().getFirstName() + " "
                        + student2.getName().getLastName() + ", " + student1
                            .getName().getFirstName() + " " + student1.getName()
                                .getLastName());
                    numPairs++;
                }
            }
        }
        return numPairs;
    }


    /**
     * get number of student in the
     * current section
     * 
     * @return number of student
     */
    public int getNumberOfStudent() {
        return this.numOfStudents;
    }


    /**
     * determine whether the current section
     * is a merged section
     * 
     * @return return true if the current
     *         section is a merged section
     *         otherwise, return false
     */
    public boolean isMerged() {
        return isMerged;
    }


    /**
     * change the status of the current
     * section if it gets merged
     * 
     * @param merge
     *            if current section gets
     *            merged, it is true, otherwise
     *            false
     */
    public void setMerged(boolean merge) {
        isMerged = merge;
    }


    /**
     * This method get all the
     * student data in the current section
     * 
     * @return studentData all the student information
     *         that currently or used to
     *         enroll in this section
     */
    public ArrayList<Student> courseInfo() {
        return studentData;
    }


    /**
     * Set the student manager of the current section
     * 
     * @param sm
     *            The student manager to be set
     */
    public void setStudentManager(StudentManager sm) {
        this.manager = sm;
    }


    /**
     * Set the number of the student in the current section
     * 
     * @param stdNum
     *            The number of the student to be set
     */
    public void setNumberOfStudent(int stdNum) {
        this.numOfStudents = stdNum;
    }


    /**
     * Set the number of the student that have enrolled in
     * the current section
     * 
     * @param stdHisNum
     *            The number of the student that have
     *            enrolled in the current section to be set
     */
    public void setNumHisOfStd(int stdHisNum) {
        this.stdNumInHistory = stdHisNum;
    }


    /**
     * This method get the student data array of the current section
     * 
     * @return The student data array of the current section
     */
    public ArrayList<Student> getStudentData() {
        return this.studentData;
    }
}
