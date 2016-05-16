import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The GameStage class acts as a parent class for ColorChooser, Animal Classifier and
 * Arithmetic classes. It handles all of animal drawing, reading the input from the
 * input bar as well as declaring the means for checking whether that input is legal or not.
 *
 * <b>Global Variables: </b>
 * </p>
 * <b>animals </b> Contains the instance of all the animals in the current barn.
 *
 * @author Inal Gotov, Modified by: Tamir Arnesty
 * @version 1.3, 2016-05-09.
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir:
 *       Inal: 8.5
 */
public abstract class GameStage extends JPanel implements ActionListener {
    protected Animal [] animals;
    protected JTextField inputBar = new JTextField(1);
    protected JButton [] buttons;;
    protected SpringLayout layout = new SpringLayout();
    private final int difficulty;
    private boolean gameActive = true;

    /**
     * Initiates the playing process by filling the array with random
     * generated instance of animal classes and draws them in their proper positions.
     *
     */
    public GameStage (int difficulty) {
        super();
        this.difficulty = difficulty;

        this.setLayout(layout);
        this.setSize(1280, 720);
        this.setBackground(Color.yellow);

        generateAnimals();
        prepareGUI ();

        this.setVisible(true);

//        drawAnimal(0, 0, 0);
    }

    /**
     * This is the second version of this method. It will create and handle the game screen.
     * <b>Local Variables </b>
     * </p>
     * <b>BUTTON_HEIGHT </b> Holds the constant value of the height of each button.
     * </p>
     * <b>BUTTON_WIDTH </b> Holds the constant value of the width of each button.
     */
    private void prepareGUI () {
        generateBackground();
        createButtons();
    }

    public boolean isActive() {
        return gameActive;
    }

    protected void setIsActive (boolean status) {
        gameActive = status;
    }

    /**
     * Will read iniput from the parameters and print it into the inputBar.
     *
     * @param input Holds the values of the button pressed.
     */
    private void writeInput (String input) {
        String tempHolder = inputBar.getText();
        inputBar.setText(tempHolder + " " + input);
        inputBar.repaint();
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
        System.out.println(animals[id].getPicture().getHeight());
        g.drawImage(animals[id].getPicture(), x, y, this);
        this.repaint(x-1, y-1, animals[id].getPicture().getWidth()+1, animals [id].getPicture().getHeight()+1);
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
        return animals [id];
    }

    /**
     * Returns the input form the input bar.
     *
     * @return The input that is entered into the input bar.
     */
    protected String readInput () {
        return inputBar.getText();
    }

    /**
     * Randomly chooses a background from the resources and draws it on the scene.
     */
    private void generateBackground () {

    }

    /**
     * Randomly generates the animals according to the difficulty parameter,
     * numbers them from 0 to n and draws them on the screen accordingly from top to bottom, left to right.
     */
    protected abstract void generateAnimals ();

    /**
     * Checks whether the input from the input bar is legal and return true if so.
     *
     * @return Returns true if, and only if the input from the input bar is legal.
     */
    protected abstract boolean inputLegal();

    protected abstract void createButtons ();

    /**
     * Handles button clicks for this JPanel
     *
     * @param ae Holds the value of the button that was clicked.
     */
    @Override
    public void actionPerformed (ActionEvent ae) {
        writeInput(ae.getActionCommand());
    }
}

class ColorChooser extends GameStage {

    /**
     * Initiates the playing process by filling the array with random
     * generated instance of animal classes and draws them in their proper positions.
     *
     * @param difficulty
     */
    public ColorChooser(int difficulty) {
        super(difficulty);
    }

    @Override
    protected void generateAnimals() {

    }

    @Override
    protected boolean inputLegal() {
        return false;
    }

    @Override
    protected void createButtons() {
        buttons = new JButton[4];
        Dimension size = new Dimension(320, 140);
        ImageIcon [] icons = {new ImageIcon ("src/pictures/Button-Icon/Icon-Black.png"), new ImageIcon ("src/pictures/Button-Icon/Icon-Gray.png"),
                              new ImageIcon ("src/pictures/Button-Icon/Icon-Brown.png"), new ImageIcon ("src/pictures/Button-Icon/Icon-White.png")};


        for (int h = 0; h < 4; h ++) {
            buttons[h] = new JButton (icons[h]);
            buttons[h].addActionListener(this);
            buttons[h].setPreferredSize(size);
        }

        layout.putConstraint(SpringLayout.WEST, buttons [0], 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, buttons [0], this.getHeight() - buttons[0].getHeight(), SpringLayout.NORTH, this);
        add (buttons[0]);

        for (int h = 1; h < 4; h ++) {
            layout.putConstraint(SpringLayout.WEST, buttons [h], 0, SpringLayout.EAST, buttons [h-1]);
            layout.putConstraint(SpringLayout.NORTH, buttons [h], this.getHeight() - buttons[0].getHeight(), SpringLayout.NORTH, this);
            add (buttons [h]);
        }
    }
}

class AnimalClassifier extends GameStage {

    /**
     * Initiates the playing process by filling the array with random
     * generated instance of animal classes and draws them in their proper positions.
     *
     * @param difficulty
     */
    public AnimalClassifier(int difficulty) {
        super(difficulty);
    }

    @Override
    protected void generateAnimals() {

    }

    @Override
    protected boolean inputLegal() {
        return false;
    }

    @Override
    protected void createButtons() {

    }
}

class Arithmetics extends GameStage {

    /**
     * Initiates the playing process by filling the array with random
     * generated instance of animal classes and draws them in their proper positions.
     *
     * @param difficulty
     */
    public Arithmetics(int difficulty) {
        super(difficulty);
    }

    @Override
    protected void generateAnimals() {

    }

    @Override
    protected boolean inputLegal() {
        return false;
    }

    @Override
    protected void createButtons() {

    }
}

//            buttons[h].setBackground(colors[h]);
//            buttons[h].setOpaque(true);
//            buttons[h].setBorderPainted(false);