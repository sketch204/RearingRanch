package root;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static root.MainMenu.*;


/** Instructions class creates a new panel that will replace main menu. The panel includes three labels that display instructions and
 * explanations of how to use the program and play the game. To return to Main Menu, user must press the return button.
 *
 * @author Tamir Arnesty
 * @version 1 2016-05-14
 *
 * Last Edited: 2016-06-05
 * Hours since 2016-05-14:
 *       Tamir: 3:30
 *       Inal: 0:10
 *
 */
public class Instructions extends JPanel{
    /** <br> <b> layout </b> Instance of LayoutManager SpringLayout is used to organize GUI Components onto the screen. */
    private SpringLayout layout = new SpringLayout();
    /** <br> <b> mainMenu </b> Clone of a JButton from MainMenu used to return the user to Main Menu.*/
    JButton mainMenu = MasterFrame.getM().getMainMenuButton();

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
        /** <br> <b> instructions </b> Instance of JLabel class displays an explanation of the game and how to play.*/
        JLabel instructions = new JLabel ("<html> \tHOW TO PLAY: <br><br>The game is composed of three levels: Colours, Classification, and Arithmetic." +
                "<br> If you choose to play this game, you will be able to choose difficulty Easy, Medium, and Hard for each of the " +
                "<br> levels before you play. When playing, you will have buttons at the bottom of the screen that you must " +
                "<br> press in order to answer the questions.");
        /** <br> <b> highscores </b> Instance of JLabel class displays an explanation on how to view high scores and what is displayed in that panel.*/
        JLabel highscores = new JLabel ("<html> As well in Main Menu, you can choose to view high scores. These high scores have the name, time," +
                "<br> level and score of other players! Your score is added up based on how well you answer the questions. You don't lose " +
                "<br> points if you get the questions wrong. Just have fun! Get ready to learn and beat your score from before, if this " +
                "<br> isn't your first time playing.");
        /** <br> <b> goBack </b> Clone of a JLabel class from MainMenu that displays a prompt message to return to main menu. */
        JLabel goBack = MasterFrame.getM().getGoBack();

        /** <b> colour </b> The variable used to set the colour of the instructions labels. */
        Color colour;
        if (background.getPath().contains("background1"))
            colour = new Color (255, 255, 255);
        else if ((background.getPath().contains("background2")))
            colour = new Color(43, 167, 133);
        else if (background.getPath().contains("background3"))
            colour = new Color (164, 0, 3);
        else
            colour = new Color(0, 0, 0);

        greeting.setFont(new Font("OCR A Std", Font.PLAIN, 14));
        layout.putConstraint(SpringLayout.NORTH, greeting, 200, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, greeting, 100, SpringLayout.WEST, this);
        greeting.setForeground(colour);
//        greeting.setBackground(new Color (191, 184, 108, 220));
        add(greeting);

        instructions.setFont(new Font ("OCR A Std", Font.PLAIN, 14));
        layout.putConstraint(SpringLayout.NORTH, instructions, 10, SpringLayout.SOUTH, greeting);
        layout.putConstraint(SpringLayout.WEST, instructions, 100, SpringLayout.WEST, this);
        instructions.setForeground(colour);
//        instructions.setBackground(new Color (191, 184, 108, 220));
        add(instructions);

        highscores.setFont(new Font ("OCR A Std", Font.PLAIN, 14));
        layout.putConstraint(SpringLayout.NORTH, highscores, 10, SpringLayout.SOUTH, instructions);
        layout.putConstraint(SpringLayout.WEST, highscores, 100, SpringLayout.WEST, this);
        highscores.setForeground(colour);
//        highscores.setBackground(new Color (191, 184, 108, 220));
        add(highscores);

        goBack.setFont(new Font ("OCR A Std", Font.PLAIN, 14));
        layout.putConstraint(SpringLayout.NORTH, goBack, 30, SpringLayout.SOUTH, highscores);
        layout.putConstraint(SpringLayout.WEST, goBack, 445, SpringLayout.WEST, this);
        goBack.setForeground(colour);
//        goBack.setBackground(new Color (191, 184, 108, 220));
        add(goBack);

        mainMenu.setContentAreaFilled(true);
//        mainMenu.addKeyListener(this);
        mainMenu.requestFocusInWindow();
        mainMenu.setBorder(BorderFactory.createEtchedBorder());
        layout.putConstraint(SpringLayout.NORTH, mainMenu, 20, SpringLayout.SOUTH, goBack);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainMenu, 0, SpringLayout.HORIZONTAL_CENTER, this);
        add(mainMenu);
        System.out.println (mainMenu.hasFocus());

    }

    /** Overridden method paintComponent paints all background and game logo onto the screen.
     *
     * @param g Graphics used to hold what to paint on the screen.
     */
    @Override
    public void paintComponent (Graphics g) {
        g.drawImage(MainMenu.getBG(), 0, 0, null);
        g.drawImage(MainMenu.getImage("GameLogo"), 380, 0, null);
    }
}
