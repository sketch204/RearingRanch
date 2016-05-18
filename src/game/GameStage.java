package game;

import dataclass.Animal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The GameStage class acts as a parent class for ColorChooser, Animal Classifier and
 * Arithmetics classes. It handles most of the user interface components such as; buttons
 * user input, label, animal drawing etc...
 *
 * It is also responsible for handling all button clicks as well as keeping track of game activity.
 *
 * <b>Global Variables: </b>
 * </p>
 * <b>stock </b> Contains the instance of all the stock in the current barn.
 *
 * @author Inal Gotov, Modified by: Tamir Arnesty
 * @version 1.3, 2016-05-09.
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir: -
 *       Inal: 9:45
 */
public abstract class GameStage extends JPanel implements ActionListener {
    /**
     * The array that holds all the stock currently on screen.
     * This is the array that is used to check for a legal input.
     */
    protected Animal [] stock;
    /**
     * The array that holds all the game buttons currently on screen.
     */
    protected JButton [] buttons;
    /**
     * The layout of this panel.
     */
    protected SpringLayout layout = new SpringLayout();
    /**
     * Holds the difficulty of the current stage
     */
    protected final int difficulty;
    /**
     * The @see java.util.ArrayList that holds all JLabel currently in the inputBar.
     */
    private ArrayList <JLabel> inputTexts = new ArrayList<JLabel>();
    /**
     * The @see javax.swing.JButton on the inputBar that is responsible for initiating the legality check.
     */
    private JButton submitButton = new JButton(new ImageIcon("src/pictures/Button-Icon/inputBar/Icon-Submit.png"));
    /**
     * The @see javax.swing.JButton on the inputBar that is responsible for erasing the last element on the inputBar.
     */
    private JButton eraseButton = new JButton(new ImageIcon("src/pictures/Button-Icon/inputBar/Icon-Erase.png"));
    /**
     * The string that holds the input currently entered in the inputBar.
     * This is the value, legality of which determines the outcome of the game.
     */
    private String inputBar = "";
    /**
     * States whether the game is currently active.
     */
    private boolean gameActive = true;

    /**
     * Creates an instance of a GameStage. Sets up the panel and creates all graphics for the game.
     */
    public GameStage (int difficulty) {
        super();

        this.difficulty = difficulty;

        this.setLayout(layout);
        this.setSize(1280, 720);
        this.setBackground(new Color (203, 203, 203));

        generateAnimals();
        try {
            prepareGUI();
        } catch (NullPointerException e) {}

        this.setVisible(true);
    }

    /**
     * Draws the background for this stage, as well as all the needed game and inputBar buttons.
     *
     * <b>Local Variables </b>
     * </p>
     * <b>x </b> Holds the width of inputBar buttons.
     * </p>
     * <b>y </b> Holds the height of inputBar buttons.
     */
    private void prepareGUI () throws NullPointerException{
        generateBackground();
        createGameButtons();

        int x = new ImageIcon("src/pictures/Button-Icon/inputBar/Icon-Submit.png").getIconWidth(),
            y = new ImageIcon("src/pictures/Button-Icon/inputBar/Icon-Submit.png").getIconHeight();;
        submitButton.setSize (x, y);
        submitButton.setPreferredSize(new Dimension(x, y));
        submitButton.addActionListener(this);
        submitButton.setBorder(BorderFactory.createLineBorder(new Color (34, 34,34), 1, true));

        layout.putConstraint(SpringLayout.WEST, submitButton, this.getWidth() - submitButton.getWidth() - 1, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, submitButton, -submitButton.getHeight(), SpringLayout.NORTH, buttons[0]);

        eraseButton.setSize(x, y);
        eraseButton.setPreferredSize(new Dimension(x, y));
        eraseButton.addActionListener(this);
        eraseButton.setBorder(BorderFactory.createLineBorder(new Color (34, 34,34), 1, true));

        layout.putConstraint(SpringLayout.EAST, eraseButton, 1 ,SpringLayout.WEST, submitButton);
        layout.putConstraint(SpringLayout.NORTH, eraseButton, -eraseButton.getHeight(), SpringLayout.NORTH, buttons[0]);

        add (submitButton);
        add (eraseButton);
    }

    /**
     * States whether the current stage is active.
     * @return returns true if, and only if a legal input has not been submitted yet.
     */
    public boolean isActive() {
        return gameActive;
    }

