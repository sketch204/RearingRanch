package root.game;

import root.DifficultyChooser;
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
 * user input, labels, drawing of the animals etc...
 * <b>Note*</b>
 * Buttons that are used for input (excluding all input bar buttons and buttons such as pausing or quiting) are referred to as game buttons.
 * A position on screen that an animal can be drawn on is referred to as a stable.
 * @author Inal Gotov, Modified by: Tamir Arnesty
 * @version 1.3, 2016-05-09.
 *          Last Edited: 2016-05-15
 *          Hours since 2016-05-11:
 *          Tamir: -
 *          Inal: 16:15
 */
public abstract class GameStage extends JPanel implements ActionListener {
    /**
     * Holds the file that will act as background through out the whole run of the current stage.
     */
    private File background;
    /**
     * The amount of stables available for the current background.
     */
    private int stablesAvailable = 0;
    /**
     * An arrays of (x,y) positions of each stable on the current background.
     */
    private Point [] stablePositions;
    /**
     * Contains the other non-game buttons such as submit and pause buttons.
     */
    private JButton [] actionButtons = {new JButton(new ImageIcon("src/pictures/buttons/stage0/Icon-Submit.png")),
                                        new JButton(new ImageIcon("src/pictures/buttons/stage0/Icon-Erase.png")),
                                        new JButton(new ImageIcon("src/pictures/buttons/stage0/Icon-Pause.png")),
                                        new JButton(new ImageIcon("src/pictures/buttons/stage0/Icon-Help.png"))};
    private static JLabel objectiveLabel;

    private boolean objectiveIsShown = false;
    /**
     * The array that holds all the game buttons currently on screen.
     */
    protected JButton[] buttons;
    /**
     * Holds the difficulty of the current stage
     */
    protected int difficulty;
    /**
     * The ArrayList that holds all JLabels currently on the input bar.
     */
    protected ArrayList<JLabel> input = new ArrayList<JLabel>();
    /**
     * The layout instance of this panel.
     */
    protected SpringLayout layout = new SpringLayout();
    /**
     * The array that holds all the animals currently on screen.
     */
    protected Animal[] stock;
    /**
     * The message that the user gets, when pressing the help button.
     */
    protected String gameObjective;

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
     * Generates the animals based on difficulty.
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
     * </p>
     * <b>Local Variables </b>
     * <br> <b>bGNum </b> Holds the number of the background that will be drawn.
     */
    private void generateBackground() {
        int bGNum = (int)(Math.random() * 4) + 1;
        background = new File("src/pictures/backgrounds/background" + bGNum + ".png");

        switch (bGNum) {
            case 1:
                stablesAvailable = 6;
                stablePositions = new Point [stablesAvailable];
                stablePositions [0] = new Point (309, 475);
                stablePositions [1] = new Point (595, 438);
                stablePositions [2] = new Point (961, 382);
                stablePositions [3] = new Point (677, 520);
                stablePositions [4] = new Point (1004, 535);
                stablePositions [5] = new Point (1279, 521);
                break;
            case 2:
                stablesAvailable = 7;
                stablePositions = new Point [stablesAvailable + 2];
                stablePositions [0] = new Point (348, 406);
                stablePositions [1] = new Point (757, 389);
                stablePositions [2] = new Point (1000, 436);
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
     * @return A version of the background in a BufferedImage format.
     * </p>
     * <b>Local Variables </b>
     * <br> <b>img </b> A temporary holder for the BufferedImage version of the background.
     */
    private BufferedImage castBG() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(background);
        } catch (IOException e) {}
        return img;
    }

    /**
     * Draws the background for this stage, as well as all the needed buttons and other graphics.
     * </p>
     * <b>Local Variables </b>
     * <br> <b>x </b> Holds the width of input buttons.
     * <br> <b>y </b> Holds the height of input buttons.
     */
    private void prepareGUI() {
        createGameButtons();

        Dimension [] size = {new Dimension(new ImageIcon("src/pictures/buttons/stage0/Icon-Submit.png").getIconWidth(), new ImageIcon("src/pictures/buttons/stage0/Icon-Submit.png").getIconHeight()),
                             new Dimension(new ImageIcon("src/pictures/buttons/stage0/Icon-Pause.png").getIconWidth(), new ImageIcon("src/pictures/buttons/stage0/Icon-Pause.png").getIconHeight())};

        for (int h = 0; h < actionButtons.length; h ++) {
            actionButtons[h].setSize(size[h/2]);
            actionButtons[h].setPreferredSize(size[h/2]);
            actionButtons[h].addActionListener(this);
            if (h <= 0)
                actionButtons[h].setBorder(BorderFactory.createLineBorder(new Color(34, 34, 34), 1, true));
            else
                actionButtons[h].setBorder(BorderFactory.createEmptyBorder());
        }

        layout.putConstraint(SpringLayout.WEST, actionButtons[0], this.getWidth() - actionButtons[0].getWidth() - 1, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, actionButtons[0], -(int)size[0].getHeight(), SpringLayout.NORTH, buttons[0]);

        layout.putConstraint(SpringLayout.EAST, actionButtons[1], 1, SpringLayout.WEST, actionButtons[0]);
        layout.putConstraint(SpringLayout.NORTH, actionButtons[1], -(int)size[0].getHeight(), SpringLayout.NORTH, buttons[0]);

        layout.putConstraint(SpringLayout.WEST, actionButtons[2], 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, actionButtons[2], 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, actionButtons[3], 10, SpringLayout.EAST, actionButtons[2]);
        layout.putConstraint(SpringLayout.NORTH, actionButtons[3], 10, SpringLayout.NORTH, this);

        for (int h = 0; h < actionButtons.length; h++)
            add(actionButtons[h]);

        objectiveLabel = new JLabel(gameObjective);
        objectiveLabel.setOpaque(true);
        objectiveLabel.setFont(new Font ("Chalkboard", 0, 25));
        objectiveLabel.setForeground(new Color (34, 34, 34));
        objectiveLabel.setBackground(new Color (213, 194, 158, 220));

        layout.putConstraint(SpringLayout.WEST, objectiveLabel, 10, SpringLayout.EAST, actionButtons[3]);
        layout.putConstraint(SpringLayout.NORTH, objectiveLabel, 10, SpringLayout.NORTH, this);
        add(objectiveLabel);
        displayGameObjective();
    }

