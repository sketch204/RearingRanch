package root.game;

import root.dataclass.Animal;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The GameStage class acts as a parent class for ColorChooser, Animal Classifier and
 * Arithmetics classes. It handles most of the user interface components such as; buttons
 * user input, label, animal drawing etc...
 * <p>
 * It is also responsible for handling all button clicks as well as keeping track of game activity.
 * <p>
 * <b>Global Variables: </b>
 * </p>
 * <b>stock </b> Contains the instance of all the stock in the current barn.
 *
 * @author Inal Gotov, Modified by: Tamir Arnesty
 * @version 1.3, 2016-05-09.
 *          Last Edited: 2016-05-15
 *          Hours since 2016-05-11:
 *          Tamir: -
 *          Inal: 14:15
 */
public abstract class GameStage extends JPanel implements ActionListener {
    /**
     * Holds the file that will act as background through out the whole run of the program.
     */
    private File background;
    /**
     * The @see javax.swing.JButton on the input that is responsible for erasing the last element on the input.
     */
    private JButton eraseButton = new JButton(new ImageIcon("src/pictures/buttons/inputBar/Icon-Erase.png"));
    /**
     * The amount of stables available for the current background.
     */
    private int stablesAvailable = 0;
    /**
     * An arrays of (x,y) positions of each stable on the current background.
     */
    private Point [] stablePositions;
    /**
     * The @see javax.swing.JButton on the input that is responsible for initiating the legality check.
     */
    private JButton submitButton = new JButton(new ImageIcon("src/pictures/buttons/inputBar/Icon-Submit.png"));
    /**
     * The array that holds all the game buttons currently on screen.
     */
    protected JButton[] buttons;
    /**
     * Holds the difficulty of the current stage
     */
    protected int difficulty;
    /**
     * The @see java.util.ArrayList that holds all JLabel currently in the input.
     */
    protected ArrayList<JLabel> input = new ArrayList<JLabel>();
    /**
     * The layout of this panel.
     */
    protected SpringLayout layout = new SpringLayout();
    /**
     * The array that holds all the stock currently on screen.
     * This is the array that is used to check for a legal input.
     */
    protected Animal[] stock;

    /**
     * Creates an instance of a GameStage. Sets up the panel and creates all graphics for the game.
     */
    public GameStage() {
        super();

        this.difficulty = 0;

        this.setLayout(layout);
        this.setSize(1280, 720);
        this.setBackground(new Color(203, 203, 203));

        generateBackground();
        prepareGUI();

        this.setVisible(true);
    }

    /**
     * Creates an instance of a GameStage. Sets up the panel and creates all graphics for the game.
     * @param difficulty Sets the difficulty of this stage.
     */
    public GameStage(int difficulty) {
        super();

        this.difficulty = difficulty;

        setLayout(layout);
        setSize(1280, 720);
        setBackground(new Color(203, 203, 203));

        generateBackground();
        generateAnimals();
        prepareGUI();

        setVisible(true);
    }

    /**
     * The accessor method for amount of available stables.
     * @return the amount of available stables.
     */
    public int getStablesAvailable() {
        return stablesAvailable;
    }

    /**
     * Randomly chooses a background from the resources and draws it on the scene.
     * Also initializes the stablePositions and StablesAvailable variables.
     */
    private void generateBackground() {
        int bGNum = (int)(Math.random() * 4) + 1;
        background = new File("src/pictures/backgrounds/background" + bGNum + ".png");

        switch (bGNum) {
            case 1:
                stablesAvailable = 5;
                stablePositions = new Point [stablesAvailable];
                stablePositions [0] = new Point (309, 475);
                stablePositions [1] = new Point (595, 438);
                stablePositions [2] = new Point (961, 382);
                stablePositions [3] = new Point (677, 520);
                stablePositions [4] = new Point (1131, 531);
                break;
            case 2:
                stablesAvailable = 7;
                stablePositions = new Point [stablesAvailable + 2];
                stablePositions [0] = new Point (338, 406);
                stablePositions [1] = new Point (757, 389);
                stablePositions [2] = new Point (1050, 436);
                stablePositions [3] = new Point (283, 503);
                stablePositions [4] = new Point (939, 523);
                stablePositions [5] = new Point (1279, 478);
                stablePositions [6] = new Point (580, 512);
                stablePositions [7] = new Point (1, 106);
                stablePositions [8] = new Point (864, 85);
                break;
            case 3:
                stablesAvailable = 7;
                stablePositions = new Point [stablesAvailable];
                stablePositions [0] = new Point (462, 318);
                stablePositions [1] = new Point (746, 340);
                stablePositions [2] = new Point (1052, 286);
                stablePositions [3] = new Point (1267, 416);
                stablePositions [4] = new Point (405, 480);
                stablePositions [5] = new Point (789, 525);
                stablePositions [6] = new Point (1043, 470);
                break;
            case 4:
                stablesAvailable = 7;
                stablePositions = new Point [stablesAvailable];
                stablePositions [0] = new Point (431, 237);
                stablePositions [1] = new Point (714, 282);
                stablePositions [2] = new Point (1030, 309);
                stablePositions [3] = new Point (358, 367);
                stablePositions [4] = new Point (606, 450);
                stablePositions [5] = new Point (1279, 503);
                stablePositions [6] = new Point (959, 548);
                break;
        }
    }

