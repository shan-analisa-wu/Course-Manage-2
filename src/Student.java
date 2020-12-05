
/**
 * This class creates a new Student object.
 * 
 * @author Shan Wu, Ko Yat Chan
 * @version 09.20.2019
 */
public class Student {
    private Name name;
    private long pid;
    private int score = 0;
    private int currentEnroll = 0;
    private String grade;


    /**
     * Construtor, create a new Student object
     * 
     * @param name
     *            The name of the student
     * @param pid
     *            The pid of the student
     */
    public Student(Name name, long pid) {
        this.name = name;
        this.pid = pid;
    }


    /**
     * This method get the enroll information of the student in the current
     * section
     * 
     * @return currentEnroll whether the student has enrolled in the current
     *         section
     */
    public int getCurrentEnroll() {
        return currentEnroll;
    }


    /**
     * This method set the current status of enrollment of the student
     * in the current sections
     * 
     * @param currentEnroll
     *            the status of enrollment of the student in the current section
     */
    public void setCurrentEnroll(int currentEnroll) {
        this.currentEnroll = currentEnroll;
    }


    /**
     * This method get the name of a student
     * 
     * @return name The name of a student
     */
    public Name getName() {
        return name;
    }


    /**
     * This command returns the student id of a student
     * 
     * @return pid The pid of a student
     */
    public Long getPid() {
        return pid;
    }


    /**
     * This command returns the score of a student
     * 
     * @return score The score of a student
     */
    public int getScore() {
        return score;
    }


    /**
     * This method gets the grade of a student
     * 
     * @return grade the grade of a student
     */
    public String getGrade() {
        return this.grade;
    }


    /**
     * This method sets the grade for a student
     * 
     * @param grade
     *            The grade to be set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }


    /**
     * This method assign a score to a specific student
     * 
     * @param score
     *            The score to be assigned to a specific student
     */
    public void setScore(int score) {
        this.score = score;
    }


    /**
     * This method print out the student id, full name and the score of a
     * specific student.
     * 
     * @return output The id, name and score of a student
     *         as a string
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (pid < 100000000) {
            String pidStr = String.valueOf(pid);
            int flag = 9 - pidStr.length();
            for (int i = 0; i < flag; i++) {
                str.append("0");
            }
            str.append(pid + ", " + name.getFirstName() + " " + name
                .getLastName() + ", " + "score = " + score);
        }
        else {
            str.append(pid + ", " + name.getFirstName() + " " + name
                .getLastName() + ", " + "score = " + score);
        }

        return str.toString();
    }


    /**
     * This method print out the student id, full name and the score of a
     * specific student.
     * 
     * @return output The id, name and score of a student
     *         as a string
     */
    public String toStringForCommand() {
        StringBuilder str = new StringBuilder();

        str.append(pid + ", " + name.getFirstName() + " " + name.getLastName()
            + ", " + "score = " + score);

        return str.toString();
    }

}
