import javax.swing.*;
import java.awt.*;

/** Instructions class creates a new panel that will replace main menu. The panel includes three labels that include instructions and
 * explanations of how to use the program and play the game.
 *
 * @author Tamir Arnesty
 * @version 1 2016-05-14
 *
 * Last Edited: 2016-05-16
 * Hours since 2016-05-14:
 *       Tamir: 3:00
 *       Inal: -
 *
 */
public class Instructions extends JPanel {

    /** <br> <b> layout </b> Instance of LayoutManager SpringLayout is used to organize GUI Components onto the screen. */
    private SpringLayout layout = new SpringLayout();
    /** <br> <b> mainMenu </b> Instance of JButton class used to return the user to Main Menu.*/
    static JButton mainMenu = new JButton("Return to Main Menu");
    /** Instructions constructor sets the layout manager to SpringLayout, sets the size to 1280x720, and references prepareGUI method. */
    Instructions() {
        super();
        setLayout(layout);
        setSize(1280, 720);
        prepareGUI();
    }

    /** prepareGUI method adds three labels onto the panel and sets their position on the screen using the SpringLayout
     * layout manager.
     *
     */
    private void prepareGUI() {

        /** <br> <b> greeting </b> Instance of JLabel class displays a greeting to the users when they open the Instructions window. */
        JLabel greeting = new JLabel ("<html> Welcome to Rearing Ranch! In this game you will learn to identify colours, stock, and do math!" +
                "<br> To play the game, go back to Main Menu by pressing the button below and then choose the Play Game button. " +
                "<br> In Main Menu, you may also choose to view highs cores of old games! Go challenge yourself and beat your last score! " +
                "<br> In Main Menu, you can also choose to quit the game if you get bored or don't want to learn anymore, which we " +
                "<br> don't recommend. Learning is the best thing you could ever do!");
        greeting.setFont(new Font("OCR A Std", Font.PLAIN, 14));

        layout.putConstraint(SpringLayout.NORTH, greeting, 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, greeting, 100, SpringLayout.WEST, this);
        add(greeting);

        /** <br> <b> instructions </b> Instance of JLabel class displays an explanation of the game and how to play.*/
        JLabel instructions = new JLabel ("<html> \tHOW TO PLAY: <br><br>The game is composed of three levels: Colours, Classification, and Arithmetic." +
                "<br> If you choose to play this game, you will be able to choose difficulty Easy, Medium, and Hard for each of the " +
                "<br> levels before you play. When playing, you will have buttons at the bottom of the screen that you must " +
                "<br> press in order to answer the questions.");
        instructions.setFont(new Font ("OCR A Std", Font.PLAIN, 14));

        layout.putConstraint(SpringLayout.NORTH, instructions, 10, SpringLayout.SOUTH, greeting);
        layout.putConstraint(SpringLayout.WEST, instructions, 100, SpringLayout.WEST, this);
        add(instructions);

        /** <br> <b> highscores </b> Instance of JLabel class displays an explanation on how to view high scores and
         * what is displayed in that panel.*/
        JLabel highscores = new JLabel ("<html> As well in Main Menu, you can choose to view high scores. These high scores have the name, time," +
                "<br> level and score of other players! Your score is added up based on how well you answer the questions. You don't lose " +
                "<br> points if you get the questions wrong. Just have fun! Get ready to learn and beat your score from before, if this " +
                "<br> isn't your first time playing.");
        highscores.setFont(new Font ("OCR A Std", Font.PLAIN, 14));

        layout.putConstraint(SpringLayout.NORTH, highscores, 10, SpringLayout.SOUTH, instructions);
        layout.putConstraint(SpringLayout.WEST, highscores, 100, SpringLayout.WEST, this);
        add(highscores);

        /** <br> <b> goBack </b> Instance of JLabel class that displays prompt message to return to main menu once the user
         * finishes reading the instructions above.*/
        JLabel goBack = new JLabel ("<html> Press the button to return to Main Menu.");
        goBack.setFont(new Font ("OCR A Std", Font.PLAIN, 14));

        layout.putConstraint(SpringLayout.NORTH, goBack, 30, SpringLayout.SOUTH, highscores);
        layout.putConstraint(SpringLayout.WEST, goBack, 445, SpringLayout.WEST, this);
        add(goBack);

        /** <br> <b> mainMenu </b> Instance of JButton class used to return the user to Main Menu.*/
        JButton mainMenu = new JButton("Return to Main Menu");
        mainMenu.setFont (new Font ("OCR A Std", Font.PLAIN, 14));
        mainMenu.setPreferredSize(MainMenu.buttonSize);
        mainMenu.addActionListener(MasterFrame.m);

        layout.putConstraint(SpringLayout.NORTH, mainMenu, 20, SpringLayout.SOUTH, goBack);
        layout.putConstraint(SpringLayout.WEST, mainMenu, 500, SpringLayout.WEST, this);
        add(mainMenu);

    }

}