import javax.swing.*;
import java.awt.*;
import java.awt.print.*;
import java.util.ArrayList;
import dataclass.Player;
import java.io.*;

/**
 * The Highscores class sorts an ArrayList of Players in descending order by their score, and displays the top 10
 * players with their name, difficulty level, time, and score onto the screen. The high scores may also be printed
 * with the EarlyEd Inc. logo. 
 *
 * @author Tamir Arnesty
 * @version 1 2016-05-15
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir: 0:30
 *       Inal: -
 */
public class Highscores extends JPanel implements Printable {

    private int recorded;

    ArrayList <Player> players = new ArrayList<Player>();

    public Highscores () {
        this.recorded++;
    }

    public Highscores (String name, int difficulty, int time) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getTime() > time) {
                players.add(i, new Player (name, difficulty, time));
                break;
            }
        }
        this.recorded++;
    }

    public void load () {
        String line = "";
        try {
            BufferedReader r = new BufferedReader (new FileReader ("Highscores.rrf"));
            while (line != null || !line.equals("END")) {
                line = r.readLine();
                if (line.equals("This is a Rearing Ranch highscores file.")) {
                    int size = Integer.parseInt(r.readLine());
                    line = r.readLine(); // divider line

                    for (int i = 0; i < size; i++) {
                        players.add(new Player (r.readLine(), Integer.parseInt(r.readLine()), Integer.parseInt(r.readLine())));
                    }
                }
            }
            if (sorted(players))
            r.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {}
    }

    private boolean sorted (ArrayList <Player> temp) {
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getTime() > temp.get(i+1).getTime()) {
                return false;
            }
        }
        return true;
    }

//    // ryan or alice's sort
//    public void sort() {
//        int temp, y;
//
//        for (int x = 1 ; x < players.size() ; x++)
//        {
//            temp = players.get(x).getTime();
//            for (y = x - 1 ; y >= 0 ; y--)
//            {
//                if (temp >= players.get(y).getTime())
//                {
//                    break;
//                }
//                players.get(y+1).getTime() = players.get(y).getTime();
//            }
//            players.get(y + 1).getTime() = temp;
//        }
//    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        return 0;
    }
}
