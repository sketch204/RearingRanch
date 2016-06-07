package root;

import javax.swing.*;
import java.awt.*;

/**
 * @author Inal Gotov
 * @version 1.3, 2016-06-03
 * Last Edited: 2016-06-06
 * Hours since 2016-06-03:
 *       Tamir: -
 *       Inal: 2
 *
 * Created by sketch204 on 2016-06-03.
 */
public class Timer extends Thread {
    private long time = 0;
    private boolean timing = false, isAlive = true;
    private UI panel;

    public Timer () {
        setDaemon(true);
        panel = new UI();
    }

    public Timer (long initialTime) {
        setDaemon(true);
        panel = new UI();
        time = initialTime;
    }

    class UI extends JPanel {
        private JLabel time = new JLabel ("00:00:00");
        private SpringLayout layout = new SpringLayout();

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

        public void updateTime (long time) {
            this.time.setText(formatTime(time));
            revalidate();
            repaint();
        }

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

    public JPanel getVisual () {
        return panel;
    }

    public long getTime () {
        return time;
    }

    public void continueTimer () {
        timing = true;
    }

    public void pauseTimer () {
        timing = false;
    }

    public void killTimer () {
        isAlive = false;
    }

    public boolean isTiming () {
        return timing;
    }

    private void time () {
        while (isAlive) {
            if (timing) {
                time++;
                panel.updateTime(time);
            }
            try { Thread.sleep(10);}catch(InterruptedException e) {}
        }
    }

    public void run () {
        timing = true;
        time();
    }

    public String toString () {
        return panel.formatTime(time);
    }
}
