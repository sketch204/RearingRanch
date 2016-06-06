package root.dataclass;

public class Player {
    private final int difficulty;
    private int time;
    private final String name;

    public Player (String name, int difficulty, int time) {
        this.difficulty = difficulty;
        this.time = time;
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public String getMinuteSecondTime() {
        int minutes = time/60;
        int seconds = (minutes > 0) ? time - (60 * minutes) : time;

        return "" + minutes + ":" + seconds;
    }

    public int getTime() {
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