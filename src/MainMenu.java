import java.awt.*;
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
 *       Tamir: 6
 *       Inal:
 */
class MainMenu extends JPanel {

    /** <br> <b> BUTTON_HEIGHT </b> Final Integer that holds the standard height for the buttons in the MainMenu and Instructions classes.*/
    final int BUTTON_HEIGHT =  (new ImageIcon("src/pictures/PlayButtonSmall.png")).getIconHeight();
    /** <br> <b> BUTTON_WIDTH </b> Final Integer that holds the standard width for the buttons in the MainMenu and Instructions classes.*/
    final int BUTTON_WIDTH = (new ImageIcon("src/pictures/PlayButtonSmall.png")).getIconWidth();
    /** <br> <b> playGame </b> Instance of JButton class with the Play Button image. The button is used to start the game. */
    private JButton playGame = new JButton (new ImageIcon ("src/pictures/PlayButtonSmall.png"));
    /** <br> <b> instructions </b> Instance of JButton class with the value 'Instructions'. The button is used to view the instructions for the game. */
    private JButton instructions = new JButton("Instructions");
    /** <br> <b> highscores </b> Instance of JButton class with the value 'High Scores'. The button is used to view the high scores for the game. */
    private JButton highscores = new JButton ("High Scores", new ImageIcon("src/pictures/Highscores.jpg"));
    /** <br> <b> quit </b> Instance of JButton class with the value 'Quit'. The button is used to exit the game. */
    private JButton quit = new JButton ("Quit Game", new ImageIcon ("src/pictures/red x.png"));
    /** <br> <b> layout </b> Instance of LayoutManager SpringLayout is used to organize GUI Components onto the screen. */
    private SpringLayout layout = new SpringLayout();

    /** The MainMenu constructor sets the layout manager to SpringLayout, sets the size to 1280x720, and references prepareGUI method. */
    MainMenu() {
        super();
        setLayout(layout);
        setSize(1280, 720);
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
        //---- set size of buttons to BUTTON_WIDTH and BUTTON_HEIGHT
        //---- set font of buttons to OCR A Std size 14.
        // play game
        playGame.setBorder(BorderFactory.createEmptyBorder());
        playGame.setContentAreaFilled(true);
        // instructions
        instructions.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        instructions.setFont (new Font ("OCR A Std", Font.PLAIN, 14));

        // highscores
        highscores.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        highscores.setFont (new Font ("OCR A Std", Font.PLAIN, 14));

        // quit game
        quit.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        quit.setFont (new Font ("OCR A Std", Font.PLAIN, 14));

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


}


class Instructions extends JPanel {

    private SpringLayout layout = new SpringLayout();

    /** Instructions constructor sets the layout manager to SpringLayout, sets the size to 1280x720, and references prepareGUI method. */
    Instructions() {
        super();
        setLayout(layout);
        setSize(1280, 720);
        prepareGUI();
    }

    private void prepareGUI() {
        JLabel greeting = new JLabel ("<html> Welcome to Rearing Ranch! In this game you will learn to identify colours, animals, and do math!" +
                "<br> To play the game, go back to Main Menu by pressing the button below and then choose the Play Game button. " +
                "<br> In Main Menu, you may also choose to view highscores of old games! Go challenge yourself and beat your last score! " +
                "<br> In Main Menu, you can also choose to quit the game if you get bored or don't want to learn anymore, which we " +
                "<br> don't recommend. Learning is the best thing you could ever do!");
        greeting.setFont(new Font ("OCR A Std", Font.PLAIN, 14));

        layout.putConstraint(SpringLayout.NORTH, greeting, 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, greeting, 100, SpringLayout.WEST, this);
        add(greeting);

        JLabel instructions = new JLabel ("<html> The game is composed of three levels: Colours, Classification, and Arithmetic." +
                "<br> If you choose to play this game, you will be able to choose difficulty Easy, Medium, and Hard for each of the " +
                "<br> levels before you play. When playing, you will have buttons at the bottom of the screen that you must " +
                "<br> press in order to answer the questions.");
        instructions.setFont(new Font ("OCR A Std", Font.PLAIN, 14));

        layout.putConstraint(SpringLayout.NORTH, instructions, 10, SpringLayout.SOUTH, greeting);
        layout.putConstraint(SpringLayout.WEST, instructions, 100, SpringLayout.WEST, this);
        add(instructions);

        JLabel highscores = new JLabel ("<html> As well in Main Menu, you can choose to view highscores. There highscores have the name, time," +
                "<br> level and score of other players! Your score is added up based on how well you answer the questions. You don't lose " +
                "<br> points if you get the questions wrong. Just have fun! Get ready to learn and beat your score from before, if this " +
                "<br> isn't your first time playing.");
        highscores.setFont(new Font ("OCR A Std", Font.PLAIN, 14));

        layout.putConstraint(SpringLayout.NORTH, highscores, 10, SpringLayout.SOUTH, instructions);
        layout.putConstraint(SpringLayout.WEST, highscores, 100, SpringLayout.WEST, this);
        add(highscores);

        JLabel goBack = new JLabel ("<html> Press the button to return to Main Menu.");
        goBack.setFont(new Font ("OCR A Std", Font.PLAIN, 14));

        layout.putConstraint(SpringLayout.NORTH, goBack, 30, SpringLayout.SOUTH, highscores);
        layout.putConstraint(SpringLayout.WEST, goBack, 445, SpringLayout.WEST, this);
        add(goBack);

        JButton mainMenu = new JButton("Return to Main Menu");
        mainMenu.setFont (new Font ("OCR A Std", Font.PLAIN, 14));
        mainMenu.setPreferredSize(new Dimension(new MainMenu().BUTTON_WIDTH, new MainMenu().BUTTON_HEIGHT));

        layout.putConstraint(SpringLayout.NORTH, mainMenu, 20, SpringLayout.SOUTH, goBack);
        layout.putConstraint(SpringLayout.WEST, mainMenu, 500, SpringLayout.WEST, this);
        add(mainMenu);




    }

}
