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

    /**
     * Creates an instance of a JFrame starting with a SplashScreen.
     */
    private Container current = null;
    //MainMenu m = new MainMenu();

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
        aboutItem.addActionListener(e -> {
            JDialog about = new JDialog();
            JButton pressOkay = new JButton("Okay");
            pressOkay.setPreferredSize(new Dimension(50, 10));
            JButton references = new JButton ("Graphics References");
            references.addActionListener(e1 -> JOptionPane.showMessageDialog(null, "REFERENCES!!!!!!", "Graphics", JOptionPane.INFORMATION_MESSAGE));

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

        current = m;
        add(current);
        setVisible(true);
        revalidate();
        repaint();
    }

    public void setPanel (JPanel panel, String title) {
        setTitle(title);
        remove (current);
        current = panel;
        add (current);
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
            remove (current);
            current = h;
            add (current);
        } else if (ae.getActionCommand().equals("Instructions")) {
            System.out.println("Knock Knock");
            remove(current);
            current = i;
            add(current);
        } else if (ae.getActionCommand().equals("Quit Game") || ae.getActionCommand().equals("Quit")) {
            System.exit(0);
        } else if (ae.getActionCommand().equalsIgnoreCase("I'm a button")) {
            System.out.println("Knock Knock");
        }
        revalidate();
        repaint();
    }
}
