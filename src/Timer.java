package root;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sketch204 on 2016-06-03.
 */
public class Timer extends Thread {
    private double time = 0.00;
    private boolean isTiming = false;

    public Timer () {

    }

    public Timer (double initialTime) {
        time = initialTime;
    }

    class UI extends JPanel {
        JLabel time = new JLabel ("00:00:00");
        public UI () {
            super ();
            setSize(100, 40);
            setOpaque(true);
            setBackground(new Color(213, 194, 158, 220));
            add (time);
        }

        public void updateTime (double time) {
            this.time.setText(formatTime(time));
        }

        private String formatTime (double time) {
            if (time > 6000) {

            }
            return "";
        }
    }


//    public Timer () {
//
//    }
//
//    public Timer (double initialTime) {
//        time = initialTime;
//    }
//
//    public void continueTimer () {
//        while (isTiming) {
//            time ++;
//            try {Thread.sleep(1000); } catch (InterruptedException e) {}
//        }
//    }
//
//    public void pauseTimer () {
//        isTiming = false;
//    }
//
//    public double getTime () {
//        return time;
//    }
//
//    public boolean isTiming () {
//        return isTiming;
//    }
//
//    @Override
//    public void run() {
//        isTiming = true;
//        continueTimer();
//    }
}