    /**
     * Sets the state of activity for the current stage.
     * Used only by sub-classes.
     * @param status The new state of activity for the current stage.
     */
    protected void setIsActive(boolean status) {
        gameActive = status;
    }

    /**
     * Inssert the appropriate input, based on which button was presed, into the inputBar.
     * @param input Holds the text of the game button.
     */
    private void writeInput (String input) {
        if (inputBar.contains(input))
            return;
        inputBar += input + " ";
        JLabel label = createJLabel (input);

        if (inputTexts.size() == 0)
            layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, this);
        else
            layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.EAST, inputTexts.get(inputTexts.size()-1));

        layout.putConstraint(SpringLayout.SOUTH, label, -5, SpringLayout.NORTH, buttons[0]);

        inputTexts.add(label);
        add (label);
        revalidate();
        repaint();
    }

    /**
     * Removes that last entered element from the inputBar.
     */
    private void removeInputElement () {
        if (inputTexts.size() == 0)
            return;
        JLabel label = inputTexts.remove(inputTexts.size()-1);
        remove (label);
        inputBar = inputBar.replace(label.getText() + " ", "");
        revalidate();
        repaint();
    }

    /**
     * Automatically checks whether the animal with the passed id has been drawn
     * and if it was, then it will be erased. After that the method draws the animal with the passed
     * id placing it top-left corner at the passed x, y coordinates.
     *
     * @param x Holds the value for the x coordinate of the top-left corner of the image.
     * @param y Holds the value for the x coordinate of the top-left corner of the image.
     * @param id Holds the value for the id number of the animal to be drawn.
     *
     *           Not working atm. (NullPointerException, line 4, 4th parameter, it technically should be null either way so idk).
     */
    protected void drawAnimal (int x, int y, int id) {
        Graphics g = this.getGraphics();
        System.out.println(stock[id].getPicture().getHeight());
        g.drawImage(stock[id].getPicture(), x, y, this);
        this.repaint(x-1, y-1, stock[id].getPicture().getWidth()+1, stock[id].getPicture().getHeight()+1);
    }

    /**
     * Erases the animal with the passed id number.
     *
     * @param id Holds the value for the id number of the animal to be erased.
     */
    protected void eraseAnimal (int id) {

    }

    /**
     * Return the instance of the animal under the passed animal id.
     *
     * @param id The id number of the animal to be returned.
     * @return The animal under the passed id number.
     */
    protected Animal getAnimal (int id) {
        return stock[id];
    }

    /**
     * Returns the input form the input bar.
     *
     * @return The input that is entered into the input bar.
     */
    protected String readInput () {
        return inputBar;
    }

    /**
     * Randomly chooses a background from the resources and draws it on the scene.
     */
    private void generateBackground () {

    }

    /**
     * Creates the @see javax.swing.JLabel the is to be inserted into the inputBar.
     * @param text The text that the JLabel will contain.
     * @return The created JLabel.
     */
    protected JLabel createJLabel (String text) {
        JLabel label = new JLabel (text);
        Color color = new Color (113, 75, 47);

        label.setForeground(color);
        label.setFont(new Font ("Chalkboard SE", 0, 20));
        label.setToolTipText(text);
        label.setBorder(BorderFactory.createLineBorder(color, 3, true));
        return label;
    }

    /**
     * Draws the borderlines for the inputBar.
     * @param g Graphics used by the current Component.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(0, 580, 1280, 200);
        g.fillRect(0, 535, 1280, 2);

    }

    /**
     * Randomly generates the stock according to the difficulty parameter,
     * numbers them from 0 to n and draws them on the screen accordingly from top to bottom, left to right.
     */
    protected abstract void generateAnimals ();

    /**
     * Checks whether the input from the input bar is legal and return true if so.
     *
     * @return Returns true if, and only if the input from the input bar is legal.
     */
    protected abstract boolean inputLegal();

    /**
     * Creates and inserts the game buttons used for the current stage.
     */
    protected abstract void createGameButtons();

    /**
     * Handles button clicks for this GameStage.
     *
     * @param ae Holds the event that was initiated.
     */
    @Override
    public void actionPerformed (ActionEvent ae) {
        if (ae.getSource().equals(submitButton))
            inputLegal();
        else if (ae.getSource().equals(eraseButton))
            removeInputElement();
        else
            writeInput(ae.getActionCommand());
    }
}