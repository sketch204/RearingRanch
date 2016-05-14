import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * The GameStage class acts as a parent class for ColorChooser, Animal Classifier and
 * Arithmetic classes. It handles all of animal drawing, reading the input from the
 * input bar as well as declaring the means for checking whether that input is legal or not.
 *
 * <b>Global Variables: </b>
 * </p>
 * <b>animals </b> Contains the instance of all the animals in the current barn.
 *
 * Created by sketch204 on 2016-05-09.
 *
 * Things i should probably think about in the future:
 * This class should probably handle all of the Game UI.
 */
public abstract class GameStage extends JPanel implements ActionListener {
    protected Animal [] animals;
    protected JTextField inputBar = new JTextField(1);
    protected JButton [] buttons = {new JButton (), new JButton (), new JButton (), new JButton ()};;
    private SpringLayout layout = new SpringLayout();
    private final int difficulty;

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
     * This is the first version of this method. It will create and handle the game screen.
     * <b>Local Variables </b>
     * </p>
     * <b>BUTTON_HEIGHT </b> Holds the constant value of the height of each button.
     * </p>
     * <b>BUTTON_WIDTH </b> Holds the constant value of the width of each button.
     */
    private void prepareGUI2 () {
        final int BUTTON_HEIGHT = 140, BUTTON_WIDTH = 320;

        Color [] colors = {Color.BLACK, Color.GRAY, new Color (196, 152, 65), Color.WHITE};
        String [] labels = {"Black", "Gray", "Brown", "White"};

        generateBackground();

        for (int h = 0; h < 4; h ++) {
            buttons[h] = new JButton(labels[h]);
            if (h != 3)
                buttons[h].setForeground(Color.WHITE);
            buttons[h].setBackground(colors[h]);
            buttons[h].setOpaque(true);
            buttons[h].setBorderPainted(false);
            buttons[h].addActionListener(this);
            buttons[h].setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        }

        layout.putConstraint(SpringLayout.WEST, buttons [0], 100, SpringLayout.WEST, this.getTopLevelAncestor());
//        layout.putConstraint(SpringLayout.NORTH, buttons [0], panel.getHeight() - buttons[0].getHeight(), SpringLayout.NORTH, this.getTopLevelAncestor());


        this.add(buttons [0]);

//        buttons [0].repaint();
//        buttons[0].revalidate();

        for (int h = 1; h < 4; h ++) {
            layout.putConstraint(SpringLayout.WEST, buttons [h], 1, SpringLayout.EAST, buttons [h-1]);
//            layout.putConstraint(SpringLayout.SOUTH, buttons [h], 0, SpringLayout.SOUTH, panel);
            add (buttons [h]);
        }
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
        final int BUTTON_HEIGHT = 140, BUTTON_WIDTH = 320;
        Color [] colors = {Color.BLACK, Color.GRAY, new Color (196, 152, 65), Color.WHITE};
        ImageIcon [] icons = {new ImageIcon ("src/pictures/Button-Icon/Icon-Black.png"), new ImageIcon ("src/pictures/Button-Icon/Icon-Gray.png"),
                              new ImageIcon ("src/pictures/Button-Icon/Icon-Brown.png"), new ImageIcon ("src/pictures/Button-Icon/Icon-Brown.png")};

        generateBackground();

        for (int h = 0; h < 4; h ++) {
            buttons[h].addActionListener(this);
            buttons[h].setIcon(new ImageIcon ("src/pictures/Button-Icon/Icon-Brown.png"));
//            buttons[h].setBackground(colors[h]);
//            buttons[h].setOpaque(true);
//            buttons[h].setBorderPainted(false);
            buttons[h].setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
            buttons[h].setPreferredSize(new Dimension (BUTTON_WIDTH, BUTTON_HEIGHT));
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

    /**
     * Handles button clicks for this JPanel
     *
     * @param ae Holds the value of the button that was clicked.
     */
    @Override
    public void actionPerformed (ActionEvent ae) {
        if (difficulty == 1) {
            if (ae.getActionCommand().equalsIgnoreCase("black") || ae.getActionCommand().equalsIgnoreCase("gray") || ae.getActionCommand().equalsIgnoreCase("brown") || ae.getActionCommand().equalsIgnoreCase("white"))
                writeInput(ae.getActionCommand());
        } else if (difficulty == 2) {
            if (ae.getActionCommand().equalsIgnoreCase("horse") || ae.getActionCommand().equalsIgnoreCase("chicken") || ae.getActionCommand().equalsIgnoreCase("cow") || ae.getActionCommand().equalsIgnoreCase("sheep") ||
                    ae.getActionCommand().equalsIgnoreCase("goose") || ae.getActionCommand().equalsIgnoreCase("goat"))
                writeInput(ae.getActionCommand());
        } else if (difficulty == 3) {
            if (ae.getActionCommand().equalsIgnoreCase("1") || ae.getActionCommand().equalsIgnoreCase("2") || ae.getActionCommand().equalsIgnoreCase("3") || ae.getActionCommand().equalsIgnoreCase("4") ||
                    ae.getActionCommand().equalsIgnoreCase("5") || ae.getActionCommand().equalsIgnoreCase("6") || ae.getActionCommand().equalsIgnoreCase("7") || ae.getActionCommand().equalsIgnoreCase("8") ||
                    ae.getActionCommand().equalsIgnoreCase("9") || ae.getActionCommand().equalsIgnoreCase("0"))
                writeInput(ae.getActionCommand());
        }
    }
}