    /**
     * Transform the background from a File type to a BufferedImage type.
     * @return the BufferedImage version of the background.
     */
    private BufferedImage generateBG () {
        BufferedImage img = null;
        try {
            img = ImageIO.read(background);
        } catch (IOException e) {}
        return img;
    }

    /**
     * Draws the background for this stage, as well as all the needed game and input buttons.
     * <p>
     * <b>Local Variables </b>
     * </p>
     * <b>x </b> Holds the width of input buttons.
     * </p>
     * <b>y </b> Holds the height of input buttons.
     */
    private void prepareGUI() {
//        generateBackground();
        createGameButtons();

        int x = new ImageIcon("src/pictures/buttons/inputBar/Icon-Submit.png").getIconWidth(),
                y = new ImageIcon("src/pictures/buttons/inputBar/Icon-Submit.png").getIconHeight();

        submitButton.setSize(x, y);
        submitButton.setPreferredSize(new Dimension(x, y));
        submitButton.addActionListener(this);
        submitButton.setBorder(BorderFactory.createLineBorder(new Color(34, 34, 34), 1, true));
//        submitButton.setIgnoreRepaint(true);

        layout.putConstraint(SpringLayout.WEST, submitButton, this.getWidth() - submitButton.getWidth() - 1, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, submitButton, -submitButton.getHeight(), SpringLayout.NORTH, buttons[0]);

        eraseButton.setSize(x, y);
        eraseButton.setPreferredSize(new Dimension(x, y));
        eraseButton.addActionListener(this);
        eraseButton.setBorder(BorderFactory.createLineBorder(new Color(34, 34, 34), 1, true));
//        eraseButton.setIgnoreRepaint(true);

        layout.putConstraint(SpringLayout.EAST, eraseButton, 1, SpringLayout.WEST, submitButton);
        layout.putConstraint(SpringLayout.NORTH, eraseButton, -eraseButton.getHeight(), SpringLayout.NORTH, buttons[0]);

        add(submitButton);
        add(eraseButton);
    }

    /**
     * Removes that last entered element from the input.
     */
    private void removeInputElement() {
        if (input.size() == 0)
            return;
        JLabel label = input.remove(input.size() - 1);
        remove(label);
        revalidate();
        repaint();
    }

