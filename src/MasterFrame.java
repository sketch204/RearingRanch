package root;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This is the master frame program, it will contain all the JPanels inside it
 *
 * @author Inal Gotov, modified by Tamir Arnesty.
 * @version 1.2, 2016-05-10.
 *
 * Last Edited: 2016-05-15
 * Hours since 2016-05-10:
 *       Tamir: 2:30
 *       Inal: 3:00
 */
public class MasterFrame extends JFrame implements ActionListener {
    public static MainMenu m = new MainMenu();
    public static DifficultyChooser d = new DifficultyChooser();
    public static Instructions i = new Instructions();
    public static HighscoresPanel h = new HighscoresPanel(0);
    public static GoodByeScreen g = new GoodByeScreen();

    private static Container current = new Panel();

    /**
     * Creates an instance of a JFrame starting with a SplashScreen.
     */
    public MasterFrame () {
        super ("Rearing Ranch");
        setSize(1280, 764);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setJMenuBar(createMenuBar());

        current = m;
        add(current);
        setVisible(true);
        revalidate();
        repaint();
    }

    static void initiateGoodbye () {
        current.removeAll();
        current.setVisible(false);
        current.revalidate();
        current.repaint();
        new SplashScreen("GoodByeScreen");
    }


    private JMenuBar createMenuBar () {
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu ("Help");
        JMenuItem quit = new JMenuItem("Quit");
        JMenuItem aboutItem = new JMenuItem ("About");
        JMenuItem helpItem = new JMenuItem("Help");

        file.add(quit);
        help.add(aboutItem);
        help.add(helpItem);
        menu.add(file);
        menu.add(help);

        quit.addActionListener(this);
        aboutItem.addActionListener(e -> {
            JDialog about = new JDialog();
            JButton pressOkay = new JButton("Okay");
            pressOkay.setPreferredSize(new Dimension(50, 10));
            JButton references = new JButton ("Graphics References");
            references.setPreferredSize(new Dimension(100, 200));
            references.addActionListener(e1 -> JOptionPane.showMessageDialog(null, "REFERENCES!!!!!!", "Graphics", JOptionPane.INFORMATION_MESSAGE));
            references.setPreferredSize(new Dimension (100, 20));
            pressOkay.addActionListener(e1 -> about.dispose());
            about.setLocationRelativeTo(current);
            about.setResizable(false);
            about.setSize(640, 480);

            about.add(new JLabel ("<html> Welcome to Rearing Ranch!" +
                    "<br> This program was created by EarlyEd Inc. members Tamir Arnesty" +
                    "<br> and Inal Gotov. This program is created in partnership with Dyke Enterprises" +
                    "<br> for educational purposes amongst pre-school and kindergarten children." +
                    "<br> <br> This is version 1 of Rearing Ranch, which may have potential upgrades." +
                    "<br> For help, select the help option in the Help menu. Make sure you are connected" +
                    "<br> to the internet."));
            about.setTitle("About Us");
            about.add(pressOkay);
            about.add(references);
            about.setVisible(true);
        });

        helpItem.addActionListener(e -> {
            try
            {
                Runtime.getRuntime().exec("help/help.chm");
            }
            catch (IOException ie)
            {
                JOptionPane.showMessageDialog(null,"Couldn't find Help File", "Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        return menu;
    }

    public void setPanel (JPanel panel, String title) {
        setTitle(title);
        remove(current);
        current = panel;
        add(current);
        revalidate();
        repaint();
    }

    private Thread timer = new Thread() {
        private long endTime;
        private long timePausedAt;
        private long startTime;
        private long elapsedTime;

        public long getStartTime() {
            return startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public long getTimePausedAt() {
            return timePausedAt;
        }

        public long getElapsedTime() {
            return elapsedTime;
        }

        public void startTimer() {
            startTime = System.nanoTime();
            countTimer();
        }

        public void countTimer () {
            elapsedTime = System.nanoTime() - getStartTime();
        }

        public void pause() {
            timePausedAt = System.nanoTime();
        }

        public void stopTimer () {
            endTime = System.nanoTime();
        }


    };


    /**
     * Handles all the button clicks and ques each JPanel change.
     * @param ae Holds the value of the button that was clicked.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Quit")) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you wish to quit?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                initiateGoodbye();
            }
        }
        revalidate();
        repaint();
    }
}
