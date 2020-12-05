import java.io.IOException;
// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * The course manager2 that contains the main function to
 * start the program.
 * 
 * @author Shan Wu, Ko Yat Chan
 * @version 10.16.2019
 */
public class Coursemanager2 {
    /**
     * main class, create a new courseManager and execute the commands
     * 
     * @param args
     *            input, the name of the input file
     * @throws IOException
     *             If the file does not exist, throw exception.
     */
    public static void main(String[] args) throws IOException {
        new FileParser2(args[0]);

    }
}
