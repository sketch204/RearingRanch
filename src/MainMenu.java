/** <Class name>
 * Created by tamirsway on 2016-05-11.
 *
 * <Variables or descriptions>
 * Last Edited: <date>
 * Hours since 2016-05-11:
 *       Tamir:
 *       Inal:
 */

import java.awt.*;
import javax.swing.*;

/** The MainMenu class creates the Panel that comes after the SplashScreen. Here, the user selects what they want to
 * do through buttons. They may play game, view instructions or highscores, or quit the game.
 *
 * <b>Global Variables: </b>
 * <br>
 * <b> layout </b> A SpringLayout reference that is used to organize the GUI components onto the screen.
 *
 * @author Tamir Arnesty.
 * @version 1, 2016-05-09.
 */

public class MainMenu extends JPanel {


    SpringLayout layout = new SpringLayout();

    /** The MainMenu constructor creates the panel with the layout manager, a size of 1280 by 720 and sets the
     * background color to blue. prepareGUI () is also called.
     *
     */
    public MainMenu () {
        super();
        setLayout(layout);
        setSize(1280, 720);
        setBackground(Color.BLUE);
        prepareGUI();


    }

    /** prepareGUI method adds the play game, instructions, highscores and quit buttons to the panel. It also adds a
     * brief intro label.
     *
     * <b>Local Variables: </b>
     * </p>
     * <b> playGame </b> Creates an instance of JButton with the value "Play Game".
     */
    private void prepareGUI () {
        //setLayout(layout);
        JButton highscores = new JButton ("Highscores");
        JButton quit = new JButton ("Quit Game");
        JButton instructions = new JButton("Instructions");
        JButton playGame = new JButton ("Play Game");
        JLabel intro = new JLabel("\nWelcome to Rearing Ranch! Press any of the following buttons to continue." +
                "\nPress Play Game to start! If you need help, press Instructions. Press the Highscores button to view previous highscores!" +
                "\nIf you want to leave, press Quit!");


        layout.putConstraint(SpringLayout.VERTICAL_CENTER, playGame, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, playGame, 0, SpringLayout.HORIZONTAL_CENTER, this);

        add (playGame);

        layout.putConstraint(SpringLayout.NORTH, intro, 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, intro, 100, SpringLayout.WEST, this);
        intro.setFont(new Font ("Times New Roman", Font.ITALIC, 15));
        add(intro);
        setVisible(true);
        revalidate();
        repaint();
    }

}
