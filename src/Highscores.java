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
 *       Tamir: 1:15
 *       Inal: -
 */
public class Highscores  {

    private int recorded;
    static File scores = new File ("Highscores.rrf");

    static ArrayList <Player> players = new ArrayList<Player>();

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

    public int getRecorded () {
        return recorded;
    }

    public void load () {
        String line = "";
        try {
            BufferedReader r = new BufferedReader (new FileReader(scores));
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
            if (!sorted(players))
                sort (players);
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

    private void sort(ArrayList <Player> tempList) {
        int temp, y;

        for (int x = 1 ; x < tempList.size() ; x++)
        {
            temp = tempList.get(x).getTime();
            for (y = x - 1 ; y >= 0 ; y--)
            {
                if (temp >= tempList.get(y).getTime())
                {
                    break;
                }
                players.set(y+1, players.get(y));
            }
            players.get(y + 1).setTime(temp);
        }
    }

    static void write () {

    }

    static void create () {
        if (!scores.exists())
            write();
    }

    static void delete () {
        if (scores.exists()) {
            scores.delete();
        System.out.println("deleted"); }
        else
            System.out.println("doesn't exist");
    }

}
