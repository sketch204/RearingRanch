import javax.swing.*;
import java.util.ArrayList;

/**
 * The Highscores class
 *
 * @author Tamir Arnesty
 * @version 1 2016-05-15
 */
public class Highscores extends JPanel {

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
}
