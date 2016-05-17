import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import dataclass.Player;

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

    public Highscores (String name, int difficulty, int time, int score) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getScore() < score) {
                players.add(i, new Player (name, difficulty, time, score));
                break;
            }
        }
        this.recorded++;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        return 0;
    }
}