    /**
     * Insert the appropriate input, based on which button was presed, into the input.
     *
     * @param input Holds the text of the game button.
     */
    private void writeInput(String input) {
        for (int h = 0; h < this.input.size(); h++)
            if (this.input.get(h).getText().equals(input))
                return;

        JLabel label = createJLabel(input);

        if (this.input.size() == 0)
            layout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, this);
        else
            layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.EAST, this.input.get(this.input.size() - 1));

        layout.putConstraint(SpringLayout.SOUTH, label, -145, SpringLayout.SOUTH, this);

        this.input.add(label);
        add(label);
        revalidate();
        repaint();
    }

    /**
     * Creates and inserts the game buttons used for the current stage.
     */
    protected void createGameButtons() {
        int i1, i2;
        ImageIcon[] icons = generateButtons();
        Dimension size = new Dimension(icons[0].getIconWidth(), icons[0].getIconHeight());

        i1 = icons[0].getDescription().lastIndexOf('-') + 1;
        for (int h = 0; h < buttons.length; h++) {
            buttons[h] = new JButton(icons[h]);
            buttons[h].addActionListener(this);
            buttons[h].setSize(size);
            buttons[h].setPreferredSize(size);
            i2 = icons[h].getDescription().lastIndexOf('.');
            buttons[h].setText(icons[h].getDescription().substring(i1, i2));
            buttons[h].setBorder(BorderFactory.createEmptyBorder());
            buttons[h].setContentAreaFilled(true);
        }

        layout.putConstraint(SpringLayout.WEST, buttons[0], 2, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.SOUTH, buttons[0], 0, SpringLayout.SOUTH, this);
        add(buttons[0]);

        for (int h = 1; h < buttons.length; h++) {
            layout.putConstraint(SpringLayout.WEST, buttons[h], 0, SpringLayout.EAST, buttons[h - 1]);
            layout.putConstraint(SpringLayout.SOUTH, buttons[h], 0, SpringLayout.SOUTH, this);
            add(buttons[h]);
        }
    }

    /**
     * Creates the @see javax.swing.JLabel the is to be inserted into the input.
     *
     * @param text The text that the JLabel will contain.
     * @return The created JLabel.
     */
    protected JLabel createJLabel(String text) {
        JLabel label = new JLabel(text);
        Color color = new Color(215, 215, 215);

        label.setForeground(color);
        label.setFont(new Font("Chalkboard SE", 0, 20));
        label.setToolTipText(text);
        label.setBorder(BorderFactory.createLineBorder(color, 3, true));
        return label;
    }

    /**
     * Return the first available position for the given axis.
     * @return Return the first available position for the given axis.
     * @throws IllegalArgumentException Thrown whenever the argument is greater than 1 or less than 0.
     */
    protected Point[] getPosition(int amount) {
        if (stablesAvailable < 1) return null;

        Point [] points = new Point[amount];
        int index = -1, lastIndex = index;

        if (stablePositions.length == 9) {
            int starter = 0;
            if (amount == 4) { // 1 Random from 1-3, if 1 || 3, add stall position
                points = new Point [amount+1];
                starter = 1;
                index = (int) ((Math.random() * 3) + 1);
                points[0] = new Point (stablePositions[index]);
                points[points.length - 1] = (index == 1) ? new Point (stablePositions [stablePositions.length - 2]) : new Point (stablePositions [stablePositions.length - 1]);
            }
            for (int h = starter; h < amount; h ++) { // Random > 3
                do {
                    index = (int) ((Math.random() * (((stablePositions.length - 3) - 3) + 1)) + 3);
                } while (index == lastIndex);
                lastIndex = index;
                points[h] = new Point (stablePositions[index]);
            }
        } else {
            int min = 0, max = stablePositions.length - 1, amountTop = 1, amountBottom = 0;

            if (amount == 2 || amount == 3) {
                amountBottom = amount - 1; // 1 || 2
                max = 2;
            } else if (amount == 4) {
                amountTop = 2;
                amountBottom = 2;
                max = 2;
            }

            for (int h = 0; h < amountTop; h++) {
                do {
                    index = (int) ((Math.random() * ((max - min) + 1)) + min);
                } while (index == lastIndex);
                lastIndex = index;
                points[h] = new Point (stablePositions [index]);
            }
            lastIndex = -1;
            for (int h = amountTop; h < amountTop + amountBottom; h++) {
                do {
                    index = (int) ((Math.random() * (((stablePositions.length - 1) - 3) + 1)) + 3);
                } while (index == lastIndex);
                lastIndex = index;
                points[h] = new Point (stablePositions [index]);
            }
        }
        return points;
    }

    /**
     * Draws the animals on screen.
     *
     * @return
     */
    protected abstract void createAnimals(Graphics g);

    /**
     * Randomly generates the stock according to the difficulty parameter,
     * numbers them from 0 to n and draws them on the screen accordingly from top to bottom, left to right.
     */
    protected abstract void generateAnimals();

    /**
     * Generates an array of Icons for the buttons to take, also sets the size of the buttons array.
     * @return An array of ImageIcon indexed respecively to the buttons array.
     */
    protected abstract ImageIcon[] generateButtons();

    /**
     * Checks whether the input from the input bar is legal and return true if so.
     *
     * @return Returns true if, and only if the input from the input bar is legal.
     */
    protected abstract void inputLegal();

    /**
     * Handles button clicks for this GameStage.
     *
     * @param ae Holds the event that was initiated.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(submitButton))
            inputLegal();
        else if (ae.getSource().equals(eraseButton))
            removeInputElement();
        else
            writeInput(ae.getActionCommand());
    }

    /**
     * Draws the borderlines for the input.
     *
     * @param g Graphics used by the current Component.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color (34, 34, 34));
        g.drawImage(generateBG(), 0, 0, null);
        createAnimals(g);
        g.fillRect(0, 580, 1280, 200);
        g.fillRect(0, 535, 1280, 47);
    }
}