import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;

/**
 * The MasterFrame class takes care of all the panel handling and switching.
 * It is also responsible for the menu bar, as well as the closing of the game.
 *
 * @author Inal Gotov, modified by Tamir Arnesty.
 * @version 1.2, 2016-05-10.
 * Last Edited: 2016-06-09
 * Hours since 2016-05-10:
 *       Tamir: 2:30
 *       Inal: 3:00
 */
public class MasterFrame extends JFrame implements ActionListener {
    /** This holds a static instance of the MainMenu class. */
    private static MainMenu m;
    /** This holds a static instance of the DifficultyChooser class. */
    private static DifficultyChooser d;
    /** This holds a static instance of the Instructions class. */
    private static Instructions i;
    /** This holds a static instance of the HighscoresPanel class. */
    private static HighscoresPanel h;
    /** This holds a static instance of the JPanel that is currently on screen.*/
    public static Container current = new JPanel();

    /**
     * Creates an instance of a JFrame starting, initially displaying MainMenu.
     * It sets up this window, assigns a JMenuBar to this window and assigns a keyListener to this window.
     */
    public MasterFrame () {
        super ("Rearing Ranch");
        setSize(1280, 764);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(createMenuBar());
        current = getM();
        this.addKeyListener((KeyListener) current);
        add(current);
        setVisible(true);
        revalidate();
        repaint();
    }

    /** Initiates the program closing sequence. */
    static void initiateGoodbye () {
        new SplashScreen("GoodByeScreen");
    }

    /**
     * Returns a new instance of the DifficultyChooser class.
     * @return A new instance of the DifficultyChooser class.
     */
    public static DifficultyChooser getD() {
        return d = new DifficultyChooser();
    }

    /**
     * Returns a new instance of the Instructions class.
     * @return A new instance of the Instructions class.
     */
    public static Instructions getI() {
        return i = new Instructions();
    }

    /**
     * Creates a new JMenuBar for this window. It creates a File menu with a Quit option that quits
     * the program. It also has a Help menu with an About option which opens a JDialog with brief explanations
     * about this program. It also has a Help option which open a .chm file designed for this program.
     * @return The newly created JMenuBar
     */
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
            JDialog dialog = new JDialog(this, "About Us");
            JButton okayButton = new JButton ("OK");
            JLabel readMe = new JLabel("<html> Welcome to Rearing Ranch!" +
                    "<br> This program was created by EarlyEd Inc. members Tamir Arnesty" +
                    "<br> and Inal Gotov. This program is created in partnership with Dyke Enterprises" +
                    "<br> for educational purposes amongst pre-school and kindergarten children." +
                    "<br> <br> This is version 1 of Rearing Ranch, which may have potential upgrades." +
                    "<br> For help, select the help option in the Help menu. </html>");
            dialog.setSize(540, 200);
            dialog.setResizable(false);
            dialog.setLayout(new FlowLayout());
            dialog.setLocationRelativeTo(this);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            okayButton.addActionListener(e1 -> dialog.dispose());

            dialog.add(readMe);
            dialog.add(okayButton);

            dialog.setVisible(true);
        });

        helpItem.addActionListener(e -> {
            try
            {
                Runtime.getRuntime().exec("hh.exe help.chm");
                System.out.print("loaded");
            }
            catch (IOException ie)
            {
                JOptionPane.showMessageDialog(null,"Couldn't find Help File", "Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        return menu;
    }

    /**
     * Sets the current panel of this window to the passed argument, also sets the title of the window to the passed argument.
     * @param panel - The JPanel that should be set as the current panel of the window,
     * @param title - The title that this window should have.
     * */
    public void setPanel (JPanel panel, String title) {
        setTitle(title);
        remove(current);
        current = panel;
        add(current);
        revalidate();
        repaint();
    }

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

    /**
     * Returns a new instance of the MainMenu class.
     * @return A new instance of the MainMenu class.
     */
    public static MainMenu getM() {
        return m = new MainMenu();
    }

    /**
     * Returns a new instance of the HighscoresPanel class.
     * @return A new instance of the HighscoresPanel class.
     */
    public static JPanel getH() {
        return h = new HighscoresPanel();
    }
}
