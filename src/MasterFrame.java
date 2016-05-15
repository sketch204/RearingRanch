import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * This is the master frame program, it will contain all the JPanels inside it
 *
 * @author Inal Gotov, modified by Tamir Arnesty.
 * @version 1.2, 2016-05-10.
 */
public class MasterFrame extends JFrame implements ActionListener {
    static MainMenu m = new MainMenu();
    static DifficultyChooser d = new DifficultyChooser();
    static Instructions i = new Instructions();
    static Highscores h = new Highscores();
    static GoodByeScreen g = new GoodByeScreen();
    private Container current = new Panel();

    /**
     * Creates an instance of a JFrame starting with a SplashScreen.
     */
    public MasterFrame () {
        super ("Rearing Ranch");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JMenu file = new JMenu("File");
        JMenu help = new JMenu ("Help");
        JMenuItem aboutItem = new JMenuItem ("About");
        JMenuItem quit = new JMenuItem("Quit");
        JMenuBar menu = new JMenuBar();
        file.add(quit);
        help.add(aboutItem);
        menu.add(file);
        menu.add(help);
        setJMenuBar(menu);

        quit.addActionListener(this);
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog about = new JDialog();
                JButton pressOkay = new JButton("Okay");
                pressOkay.setPreferredSize(new Dimension(50, 10));
                JButton references = new JButton ("Graphics References");
                references.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "REFERENCES!!!!!!", "Graphics", JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                pressOkay.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        about.dispose();
                    }
                });
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
        }});

        MainMenu m = new MainMenu();
        GoodByeScreen s = new GoodByeScreen();
        /*GameStage gm = new GameStage(0) {

        GameStage gm = new GameStage(0, getContentPane()) {
            @Override
            protected void generateAnimals() {

            }

            @Override
            protected boolean inputLegal() {
                return false;
            }
        };*/

        current = new MainMenu();
        /*new MainMenu().playGame.addActionListener(this);
        new MainMenu().instructions.addActionListener(this);
        new MainMenu().highscores.addActionListener(this);
        new MainMenu().quit.addActionListener(this);*/
        add(current);
        setVisible(true);
        revalidate();
        repaint();
    }

    /**
     * Handles all the button clicks and ques each JPanel change.
     * @param ae Holds the value of the button that was clicked.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Highscores")) {

        } else if (ae.getActionCommand().equals("Instructions")) {
            remove(current);
            add(new Instructions());
        } else if (ae.getActionCommand().equals("Quit Game") || ae.getActionCommand().equals("Quit")) {
            System.exit(0);
        }
        revalidate();
        repaint();
    }

    public void setPanel (JPanel panel, String title) {
        setTitle(title);
        remove(current);
        current = panel;
        add(current);
        revalidate();
        repaint();
    }
}
