import java.io.*;

/**
 * a class for reading the given command file.
 * 
 * @author Shan Wu, Ko Yat Chan
 * @version 09.20.2019
 */
public class FileParser2 {
    /**
     * The CommandsExecution2 field that handles with different commands.
     */
    private CommandsExecution2 ce;


    /**
     * The constructor for reading the input file.
     * 
     * @param cmdFileName
     *            the name of cmd file
     */
    public FileParser2(String cmdFileName) {
        ce = new CommandsExecution2();
        readCmdFile(cmdFileName);
    }


    /**
     * A helper function for reading the .txt file
     * 
     * @param cmdFileName
     *            the name of the cmd file.
     */
    private void readCmdFile(String cmdFileName) {
        String line;
        try {
            RandomAccessFile raf = new RandomAccessFile(cmdFileName, "rw");
            while ((line = raf.readLine()) != null) {
                ce.checkCommands(line);
            }
            raf.close();
        }
        catch (FileNotFoundException e) {
            System.err.println("Cannot find the file: " + cmdFileName);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Unable to access the file: " + cmdFileName);
        }
    }
}
