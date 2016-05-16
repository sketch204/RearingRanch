package dataclass;

public class Player {
    private final int difficulty, time, score;
    private final String name;

    public Player (String name, int difficulty, int time, int score) {
        this.difficulty = difficulty;
        this.time = time;
        this.score = score;
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1, name.length()-1).toLowerCase();
    }

    private String getMinuteSecondTime() {
        int minutes = time/60;
        int seconds = (minutes > 0) ? time - (60 * minutes) : time ;
        return "" + minutes + ":" + seconds;
    }

    public int getScore() {
        return score;
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
        return name + "scored " + score + " points on " + difficulty + " difficulty in " + getMinuteSecondTime();
    }
}