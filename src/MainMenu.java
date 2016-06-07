package root;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.text.DefaultEditorKit;

/**
 * The MainMenu class creates the main control panel for the program, otherwise called the main menu. In here, users may choose to play the game, view instructions
 * or high scores, and also quit the game. Handling is done via GUI components such as buttons.
 *
 *
 * @author Tamir Arnesty
 * @version 2 2016-05-11.
 *
 * Last Edited: 2016-05-30
 * Hours since 2016-05-11:
 *       Tamir: 10:00
 *          - x hours commenting since 10 hours total. still buggy, but to be fixed and thats more hours.
 *       Inal: 2:00
 */
public class MainMenu extends JPanel implements ActionListener, KeyListener {

    /** <b> background </b> Holds the file that will act as background through out the whole run of the program. */
    public static File background = new File("src/pictures/backgrounds/background" + ((int) (Math.random() * 4) + 1) + ".png");
    /** <b> mainMenu </b> Instance of JButton class used to return the user to Main Menu.*/
    private JButton mainMenu = new JButton(new ImageIcon (getImage("buttons/MainMenuButton").getScaledInstance(150, 35, 0)));
    /** <b> goBack </b> Instance of JLabel class that displays prompt message to return to main menu once the user
     * finishes reading the instructions above.*/
    private JLabel goBack = new JLabel ("<html> Press the button to return to Main Menu.");
    /** <b> layout </b> Instance of LayoutManager SpringLayout is used to organize GUI Components onto the screen. */
    private SpringLayout layout = new SpringLayout();
    /** <b> index </b> Integer that stores the index of mainChoices button array. */
    private int index = 0;
    /** <b> mainChoices </b> Array instance of JButton class that stores the menu buttons.
     * <br> In order: Play Game, Instructions, High Scores, Quit. */
    private JButton [] mainChoices = {new JButton (new ImageIcon ("src/pictures/buttons/mainMenu/PlayButton.png")),
            new JButton(new ImageIcon("src/pictures/buttons/mainMenu/InstructionsButton.png")),
            new JButton(new ImageIcon("src/pictures/buttons/mainMenu/HighscoresButton.png")),
            new JButton(new ImageIcon ("src/pictures/buttons/mainMenu/QuitButton.png"))};
    /** <b> enter </b> Instance of KeyListener interface that determines when the enter key is pressed. */
    static KeyListener enter = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent ke) {
            if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
                ((JButton) ke.getComponent()).doClick();
            }
        }
    };

    /** The MainMenu constructor sets the layout manager to SpringLayout, sets the size to 1280x720, and references prepareGUI method. */
    MainMenu() {
        super();
        setLayout(layout);
        setSize(1280, 720);
//        addBindings();
        prepareGUI();
    }

    /** prepareGUI method adds the play game, instructions, highscores and quit buttons to the panel. Window is set visible
     * and calls paintComponent to draw the images.
     *
     * <br> <b> For Loops: </b>
     * <br> 1st: Adds ActionListener, KeyListener, and ToolTip text to each menu button, as well as setting a border for the button.
     * <br> 2nd: Sets the last three buttons onto the screen relative to the first in the button array.
     */
    private void prepareGUI () {
        /** <b> shortcuts </b> String array that stores text for button ToolTip that displays shortcuts for each button. */
        String [] shortcuts = {"Press p to play game.", "Press i to open the instructions.", "Press h to view highscores", "Press q to quit"};

        mainMenu.addActionListener(this);
        mainMenu.addKeyListener(MainMenu.enter);

        for (int ii = 0; ii < mainChoices.length; ii++) {
            mainChoices[ii].addActionListener(this);
            mainChoices[ii].addKeyListener(enter);
            mainChoices[ii].addKeyListener(this);
            mainChoices[ii].setToolTipText(shortcuts[ii]);
            mainChoices[ii].setContentAreaFilled(true);
            mainChoices[ii].setBorder(BorderFactory.createEtchedBorder());
        }

        mainChoices[0].grabFocus();
        layout.putConstraint(SpringLayout.NORTH,  mainChoices[0], 200, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER,  mainChoices[0], 0, SpringLayout.HORIZONTAL_CENTER, this);
        add (mainChoices[0]);

        for (int i = 1; i < mainChoices.length; i++) {
            layout.putConstraint(SpringLayout.NORTH, mainChoices[i], 10, SpringLayout.SOUTH, mainChoices[i-1]);
            layout.putConstraint(SpringLayout.WEST, mainChoices[i], 0, SpringLayout.WEST, mainChoices[i-1]);
            add(mainChoices[i]);
        }

        mainChoices[index].grabFocus();
        paintComponent(this.getGraphics());
        setVisible(true);
        revalidate();
        repaint();
    }

    /** method getMainMenu used to return the "Return to Main Menu" button.
     * @return JButton that returns user to the Main Menu screen.
     */
    public JButton getMainMenuButton() {
        return mainMenu;
    }

    /** method getGoBack used to return the label prompt for the Main Menu return button.
     * @return JLabel that prompts user with return message.
     */
    public JLabel getGoBack () {
        return goBack;
    }

    /** method getBG used to return the background for each window.
     * @return BufferedImage that is set as the window background.
     */
    static BufferedImage getBG() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(background);
        } catch (IOException e) {}
        return img;
    }


    /** method getImage used to return the specified image through the specified file path.
     * @param name the file path of the image requested.
     * @return BufferedImage that is to be displayed.
     */
    static BufferedImage getImage (String name) {
        try {
            BufferedImage pic = ImageIO.read (new File("src/pictures/" + name + ".png"));
            return pic;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No such file exists! Sorry mate.", "ErrorMsg", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }

    /** Overridden method paintComponent paints all images and graphics onto the screen.
     *
     * @param g Graphics used to hold what to paint on the screen.
     */
    @Override
    public void paintComponent (Graphics g) {
        /** <b> yCooord </b> Integer that holds the y coordinate used to draw the goose. */
        int yCoord = 215 + 105*index;

        if (g != null) {
                g.drawImage(getBG(), 0, 0, null);
            g.drawImage(getImage("GameLogo"), 380, 0, null);
            g.drawImage(getImage("Goose-Brown small"), 400, yCoord, null); // interval is 105 pixels on y axis
        }
    }

    /** Overridden method actionPerformed determines which component experiences an action and proceeds with the action
     * respective to each component.
     *
     * @param e ActionEvent used to store the value of the action performed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(mainMenu)) {
            RearingRanchDriver.getWindow().setPanel(MasterFrame.getM(), "Rearing Ranch" );
        } else if (e.getSource().equals(mainChoices[0])) {
            RearingRanchDriver.getWindow().setPanel(MasterFrame.getD(), "Difficulty Chooser");
        } else if (e.getSource().equals((mainChoices[1])))
            RearingRanchDriver.getWindow().setPanel(MasterFrame.getI(), "Instructions");
        else if (e.getSource().equals(mainChoices[2])) {
            RearingRanchDriver.getWindow().setPanel(MasterFrame.getH(), "Highscores");
        } else if (e.getSource().equals(mainChoices[3])) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you wish to quit?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                MasterFrame.initiateGoodbye();
            }
        }
    }

    /** Overridden method keyTyped to fill the requirements of KeyListener.
     * @param e KeyEvent used to store the value of the key typed.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

//    protected void addBindings () {
//        InputMap map = this.getInputMap();
//
//        map.put(KeyStroke.getKeyStroke((char) KeyEvent.VK_UP), mainChoices[mainChoices.length-1].requestFocusInWindow());
//        map.put(KeyStroke.getKeyStroke((char) KeyEvent.VK_DOWN), DefaultEditorKit.forwardAction);
//        revalidate();
//        repaint();
//    }
    /** Overridden method keyPressed invoked when a key is pressed, used to control program flow when a key applicable
     * to this program is pressed.
     * @param e KeyEvent used to store the value of the key pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /** Overridden method keyReleased to fill the requirements of KeyListener.
     * @param e KeyEvent used to store the value of the key released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (index > 0) {
                    mainChoices[index - 1].requestFocus();
                    index--;
                } else {
                    mainChoices[mainChoices.length - 1].requestFocus();
                    index = 3;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (index == mainChoices.length - 1) {
                    mainChoices[0].requestFocus();
                    index = 0;
                } else {
                    mainChoices[index + 1].requestFocus();
                    index++;
                }
                break;
            case KeyEvent.VK_P:
                mainChoices[0].doClick(100);
                break;
            case KeyEvent.VK_I:
                mainChoices[1].doClick(100);
                break;
            case KeyEvent.VK_H:
                mainChoices[2].doClick(100);
                break;
            case KeyEvent.VK_Q:
                mainChoices[3].doClick(100);
                break;
            default:
                break;
        }
        repaint();
        revalidate();
    }

}