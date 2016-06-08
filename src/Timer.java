package root;

import javax.swing.*;
import java.awt.*;

/**
 * The Timer class is used for measuring the amount of time it took for a player to finish the whole game.
 *
 * @author Inal Gotov
 * @version 1.3, 2016-06-03
 * Last Edited: 2016-06-06
 * Hours since 2016-06-03:
 *       Tamir: -
 *       Inal: 2:00
 */
public class Timer extends Thread {
    /** Holds the time to the 100th of a second. */
    protected long time = 0;
    /** States whether this timer is timing or not. */
    protected boolean timing = false;
    /** States whether this timer is alive or not. Used for disposing of timers. */
    protected boolean isAlive = true;
    /** The visual representation of this Timer. */
    private UI panel;

    /** Creates a new instance of this timer the count of which starts with 0.*/
    public Timer () {
        setDaemon(true);
        panel = new UI();
    }

    /**
     * Creates a new instance of this timer the count of which starts with the passed argument.
     * @param initialTime - The time from which to start the count.
     * */
    public Timer (long initialTime) {
        setDaemon(true);
        panel = new UI();
        time = initialTime;
    }

    /** The UI class is used for graphically visualizing this Timer. */
    class UI extends JPanel {
        /** The JLabel that stores a string representation of this Timer. */
        private JLabel time = new JLabel ("00:00:00");
        /** The layout that his JPanel uses. */
        private SpringLayout layout = new SpringLayout();
        /** Creates a new instance of the UI class, with a fully set-up and ready to go visual timer.*/
        public UI () {
            super ();
            setPreferredSize(new Dimension(108, 40));
            setLayout(layout);
            setOpaque(true);
            setForeground(new Color (34, 34, 34));
            setBackground(new Color(213, 194, 158, 220));
            time.setFont(new Font ("Chalkboard", 0, 25));
            layout.putConstraint(SpringLayout.WEST, time, 5, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, time, 5, SpringLayout.NORTH, this);
            add (time);
        }
        /**
         * Updates the graphical timer to the new time.
         * @param time - The new time to be displayed.
         * */
        public void updateTime (long time) {
            this.time.setText(formatTime(time));
            revalidate();
            repaint();
        }

        /**
         * Formats the time into a mm:ss:ms format.
         * @param time - The time to the 100th of a second, to format.
         * @return The formatted time.
         */
        public String formatTime (long time) {
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
    }

    /**
     * Returns the visual representation of this Timer.
     * @return The JPanel representation of this Timer.
     */
    public JPanel getVisual () {
        return panel;
    }

    /**
     * Returns the current time of this Timer.
     * @return The current time of this TImer.
     */
    public long getTime () {
        return time;
    }

    /** Continues the count of this Timer. */
    public void continueTimer () {
        timing = true;
    }

    /** Pauses the count of this Timer. */
    public void pauseTimer () {
        timing = false;
    }

    /** Kills this timer, effectively causing it to remove it self from the stack. */
    public void killTimer () {
        isAlive = false;
    }

    /**
     * States whether this Timer is timing or not.
     * @return Returns true if, and only if, this timer is currently not paused.
     */
    public boolean isTiming () {
        return timing;
    }

    /** Counts the amount of milliseconds that have passed since this Timer has been started. */
    protected void time () {
        while (isAlive) {
            if (timing) {
                time++;
                panel.updateTime(time);
            }
            try { Thread.sleep(10);}catch(InterruptedException e) {}
        }
    }

    /** The run method of this class. Starts the actual timing but must be called by an ExecutorService class. */
    public void run () {
        timing = true;
        time();
    }

    /**
     * Return a String representation of this class in a mm:ss:ml format.
     * @return A String representation of this class
     */
    public String toString () {
        return panel.formatTime(time);
    }
}
