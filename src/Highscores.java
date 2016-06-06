package root;

import java.awt.*;
import java.awt.print.*;
import java.util.ArrayList;
import root.dataclass.Player;
import java.io.*;

/**
 * The Highscores class sorts an ArrayList of Players in descending order by their score, prints the high scores,
 * creates new .rrh files and updates old ones, or deletes them based on user choice.
 * NOTE: If the .rrh file doesn't exist, one will be created.
 *
 * @author Tamir Arnesty
 * @version 1 2016-05-15
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir: 3:00
 *       Inal: -
 */
public class Highscores  extends HighscoresPanel{

    /** <b> recorded </b> Integer that stores the number of high scores recorded.*/
    static private int recorded;
    /** <b> scores </b> A RearingRanch high scores file reference. */
    static File scores = new File ("src/files/Highscores.rrh");
    /** <b> players </b> An ArrayList of the Players class.*/
    static ArrayList <Player> players = new ArrayList<Player>();
    /** <b> loadedOnce </b> Boolean to determine when to load the high scores from the file. */
    private static boolean loadedOnce = false;

    /** Highscores constructor increments the recorded counter. */
    public Highscores () {
        this.recorded++;
    }

    /** Highscores overloaded constructor creates a new Player with a name, difficulty, and time as the score.
     * Recorded counter is also incremented.
     *
     * @param name The name of the player.
     * @param difficulty The difficulty the player chose.
     * @param time The amount of time it took the player to finish the game.
     */
    public Highscores (String name, int difficulty, int time) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getTime() > time) {
                players.add(i, new Player (name, difficulty, time));
                break;
            }
        }
        this.recorded++;
    }

    /** Method getRecorded returns the amount of recorded scores.
     * @return int of the amount of scores recorded.
     */
    public int getRecorded () {
        return recorded;
    }

    // difficulty to sort the list
    public static void load(int difficulty) {
        if (!loadedOnce) {
            clear();
            // look for only the certain difficulty
            String line = "";
            try {
                BufferedReader r = new BufferedReader(new FileReader(scores));
                while (line != null && !line.equals("END" )) {
                    line = r.readLine();
                    if (line.equals("This is a Rearing Ranch highscores file." )) {
                        int size = Integer.parseInt(r.readLine());
                        line = r.readLine(); // divider line
                        System.out.println(line);
                        for (int i = 0; i < size; i++) {
                            players.add(new Player(r.readLine(), Integer.parseInt(r.readLine()), Integer.parseInt(r.readLine())));
                            r.readLine(); // divider line
                        }
                        r.readLine();
                        line = r.readLine();
                    }
                }
                if (!sorted(players))
                    sort(players);
                r.close();
                loadedOnce = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            }
        }
    }

    /** Method sorted compares consequent scores to determine whether the list of scores is sorted.
     *
     * <br> <b> If Statements: </b>
     * <br> 1st: Return true if the amount of scores recorded is less than 2, since the list is sorted.
     * <br> 2nd: Return false if the time for the first index is greater than the time of the following
     * index, since the list is unsorted.
     *
     * <br> <b> For Loops: </b>
     * <br> 1st: Used to traverse through the list of Players to compare times.
     *
     * @param tempList The ArrayList of players that is to be compared.
     * @return true if the list is sorted in descending order.
     */
    private static boolean sorted(ArrayList<Player> tempList) {
        if (tempList.size() < 2) return true;
        for (int i = 0; i < tempList.size()-1; i++) {
            if (tempList.get(i).getTime() > tempList.get(i+1).getTime()) {
                return false;
            }
        }
        return true;
    }

    private static void sort(ArrayList<Player> tempList) {
        int y;
        Player tempP;
        for (int x = 1 ; x < tempList.size() ; x++)
        {
            tempP = tempList.get(x);
            for (y = x - 1 ; y >= 0 ; y--)
            {
                if (tempP.getTime() >= tempList.get(y).getTime())
                {
                    break;
                }
                tempList.set(y+1, players.get(y));
            }
            tempList.set(y + 1, tempP);
        }
    }

    static void write () {
        try {

            PrintWriter w = new PrintWriter(new FileWriter(scores));
            w.println("This is a Rearing Ranch highscores file.");
            w.println(recorded);
            w.println("---- divider ----");

            for (int i = 0; i < players.size(); i++) {
                w.println(players.get(i).getName());
                w.println(players.get(i).getDifficulty());
                w.println(players.get(i).getTime());
                w.println("---- divider ----");
            }
            w.close();
        } catch (IOException e) {
        }
    }

    static void create () {
        if (!scores.exists()) {
            System.out.println("Doesnt exist.. creating..");
            write();
            System.out.println("created...");
        }
    }

    static private void clear () {
        players = new ArrayList<Player>();
    }
    /**
     *
     */
    static void delete () {
        if (scores.exists()) {
            scores.delete();
            System.out.println("deleted");
            clear();
            create();
        } else
            System.out.println("doesn't exist");
    }

}
