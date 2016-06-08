package root.dataclass;

/**
 * The PLayer class contains information about a single player that has played a game.
 * It is created at the end of each game and contains information about the user's time and
 * difficulty that the game was played on, as well as his name.
 *
 * @author Inal Gotov
 * @version 1.3, 2016-05-30
 * Last Edited: 2016-06-03
 * Hours since 2016-05-30:
 *       Tamir: -
 *       Inal: 1:00
 */
public class Player {
    /** Holds the difficulty level that the player completed the game on. */
    private int difficulty;
    /** Holds the time that the player completed the game in, to the 100th of a second. */
    private long time;
    /** Holds the name of the current player. */
    private String name;

    /**
     * Creates a new instance of a Player and sets its name, difficulty and time
     * @param name - The name of the new player.
     * @param difficulty - The difficulty that the player completed the game on.
     * @param time - The time that the player completed the game in.
     */
    public Player (String name, int difficulty, long time) {
        this.difficulty = difficulty;
        this.time = time;
        this.name = formatName(name);
    }

    /**
     * Formats the player name into by turning the first letter into a upper case letter and the rest into lower case letters.
     * @param name - The name to be formatted.
     * @return The formatted name.
     */
    private String formatName (String name) {
        if (name == null || name.length() < 1 || name.trim().length() < 1) {
            name = "Player";
        }
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    /**
     * Formats and returns the current player time in a mm:ss:ms format.
     * @return The formatted time.
     */
    public String getFormattedTime() {
        long [] units = {0, 0, 0};
        String result = "";

        if (time > 5999) {
            units[0] = Math.round(time/6000);
        } if (time > 99) {
            units[1] = Math.round((time - (units[0] * 6000)) / 100);
        }
        units[2] = (time - ((units[0] * 6000) + (units[1] * 100)));

        for (int h = 0; h < 2; h ++) {
            if (units[h] < 10)
                result += "0" + units[h] + ":";
            else
                result += units[h] + ":";
        }
        result += units[2];

        return result;
    }

    /**
     * Returns the time that the player took, to complete the game.
     * @return The time that the player took, to complete the game.
     * */
    public long getTime() {
        return time;
    }

    /**
     * Returns the difficulty that the player completed the game on.
     * @return The difficulty that the player completed the game on.
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Returns the current player's name.
     * @return The current player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of this player.
     * @return A string representation of this player.
     */
    public String toString () {
        return name + " finished the game on difficulty " + difficulty + " in " + getFormattedTime();
    }
}