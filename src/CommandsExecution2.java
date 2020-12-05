import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Commands Execution that will take care of all
 * the given commands
 * 
 * @author Shan Wu, Ko Yat Chan
 * @version 10.16.2019
 */
public class CommandsExecution2 {
    /**
     * Default maximum size for each section: 120
     */
    private static final int DEFAULT_SECTION_SIZE = 120;
    /**
     * records the latestCommand
     */
    private String latestCommand;
    /**
     * Stores the 3 sections
     */
    private Section[] sections; // 20 sections
    /**
     * A flag that control the sections array.
     */
    private int sectionFlag;
    /**
     * A student manager that holds simple records of students
     */
    private StudentManager sm;


    /**
     * Constructor, create a new CommandsExecution2 object.
     * 
     */

    public CommandsExecution2() {
        sm = new StudentManager();
        sectionFlag = 0;
        sections = new Section[20];
        for (int i = 0; i < 20; i++) {
            sections[i] = new Section(DEFAULT_SECTION_SIZE, i + 1, sm);
        }
    }


    /**
     * Call different command functions depends on the arguments of the input
     * lines.
     * 
     * @param line
     *            A line that specifies a command
     *            to be executed.
     */
    public void checkCommands(String line) {
        if (line != null) {
            line = line.trim();
            String[] words = line.split("\\s+"); // for whitespace splits
            switch (words[0]) {
                case "loadcoursedata":
                    if (sm.isEmpty()) {
                        System.out.println(
                            "Course Load Failed. You have to load "
                                + "Student Information file first.");
                        break;
                    }
                    String[] courseFileType = words[1].split("\\.");
                    System.out.println(courseFileType[0]
                        + " Course has been successfully loaded.");
                    if (courseFileType[1].equals("csv")) {
                        cvsCourseReader(words[1]);
                    }
                    if (courseFileType[1].equals("data")) {
                        binCourseReader(words[1]);
                    }

                    break;
                case "loadstudentdata":
                    String[] studentFileType = words[1].split("\\.");
                    System.out.println(words[1] + " successfully loaded");
                    if (studentFileType[1].equals("csv")) {
                        cvsStudentReader(words[1]);
                        for (int i = 0; i < 20; i++) {
                            sections[i].setStudentManager(sm);
                        }
                    }
                    if (studentFileType[1].equals("data")) {
                        binStudenteReader(words[1]);
                        for (int i = 0; i < 20; i++) {
                            sections[i].setStudentManager(sm);
                        }
                    }

                    break;
                case "savestudentdata":
                    binStudentWriter(words[1]);
                    System.out.println("Saved all Students data to "
                        + words[1]);
                    break;
                case "savecoursedata":
                    binCourseWriter(words[1]);
                    System.out.println("Saved all course data to " + words[1]);
                    break;

                case "section":
                    if (words.length == 1) {
                        sectionFlag = 0; // default value is 1
                        System.out.println("switch to section 1");
                    }
                    else {
                        System.out.println("switch to section " + words[1]);
                        sectionFlag = Integer.parseInt(words[1]) - 1;
                    }
                    latestCommand = words[0];
                    break;
                case "insert":
                    if (!sections[sectionFlag].isMerged()) {
                        int flag = -1;
                        long pid = Long.parseLong(words[1]);
                        Name name = new Name(words[2], words[3], null);
                        flag = sections[sectionFlag].insertStudent(pid, name);
                        if (flag == 1) {
                            latestCommand = words[0];
                        }
                        else {
                            latestCommand = "Failed insert";
                        }
                    }
                    else {
                        System.out.println("Command " + words[0]
                            + " is not valid for merged sections");
                    }
                    break;

                case "searchid":
                    if (!sections[sectionFlag].isMerged()) {
                        int numOfResults = 0;
                        long pid1 = Long.parseLong(words[1]);
                        numOfResults = sections[sectionFlag].searchPid(pid1);
                        if (numOfResults == 1) {
                            latestCommand = words[0];
                        }
                        else {
                            latestCommand = "SearchSingleName";
                        }
                    }
                    else {
                        System.out.println("Command " + words[0]
                            + " is not valid for merged sections");
                    }
                    break;

                case "search": // there are two possible parameters
                    if (!sections[sectionFlag].isMerged()) {
                        int numOfResults1 = 0;
                        if (words.length == 3) {
                            numOfResults1 = sections[sectionFlag].searchStudent(
                                new Name(words[1], words[2], null));
                        }
                        else if (words.length == 2) {
                            numOfResults1 = sections[sectionFlag]
                                .searchStudentByOneName(words[1]);
                        }
                        if (numOfResults1 == 1) {
                            latestCommand = words[0];
                        }
                        else {
                            latestCommand = "SearchSingleName";
                        }
                    }
                    else {
                        System.out.println("Command " + words[0]
                            + " is not valid for merged sections");
                    }
                    break;

                case "score":
                    if (!sections[sectionFlag].isMerged()) {
                        if (latestCommand.equals("insert") || latestCommand
                            .equals("search") || latestCommand.equals(
                                "searchid")) {
                            int score = Integer.parseInt(words[1]);
                            if (!(score > 100 || score < 0)) {
                                sections[sectionFlag].score(score);
                            }
                            else {
                                System.out.println(
                                    "Scores have to be integers in "
                                        + "range 0 to 100.");
                            }
                        }
                        else {
                            System.out.println(
                                "score command can only be called after an "
                                    + "insert command or a successful "
                                    + "search command"
                                    + " with one exact output.");
                        }
                        latestCommand = words[0];
                    }
                    else {
                        System.out.println("Command " + words[0]
                            + " is not valid for merged sections");
                    }
                    break;

                case "remove":
                    if (!sections[sectionFlag].isMerged()) {
                        if (words.length == 3) {
                            Name targetName = new Name(words[1], words[2],
                                null);
                            sections[sectionFlag].removeStudent(targetName);
                            latestCommand = words[0];
                        }
                        else if (words.length == 2) {
                            long pid3 = Long.parseLong(words[1]);
                            sections[sectionFlag].remove(pid3);
                            latestCommand = words[0];
                        }
                    }
                    else {
                        System.out.println("Command " + words[0]
                            + " is not valid for merged sections");
                    }
                    break;
                case "clearsection":
                    int clearedSectionNumber = sectionFlag;
                    sections[clearedSectionNumber].clearSection();
                    System.out.println("Section " + (clearedSectionNumber + 1)
                        + " cleared");

                    latestCommand = words[0];
                    break;

                case "clearcoursedata":
                    for (int i = 0; i < sections.length; i++) {
                        sections[i].clearSection();
                    }
                    for (Student s : sm.getStudentList()) {
                        s.setCurrentEnroll(0);
                    }

                    System.out.println("All course data cleared.");
                    break;
                case "dumpsection":
                    sections[sectionFlag].dumpSection();
                    latestCommand = words[0];
                    break;
                case "grade":
                    sections[sectionFlag].grade();
                    latestCommand = words[0];
                    break;
                case "stat":
                    sections[sectionFlag].stat();
                    latestCommand = words[0];
                    break;
                case "list":
                    sections[sectionFlag].list(words[1]);
                    latestCommand = words[0];
                    break;
                case "findpair":
                    if (words.length == 1) {
                        System.out.println("Students with score difference "
                            + "less than or equal " + "0" + ":");
                        int numOfPairs = sections[sectionFlag].findPair("0");

                        System.out.println("found " + numOfPairs + " pairs");
                    }
                    if (words.length == 2) {
                        System.out.println("Students with score difference "
                            + "less than or equal " + words[1] + ":");
                        int numOfPairs = sections[sectionFlag].findPair(
                            words[1]);

                        System.out.println("found " + numOfPairs + " pairs");
                    }
                    latestCommand = words[0];
                    break;
                case "merge":
                    this.merge();
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * This command merge all the course
     * section to one empty section for
     * grading purpose
     */
    public void merge() {
        if (sections[sectionFlag].getNumberOfStudent() != 0) {
            System.out.println(
                "Sections could only be merged to an empty section. section "
                    + sections[sectionFlag].getSectionNumber()
                    + " is not empty.");
        }
        else {
            ArrayList<Student> courseData = sections[sectionFlag].courseInfo();
            BinarySearchTree<Long, Integer> pidBst = sections[sectionFlag]
                .getPidBST();
            BinarySearchTree<Integer, Integer> scoreBst = sections[sectionFlag]
                .getScoreBST();
            BinarySearchTree<Name, Integer> nameBst = sections[sectionFlag]
                .getNameBST();
            int currentIndex = 0;
            for (int i = 0; i < 20; i++) {
                if (i != sectionFlag) {
                    if (sections[i].getNumberOfStudent() != 0) {
                        ArrayList<Student> sectionInfo = sections[i]
                            .courseInfo();
                        for (int a = 0; a < sectionInfo.size(); a++) {
                            Student current = sectionInfo.get(a);
                            if (current.getCurrentEnroll() == sections[i]
                                .getSectionNumber()) {
                                courseData.add(current);
                                pidBst.insert(current.getPid(), currentIndex);
                                scoreBst.insert(current.getScore(),
                                    currentIndex);
                                nameBst.insert(current.getName(), currentIndex);
                                currentIndex++;
                            }
                        }
                    }
                }
            }
            sections[sectionFlag].setMerged(true);
            sections[sectionFlag].setNumberOfStudent(currentIndex);
            sections[sectionFlag].setNumHisOfStd(currentIndex);
            System.out.println("All sections merged at section " + (sectionFlag
                + 1));
        }
    }


    /**
     * Read the course .cvs file and store the corresponding data into different
     * sections.
     * 
     * @param cvsName
     *            the name of the course .cvs file.
     */
    private void cvsCourseReader(String cvsName) {
        String line;
        try {
            RandomAccessFile raf = new RandomAccessFile(cvsName, "rw");
            while ((line = raf.readLine()) != null) {
                String[] str = line.split("\\,");
                if (str.length > 3) {
                    int currScore = 0;
                    String currGrade;
                    if (str.length == 4) {
                        currScore = 0;
                        currGrade = "F";
                    }
                    else if (str.length == 5) {
                        currScore = Integer.parseInt(str[4]);
                        currGrade = "F";
                    }
                    else {
                        if (str[4].length() == 0) {
                            currScore = 0;
                        }
                        else {
                            currScore = Integer.parseInt(str[4]);
                        }
                        currGrade = str[5];
                    }
                    sections[(Integer.parseInt(str[0]) - 1)].courseInsert(Long
                        .parseLong(str[1]), new Name(str[2], str[3]), currScore,
                        currGrade);
                }
            }
            raf.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Read the course binary file and store the corresponding data into
     * different sections.
     * 
     * @param binName
     *            the name of the course binary file.
     */
    private void binCourseReader(String binName) {
        ArrayList<Integer> ptrList = new ArrayList<Integer>();
        ArrayList<Integer> recordList = new ArrayList<Integer>();
        ArrayList<Long> pidList = new ArrayList<Long>();
        ArrayList<String> firstNameList = new ArrayList<String>();
        ArrayList<String> lastNameList = new ArrayList<String>();
        ArrayList<Integer> scoreList = new ArrayList<Integer>();
        ArrayList<String> gradeList = new ArrayList<String>();
        byte[] titleByte = new byte[10];
        byte[] dummyByte = new byte[8];
        byte[] gradeByte = new byte[2];
        try {
            RandomAccessFile raf = new RandomAccessFile(binName, "r");
            StringBuffer sBuffer = new StringBuffer();
            ptrList.add((int)raf.getFilePointer());
            raf.seek(ptrList.get(0));
            raf.read(titleByte);
            @SuppressWarnings("unused")
            String titleStr = new String(titleByte);
            long currPtr = 0;
            int numOfSection = raf.readInt();
            int currNumOfRecord = raf.readInt();
            recordList.add(currNumOfRecord);
            while (currPtr <= raf.length()) {
                if (currPtr >= (raf.length() - 8)) {
                    break;
                }
                if (currNumOfRecord != 0) {
                    pidList.add(raf.readLong());
                    byte currByte;
                    sBuffer = new StringBuffer();
                    while ((currByte = raf.readByte()) != (byte)36) {
                        sBuffer = sBuffer.append((char)currByte);
                    }
                    firstNameList.add(sBuffer.toString());
                    sBuffer = new StringBuffer();
                    while ((currByte = raf.readByte()) != (byte)36) {
                        sBuffer = sBuffer.append((char)currByte);
                    }
                    lastNameList.add(sBuffer.toString());
                    scoreList.add(raf.readInt());
                    raf.read(gradeByte);
                    gradeList.add(new String(gradeByte));
                }
                raf.read(dummyByte);
                String dummyStr = new String(dummyByte);
                currPtr = raf.getFilePointer();
                if (dummyStr.equals("GOHOKIES") && currPtr < raf.length()) {
                    currNumOfRecord = raf.readInt();
                    recordList.add(currNumOfRecord);
                    currPtr = raf.getFilePointer();
                }
                else {
                    raf.seek(currPtr - 8);
                    currPtr = raf.getFilePointer();
                }
            }
            int k = 0; // current index for those arraylists
            for (int i = 0; i < numOfSection; i++) {
                for (int j = 0; j < recordList.get(i); j++) {
                    sections[i].courseInsert(pidList.get(k), new Name(
                        firstNameList.get(k), lastNameList.get(k)), scoreList
                            .get(k), gradeList.get(k));
                    k++;
                }
            }
            raf.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Read the student .cvs file and store the data into the BST of the local
     * student manager
     * 
     * @param cvsName
     *            The name of the student .cvs file.
     */
    private void cvsStudentReader(String cvsName) {
        String line;
        try {
            RandomAccessFile raf = new RandomAccessFile(cvsName, "rw");
            while ((line = raf.readLine()) != null) {
                String[] str = line.split("\\,");
                Name newName;
                if (str.length > 0) {
                    if (str[2].equals("")) {
                        newName = new Name(str[1], str[3]);
                    }
                    else {
                        newName = new Name(str[1], str[3], str[2]);
                    }
                    Student newStudent = new Student(newName, Long.parseLong(
                        str[0]));
                    sm.addStudent(newStudent);
                }
            }
            raf.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Create a new binary file and output the student data to the binary file
     * by iterating through the BST of the local student manager.
     * 
     * @param binName
     *            the name of the binary student file to output.
     */
    private void binStudentWriter(String binName) {
        try {
            RandomAccessFile raf = new RandomAccessFile(binName, "rw");
            raf.writeBytes("VTSTUDENTS");
            raf.writeInt(sm.size());
            for (Student s : sm.getStudentList()) {
                raf.writeLong(s.getPid());
                raf.writeBytes(s.getName().getFirstName());
                raf.writeBytes("$");
                String middle = s.getName().getMiddleName();
                if (middle == null) {
                    raf.writeBytes("");
                }
                raf.writeBytes("$");
                raf.writeBytes(s.getName().getLastName());
                raf.writeBytes("$");
                raf.writeBytes("GOHOKIES");
            }
            raf.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Create a new binary file and output the course data to the binary file by
     * iterating through the sections array.
     * 
     * @param binName
     *            the binary course file to create and output
     */
    private void binCourseWriter(String binName) {
        try {
            RandomAccessFile raf = new RandomAccessFile(binName, "rw");
            raf.setLength(0);
            raf.writeBytes("CS3114atVT");
            int largestSection = 1;
            for (int i = 0; i < sections.length; i++) {
                if (sections[i].getNumberOfStudent() > 0 && !sections[i]
                    .isMerged()) {
                    largestSection = i + 1;
                }
            }
            raf.writeInt(largestSection);
            for (int i = 0; i < largestSection; i++) {
                int currNumOfStudent = sections[i].getNumberOfStudent();
                raf.writeInt(currNumOfStudent);
                for (int j = 0; j < sections[i].getStudentData().size(); j++) {
                    if (sections[i].getStudentData().get(j)
                        .getCurrentEnroll() == (i + 1)) {
                        raf.writeLong(sections[i].getStudentData().get(j)
                            .getPid());
                        raf.writeBytes(sections[i].getStudentData().get(j)
                            .getName().getFirstName());
                        raf.writeBytes("$");
                        raf.writeBytes(sections[i].getStudentData().get(j)
                            .getName().getLastName());
                        raf.writeBytes("$");
                        raf.writeInt(sections[i].getStudentData().get(j)
                            .getScore());
                        raf.writeBytes(sections[i].getStudentData().get(j)
                            .getGrade());
                        if (sections[i].getStudentData().get(j).getGrade()
                            .length() == 1) {
                            raf.writeBytes(" ");
                        }
                    }
                }
                raf.writeBytes("GOHOKIES");
            }
            raf.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * Read the student binary file and store the corresponding data into the
     * BST of local student manager.
     * 
     * @param binName
     *            The name of the binary file to be read
     */
    public void binStudenteReader(String binName) {
        ArrayList<Integer> ptrList = new ArrayList<Integer>();
        ArrayList<Long> pidList = new ArrayList<Long>();
        ArrayList<String> firstNameList = new ArrayList<String>();
        ArrayList<String> middleNameList = new ArrayList<String>();
        ArrayList<String> lastNameList = new ArrayList<String>();
        StringBuffer sBuffer = new StringBuffer();
        byte[] titleByte = new byte[10];
        byte[] dummyByte = new byte[8];
        try {
            RandomAccessFile raf = new RandomAccessFile(binName, "rw");
            ptrList.add((int)raf.getFilePointer());
            raf.seek(ptrList.get(0));
            raf.read(titleByte);
            @SuppressWarnings("unused")
            String titleStr = new String(titleByte);
            long currPtr = 0;
            int numOfRecord = raf.readInt();
            while (currPtr < raf.length()) {
                pidList.add(raf.readLong());
                byte currByte;
                sBuffer = new StringBuffer();
                while ((currByte = raf.readByte()) != (byte)36) {
                    sBuffer = sBuffer.append((char)currByte);
                }
                firstNameList.add(sBuffer.toString());
                sBuffer = new StringBuffer();
                while ((currByte = raf.readByte()) != (byte)36) {
                    sBuffer.append((char)currByte);
                }
                middleNameList.add(sBuffer.toString());
                sBuffer = new StringBuffer();
                while ((currByte = raf.readByte()) != (byte)36) {
                    sBuffer.append((char)currByte);
                }
                lastNameList.add(sBuffer.toString());
                raf.read(dummyByte);
                currPtr = raf.getFilePointer();

            }
            raf.close();
            for (int i = 0; i < numOfRecord; i++) {
                Student newStudent;
                if (middleNameList.get(i).length() == 0) {
                    newStudent = new Student(new Name(firstNameList.get(i),
                        lastNameList.get(i)), pidList.get(i));
                }
                else {
                    Name newName = new Name(firstNameList.get(i), lastNameList
                        .get(i), middleNameList.get(i));

                    newStudent = new Student(newName, pidList.get(i));
                }
                sm.addStudent(newStudent);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * A getter to get the local student manager.
     * 
     * @return the student manager
     */
    public StudentManager getSM() {
        return this.sm;
    }


    /**
     * get latest command
     * 
     * @return latest command as a string
     */
    public String getLastestCommand() {
        return latestCommand;
    }

}
