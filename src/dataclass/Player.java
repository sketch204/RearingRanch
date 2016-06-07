package root.dataclass;

/**
 *
 * @author Inal Gotov
 * @version 1.3, 2016-05-15 // Incorrect
 * Last Edited: 2016-05-16
 * Hours since 2016-05-15:
 *       Tamir: -
 *       Inal: 1:00
 */
public class Player {
    private final int difficulty;
    private long time;
    private final String name;

    public Player (String name, int difficulty, long time) {
        this.difficulty = difficulty;
        this.time = time;
        this.name = formatName(name);
    }

    private String formatName (String name) {
        if (name.length() < 1 || name.trim().length() < 1) {
            name = "Player";
        }
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    public String getMinuteSecondTime() {
        long minutes = time/60;
        long seconds = (minutes > 0) ? time - (60 * minutes) : time;

        return "" + minutes + ":" + seconds;
    }

    public long getTime() {
        return time;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getName() {
        return name;
    }

    public String toString () {
        return name + " finished the game on difficulty " + difficulty + " in " + getMinuteSecondTime();
    }

    public void setTime(int time) {
        this.time = time;
    }
}