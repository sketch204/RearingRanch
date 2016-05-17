import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The MainMenu class creates the main control panel for the program, otherwise called the main menu. In here, users may choose to play the game, view instructions
 * or high scores, and also quit the game. Handling is done via GUI components such as buttons.
 *
 *
 * @author Tamir Arnesty
 * @version 2 2016-05-11.
 *
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir: 6:00
 *       Inal: 1:30
 */
public class MainMenu extends JPanel implements ActionListener{

    // Sorry but these two are useless now
    /** <br> <b> BUTTON_HEIGHT </b> Final Integer that holds the standard height for the buttons in the MainMenu and Instructions classes.*/
    final int BUTTON_HEIGHT =  (new ImageIcon("src/pictures/Button-Icon/PlayButtonSmall.png")).getIconHeight();
    /** <br> <b> BUTTON_WIDTH </b> Final Integer that holds the standard width for the buttons in the MainMenu and Instructions classes.*/
    final int BUTTON_WIDTH = (new ImageIcon("src/pictures/Button-Icon/PlayButtonSmall.png")).getIconWidth();
    /** <br> <b> buttonSize </b> Instance of Dimension with the width and size of the Play Game Button. This Dimension is used to
     * set the default size of all the buttons on the screen.*/
    static Dimension buttonSize = new Dimension((new ImageIcon("src/pictures/Button-Icon/PlayButtonSmall.png")).getIconWidth(),
            (new ImageIcon("src/pictures/Button-Icon/PlayButtonSmall.png")).getIconHeight());

    static Font buttonFont = new Font ("OCR A Std", Font.PLAIN, 14);

    /** <br> <b> playGame </b> Instance of JButton class with the Play Button image. The button is used to start the game. */
    private JButton playGame = new JButton (new ImageIcon ("src/pictures/Button-Icon/PlayButtonSmall.png"));
    /** <br> <b> instructions </b> Instance of JButton class with the value 'Instructions'. The button is used to view the instructions for the game. */
    private JButton instructions = new JButton("Instructions");
    /** <br> <b> highscores </b> Instance of JButton class with the value 'High Scores'. The button is used to view the high scores for the game. */
    private JButton highscores = new JButton ("High Scores", new ImageIcon("src/pictures/Button-Icon/Highscores.jpg"));
    /** <br> <b> quit </b> Instance of JButton class with the value 'Quit'. The button is used to exit the game. */
    private JButton quit = new JButton ("Quit Game", new ImageIcon ("src/pictures/Button-Icon/red x.png"));
    /** <br> <b> layout </b> Instance of LayoutManager SpringLayout is used to organize GUI Components onto the screen. */
    private SpringLayout layout = new SpringLayout();

    /** The MainMenu constructor sets the layout manager to SpringLayout, sets the size to 1280x720, and references prepareGUI method. */
    MainMenu() {
        super();
        setLayout(layout);
        setSize(1280, 720);
        prepareGUI();


    }

    /** prepareGUI method adds the play game, instructions, highscores and quit buttons to the panel, as well as adds a
     * brief intro label.
     */
    private void prepareGUI () {

        // action listener
        playGame.addActionListener(this);
        instructions.addActionListener(this);
        quit.addActionListener(this);
        highscores.addActionListener(this);

        //---- set size of buttons to BUTTON_WIDTH and BUTTON_HEIGHT
        //---- set font of buttons to OCR A Std size 14.
        // play game
        playGame.setBorder(BorderFactory.createEmptyBorder());
        playGame.setContentAreaFilled(true);
        // instructions
        instructions.setPreferredSize(buttonSize);
        instructions.setFont (buttonFont);

        // highscores
        highscores.setPreferredSize(buttonSize);
        highscores.setFont (buttonFont);

        // quit game
        quit.setPreferredSize(buttonSize);
        quit.setFont (buttonFont);

        /** <br> <b> intro </b> Instance of JLabel class that stores an introduction message to the user. */
        JLabel intro = new JLabel("<html> Welcome to Rearing Ranch! Press any of the following buttons to continue." +
                "<br>Press Play Game to start! If you need help, press Instructions. Press the Highscores button to view previous highscores!" +
                "<br>If you want to leave, press Quit! </html>");

        layout.putConstraint(SpringLayout.NORTH, playGame, 200, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, playGame, 0, SpringLayout.HORIZONTAL_CENTER, this);
        add (playGame);

        layout.putConstraint(SpringLayout.NORTH, instructions, 10, SpringLayout.SOUTH, playGame);
        layout.putConstraint(SpringLayout.WEST, instructions, 0, SpringLayout.WEST, playGame);
        add(instructions);

        layout.putConstraint(SpringLayout.NORTH, highscores, 10, SpringLayout.SOUTH, instructions);
        layout.putConstraint(SpringLayout.WEST, highscores, 0, SpringLayout.WEST, instructions);
        add(highscores);

        layout.putConstraint(SpringLayout.NORTH, quit, 10, SpringLayout.SOUTH, highscores);
        layout.putConstraint(SpringLayout.WEST, quit, 0, SpringLayout.WEST, highscores);
        add(quit);

        layout.putConstraint(SpringLayout.NORTH, intro, 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, intro, 100, SpringLayout.WEST, this);
        intro.setFont(new Font ("OCR A Std", Font.PLAIN, 13));
        add(intro);
        setVisible(true);
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Return to Main Menu"))
            RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().m, "Rearing Ranch");
        else if (e.getSource().equals(playGame)) {
            RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().d, "Difficulty Chooser");
            // Temporary
            RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().m, "Rearing Ranch");
        } else if (e.getSource().equals((instructions)))
            RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().i, "Instructions");
        else if (e.getSource().equals(highscores)) {
            RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().h, "Highscores");
            // Temporary
            RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().m, "Rearing Ranch");
        } else if (e.getSource().equals(quit)) {
            RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().g, "Good Bye!");
            System.exit(0);
        }
    }
}