    private void displayGameObjective () {
        if (objectiveIsShown)
            objectiveLabel.setVisible(false);
        else
            objectiveLabel.setVisible(true);
        objectiveIsShown = !objectiveIsShown;
        revalidate();
        repaint();
    }

    /**
     * Removes that last entered element from the input bar.
     * <p>
     * <b>Local Variables </b>
     * <br> <b>label </b> A temporary holder for the JLabel that should be removed.
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
     * Removes that last entered element from the input bar.
     * <p>
     * <b>Local Variables </b>
     * <br> <b>label </b> A temporary holder for the JLabel that should be removed.
     */
    private void removeInputElement(String elementText) {
        JLabel label = null;
        for (int h = 0; h < input.size(); h ++)
            if (input.get(h).getText().equals(elementText))
                label = input.remove(h);
        if (label == null)
            return;
        remove(label);
        revalidate();
        repaint();
    }

    private void pauseGame() {
//        DifficultyChooser.timer.interrupt();
    }

    /**
     * Insert the appropriate input into the input bar, based on the game button that was pressed.
     * @param input Holds the text of the game button that will be used for the JLabel.
     */
    private void writeInput(String input) {
        if (!canWriteInput(input))
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
     * Used for checking whether a JLabel with the given String parameter already exists on the input bar.
     * @param input The text that should be searched for, among the input JLabels.
     * @return Returns true if, and only if, a JLabel with the given String parameter does not exist on the input bar.
     */
    protected boolean canWriteInput (String input) {
        for (int h = 0; h < this.input.size(); h++)
            if (this.input.get(h).getText().equals(input))
                return false;
        return true;
    }

    /**
     * Creates and inserts the game buttons used for the current stage.
     * It has a protected access level because it is overriden in Arithmetics.
     * <p>
     * <b>Local Variables </b>
     * <br> <b>i1 </b> A starting index holder used for creating a substring from the icons' paths.
     * <br> <b>i2 </b> A ending index holder used for creating a substring from the icons' paths.
     * <br> <b>icons </b> An array of icons that will be placed on top of the game buttons.
     * <br> <b>size </b> The size of each game button.
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
     * Creates the JLabel the is to be inserted into the input bar.
     * <p>
     * <b>Local Variables </b>
     * <br> <b>label </b> The label that will be printed on the input bar.
     * <br> <b>color </b> The color that the label will have.
     * @param text The text that the JLabel will have on it.
     * @return The created JLabel.
     */
    private JLabel createJLabel(String text) {
        JLabel label = new JLabel(text);
        Color color = new Color(215, 215, 215);

        label.setForeground(color);
        label.setFont(new Font("Chalkboard SE", 0, 20));
        label.setToolTipText(text);
        label.setBorder(BorderFactory.createLineBorder(color, 3, true));
        return label;
    }

    /**
     * Returns an array of Points containing the stable positions for the required amount of animals.
     * </p>
     * <br> <b>Local Variables </b>
     * <br> <b>points </b> An ArrayList that will contain the stable positions.
     * <br> <b>positionIndex </b> Holds the index of a randomly generated position.
     * <br> <b>starter </b> Holds the position from which to start filling the 'points' ArrayList. Defaults to 0, and is only changed if amount is greater than 3.
     * <br> <b>max </b> Holds the maximum range number when choosing a random position for the screen. Defaults to 0, and is changed if amount is greater than 1.
     * <br> <b>amountTop </b> Holds the number of positions that should be generated among the top rows.
     * <br> <b>amountBottom </b> Holds the number of position that should be generated amond the top rows.
     * </p>
     * @return The array of Points containing the stable positions.
     */
    protected Point[] getPosition(int amount) {
        if (stablesAvailable < 1) return null;
        ArrayList <Point> points = new ArrayList<Point>();
        int positionIndex = -1;
        // Initialize the ArrayList, for 'set' to work
        for (int h = 0; h < amount; h ++)
            points.add (null);
        if (stablePositions.length == 9) {
            // Will have the starter index for the returning ArrayList.
            // If request > 3 then allocate for the amount of top spaces there are
            int starter = (amount > 3) ? amount - 3 : 0;
            // Contains the returning coordinates
            points = new ArrayList<Point>(amount + starter);
            // Initialize ArrayList, for 'set' to work
            for (int h = 0; h < amount + starter; h ++)
                points.add (null);
            // Randomizes points on screen
            for (int h = starter; h < amount; h ++) { // Random > 2
                do { // Generate position                                     - 3 here to subtract the 2 last points (they're stall postions).
                    positionIndex = (int) ((Math.random() * (((stablePositions.length - 3) - 3) + 1)) + 3); // Min = 3 | Max = 6
                } while (points.contains(new Point (stablePositions[positionIndex]))); // Repetition checker
                points.set (h, new Point (stablePositions[positionIndex]));
            }
            // Generate for the allocated spaces, If none exist, then will not run.
            for (int h = 0; h < amount - 3; h++) {
                do { // Generate position
                    positionIndex = (int) (Math.random() * 3); // Min = 0 | Max = 2
                } while (points.contains(new Point (stablePositions[positionIndex])));
                points.set (h, new Point(stablePositions[positionIndex]));
                if (positionIndex == 0)
                    points.set (h + amount, new Point(stablePositions[stablePositions.length - 2]));  // Allocate left stable
                else if (positionIndex == 1)
                    points.set (h + amount, new Point (-1,-1));  // Center position, no stable needed.
                else if (positionIndex == 2)
                    points.set (h + amount, new Point(stablePositions[stablePositions.length - 1]));  // Allocate right stable
            }
        } else {  // If not second background
            int max = stablePositions.length - 1, amountTop = 1, amountBottom = 0;
            // Default run (amount == 1): Random position on screen.
            if (amount > 1) // Case (amount): 2, 3, 4, 5, 6
                max = 2;
            if (amount == 2 || amount == 3) { // Case (amount): 2, 3
                amountBottom = amount - 1; // 1 || 2
            } else if (amount == 4 || amount == 5) { // Case (amount): 4, 5
                amountTop = 2;
                amountBottom = 2;
            } if (amount > 4) { // Case (amount): 5, 6
                amountBottom = 3;
            } if (amount == 6) { // Case (amount): 6
                amountTop = 3;
            }
            // Generate for requested amount of positions from top row.
            for (int h = 0; h < amountTop; h++) {
                do {
                    positionIndex = (int) (Math.random() * (max + 1));
                } while (points.contains(new Point (stablePositions[positionIndex])));
                points.set (h, new Point (stablePositions [positionIndex]));
            }
            // Generate for requested amount of positions from bottom rows.
            for (int h = amountTop; h < amountTop + amountBottom; h++) {
                do {
                    positionIndex = (int) ((Math.random() * (((stablePositions.length - 1) - 3) + 1)) + 3);
                } while (points.contains(new Point (stablePositions[positionIndex])));
                points.set (h, new Point (stablePositions [positionIndex]));
            }
        }
        return points.toArray(new Point [points.size()]);
    }

    /**
     * Draws the animals on screen. If required, will also draw the stalls in front of the animals.
     * The animals are drawn from left to right, top to bottom.
     * @return
     */
    protected abstract void createAnimals(Graphics g);

    /**
     * Fills the stock with randomly generated animals, according to the difficulty parameter,
     */
    protected abstract void generateAnimals();

    /**
     * Generates an array of Icons for the buttons to take. Also sets the size of the buttons array.
     * @return An array of ImageIcon indexed respectively to the buttons array.
     */
    protected abstract ImageIcon[] generateButtons();

    /**
     * Checks whether the input from the input bar is legal and return true if so.
     */
    protected abstract void inputLegal();

    /**
     * Handles all button clicks for this GameStage.
     * @param ae Holds the event that was initiated.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(actionButtons[0]))
            inputLegal();
        else if (ae.getSource().equals(actionButtons[1]))
            removeInputElement();
        else if (ae.getSource().equals(actionButtons[2]))
            pauseGame();
        else if (ae.getSource().equals(actionButtons[3]))
            displayGameObjective();
        else {
            if (!canWriteInput(ae.getActionCommand()))
                removeInputElement(ae.getActionCommand());
            else
                writeInput(ae.getActionCommand());
        }
    }

    /**
     * Draws the borderlines for the input bar as well as the background for the game buttons.
     * Also draws the animals.
     * @param g Graphics used by the current Component.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color (34, 34, 34));
        g.drawImage(castBG(), 0, 0, null);
        createAnimals(g);
        g.fillRect(0, 580, 1280, 200);
        g.fillRect(0, 535, 1280, 47);
    }
}