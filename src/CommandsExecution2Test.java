import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;

/**
 * Commands Execution Test
 * 
 * @author Shan Wu, Ko Yat Chan
 * @version 09.20.2019
 */
public class CommandsExecution2Test extends TestCase {
    private CommandsExecution2 ce1;

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();


    /**
     * setup
     */
    public void setUp() {
        ce1 = new CommandsExecution2();
    }


    /**
     * test checkcommands
     */
    public void testCheckCommands() {
        String line1 = "loadcoursedata CS3114.csv";
        System.setOut(new PrintStream(output));
        ce1.checkCommands(line1);
        String line2 = "";
        String line3 = "loadstudentdata students.csv";
        String line4 = "";
        String line5 = "";
        String line6 = "loadcoursedata CS3114.data";
        String line7 = "";
        String line8 = "section 1";
        String line9 = "";
        String line10 = "";
        String line11 = "dumpsection";
        String line12 = "insert 977159896 Naomi Cote";
        String line13 = "";
        String line14 = "insert 256593948 Sandra Duncan";
        String line15 = "";
        String line16 = "insert 239721904 Aileen   Ford";
        String line17 = "";
        String line18 = "insert 239721905 Caleb   Foley";
        String line19 = "insert 239721905    Aileen Ford";
        String line20 = "score 91";
        String line21 = "";
        String line22 = "searchid 394691224";
        String line23 = "score 100";
        String line24 = "searchid 291935757";
        String line25 = "score 100";
        String line26 = "searchid 239721905";
        String line27 = "";
        String line28 = "search Mostafa Kamel";
        String line29 = "";
        String line30 = "search Sage Forbes";
        String line31 = "";
        String line32 = "search Naomi";
        String line33 = "dumpsection";
        String line34 = "";
        String line35 = "remove Mostafa Kamel";
        String line36 = "";
        String line37 = "remove Naomi Cote";
        String line38 = "";
        String line39 = "search Naomi";
        String line40 = "";
        String line41 = "remove Naomi Cote";
        String line42 = "";
        String line43 = "section 3";
        String line44 = "dumpsection";
        String line45 = "insert 349195701 Gloria     Chavez";
        String line46 = "score 100";
        String line47 = "";
        String line48 = "grade";
        String line49 = "section 1";
        String line50 = "grade";
        String line51 = "stat";
        String line52 = "";
        String line53 = "merge";
        String line54 = "section 4";
        String line55 = "merge";
        String line56 = "insert 635043110 Ishmael   Carlson";
        String line57 = "grade";
        String line58 = "stat";
        String line59 = "";
        String line60 = "list C*";
        String line61 = "";
        String line62 = "section 2";
        String line63 = "";
        String line64 = "list C*";
        String line65 = "list F";
        String line66 = "list C+";
        String line67 = "list C-";
        String line68 = "list C";
        String line69 = "";
        String line70 = "remove 256593948";
        String line71 = "findpair 10";
        String line72 = "";
        String line73 = "dumpsection";
        String line74 = "section 3";
        String line75 = "";
        String line76 = "clearsection";
        String line77 = "";
        String line78 = "section 4";
        String line79 = "merge";
        String line80 = "";
        String line81 = "section 5";
        String line82 = "merge";
        String line83 = "grade";
        String line84 = "stat";
        String line85 = "";
        String line86 = "savestudentdata mystudents.data";
        String line87 = "savecoursedata cs3114course.data";
        String line88 = "";
        String line89 = "clearcoursedata";
        String line90 = "";
        String line91 = "section 1";
        String line92 = "dumpsection";
        String line93 = "";
        String line94 = "loadcoursedata cs3114course.data";
        String line95 = "section 1";
        String line96 = "dumpsection";
        String line97 = "";
        String line98 = "loadcoursedata CS3114_2.csv";
        String line99 = "section 2";
        String line100 = "dumpsection";

        ce1.checkCommands(line2);

        ce1.checkCommands(line3);

        ce1.checkCommands(line4);

        ce1.checkCommands(line5);

        ce1.checkCommands(line6);

        ce1.checkCommands(line7);

        ce1.checkCommands(line8);

        ce1.checkCommands(line9);

        ce1.checkCommands(line10);

        ce1.checkCommands(line11);

        ce1.checkCommands(line12);

        ce1.checkCommands(line13);
        ce1.checkCommands(line14);
        ce1.checkCommands(line15);

        ce1.checkCommands(line16);

        ce1.checkCommands(line17);
        ce1.checkCommands(line18);
        ce1.checkCommands(line19);
        ce1.checkCommands(line20);
        ce1.checkCommands(line21);
        ce1.checkCommands(line22);
        ce1.checkCommands(line23);
        ce1.checkCommands(line24);
        ce1.checkCommands(line25);
        ce1.checkCommands(line26);
        ce1.checkCommands(line27);
        ce1.checkCommands(line28);
        ce1.checkCommands(line29);
        ce1.checkCommands(line30);
        ce1.checkCommands(line31);
        ce1.checkCommands(line32);
        ce1.checkCommands(line33);
        ce1.checkCommands(line34);
        ce1.checkCommands(line35);
        ce1.checkCommands(line36);
        ce1.checkCommands(line37);
        ce1.checkCommands(line38);
        ce1.checkCommands(line39);
        ce1.checkCommands(line40);
        ce1.checkCommands(line41);
        ce1.checkCommands(line42);
        ce1.checkCommands(line43);
        ce1.checkCommands(line44);
        ce1.checkCommands(line45);
        ce1.checkCommands(line46);
        ce1.checkCommands(line47);
        ce1.checkCommands(line48);
        ce1.checkCommands(line49);
        ce1.checkCommands(line50);
        ce1.checkCommands(line51);
        ce1.checkCommands(line52);
        ce1.checkCommands(line53);
        ce1.checkCommands(line54);
        ce1.checkCommands(line55);
        ce1.checkCommands(line56);
        ce1.checkCommands(line57);
        ce1.checkCommands(line58);
        ce1.checkCommands(line59);
        ce1.checkCommands(line60);
        ce1.checkCommands(line61);
        ce1.checkCommands(line62);
        ce1.checkCommands(line63);
        ce1.checkCommands(line64);
        ce1.checkCommands(line65);
        ce1.checkCommands(line66);
        ce1.checkCommands(line67);
        ce1.checkCommands(line68);
        ce1.checkCommands(line69);
        ce1.checkCommands(line70);
        ce1.checkCommands(line71);
        ce1.checkCommands(line72);
        ce1.checkCommands(line73);
        ce1.checkCommands(line74);
        ce1.checkCommands(line75);
        ce1.checkCommands(line76);
        ce1.checkCommands(line77);
        ce1.checkCommands(line78);
        ce1.checkCommands(line79);
        ce1.checkCommands(line80);
        ce1.checkCommands(line81);
        ce1.checkCommands(line82);
        ce1.checkCommands(line83);
        ce1.checkCommands(line84);
        ce1.checkCommands(line85);
        ce1.checkCommands(line86);
        ce1.checkCommands(line87);
        ce1.checkCommands(line88);
        ce1.checkCommands(line89);
        ce1.checkCommands(line90);
        ce1.checkCommands(line91);
        ce1.checkCommands(line92);
        ce1.checkCommands(line93);
        ce1.checkCommands(line94);
        ce1.checkCommands(line95);
        ce1.checkCommands(line96);
        ce1.checkCommands(line97);
        ce1.checkCommands(line98);
        ce1.checkCommands(line99);
        ce1.checkCommands(line100);
        System.out.print(output.toString());
        boolean result = true;
        assertTrue(result);

    }

}
