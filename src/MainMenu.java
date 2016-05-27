package root;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
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
 *       Tamir: 8:00
 *       Inal: 2:00
 */
public class MainMenu extends JPanel implements ActionListener, KeyListener{
    /** <br> <b> buttonSize </b> Instance of Dimension with the width and size of the Play Game Button. This Dimension is used to
     * set the default size of all the buttons on the screen.*/
    public static Dimension buttonSize = new Dimension((new ImageIcon("src/pictures/buttons/mainMenu/PlayButton.png")).getIconWidth(),
                                                       (new ImageIcon("src/pictures/buttons/mainMenu/PlayButton.png")).getIconHeight());
    /**
     * Holds the file that will act as background through out the whole run of the program.
     */
    static File background = new File("src/pictures/backgrounds/background" + ((int) (Math.random() * 4) + 1) + ".png");

//    static Font buttonFont = new Font ("OCR A Std", Font.PLAIN, 14);

//    /** <br> <b> playGame </b> Instance of JButton class with the Play Button image. The button is used to start the game. */
//    private JButton playGame = new JButton (new ImageIcon ("src/pictures/Button-Icon/Main Menu/PlayButton.png"));
//    /** <br> <b> instructions </b> Instance of JButton class with the value 'Instructions'. The button is used to view the instructions for the game. */
//    private JButton instructions = new JButton(new ImageIcon("src/pictures/Button-Icon/Main Menu/InstructionsButton.png"));
//    /** <br> <b> highscores </b> Instance of JButton class with the value 'High Scores'. The button is used to view the high scores for the game. */
//    private JButton highscores = new JButton (new ImageIcon("src/pictures/Button-Icon/Main Menu/HighscoresButton.png"));
//    /** <br> <b> quit </b> Instance of JButton class with the value 'Quit'. The button is used to exit the game. */
//    private JButton quit = new JButton (new ImageIcon ("src/pictures/Button-Icon/Main Menu/QuitButton.png"));
    /** <br> <b> mainMenu </b> Instance of JButton class used to return the user to Main Menu.*/
    private JButton mainMenu = new JButton("Return to Main Menu");
    /** <br> <b> goBack </b> Instance of JLabel class that displays prompt message to return to main menu once the user
     * finishes reading the instructions above.*/
    private JLabel goBack = new JLabel ("<html> Press the button to return to Main Menu.");
    /** <br> <b> layout </b> Instance of LayoutManager SpringLayout is used to organize GUI Components onto the screen. */
    private SpringLayout layout = new SpringLayout();

    public int ii = 0;
    public int index = 0;
    private JButton [] mainChoices = {new JButton (new ImageIcon ("src/pictures/buttons/mainMenu/PlayButton.png")),
            new JButton(new ImageIcon("src/pictures/buttons/mainMenu/InstructionsButton.png")),
            new JButton(new ImageIcon("src/pictures/buttons/mainMenu/HighscoresButton.png")),
            new JButton(new ImageIcon ("src/pictures/buttons/mainMenu/QuitButton.png"))};

    /** The MainMenu constructor sets the layout manager to SpringLayout, sets the size to 1280x720, and references prepareGUI method. */
    MainMenu() {
        super();
        setLayout(layout);
        setSize(1280, 720);
        prepareGUI();
    }

    static KeyListener enter = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent ke) {
            if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
                ((JButton) ke.getComponent()).doClick();
            }
        }
    };

    /** prepareGUI method adds the play game, instructions, highscores and quit buttons to the panel, as well as adds a
     * brief intro label.
     */
    private void prepareGUI () {
        /** <br> <b> intro </b> Instance of JLabel class that stores an introduction message to the user. */
        JLabel intro = new JLabel("<html> Welcome to Rearing Ranch! Press any of the following buttons to continue." +
                "<br>Press Play Game to start! If you need help, press Instructions. Press the Highscores button to view previous highscores!" +
                "<br>If you want to leave, press Quit! </html>");

        for (ii = 0; ii < mainChoices.length; ii++) {
//            final int index = ii;
            mainChoices[ii].addActionListener(this);
            mainChoices[ii].addKeyListener(enter);
            mainChoices[ii].addKeyListener(this);
//            mainChoices[i].setContentAreaFilled(true);
//            mainChoices[i].setBorder((BorderFactory.createEmptyBorder()));
//            mainChoices[ii].addKeyListener(new KeyAdapter() {
//                @Override
//                public void keyPressed(KeyEvent e) {
//                    switch (e.getKeyCode()) {
//                        case KeyEvent.VK_UP:
//                            if (index > 0)
//                                mainChoices[index - 1].requestFocus();
//                            else
//                                mainChoices[mainChoices.length-1].requestFocus();
//                            break;
//                        case KeyEvent.VK_DOWN:
//                            if (index  == mainChoices.length-1)
//                                mainChoices[0].requestFocus();
//                            else
//                                mainChoices[index+1].requestFocus();
//                            break;
//                        default:
//                            break;
//                    }}});
        }

        mainChoices[0].requestFocus();

        layout.putConstraint(SpringLayout.NORTH,  mainChoices[0], 200, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER,  mainChoices[0], 0, SpringLayout.HORIZONTAL_CENTER, this);
        add (mainChoices[0]);


        for (int i = 1; i < mainChoices.length; i++) {
            layout.putConstraint(SpringLayout.NORTH, mainChoices[i], 10, SpringLayout.SOUTH, mainChoices[i-1]);
            layout.putConstraint(SpringLayout.WEST, mainChoices[i], 0, SpringLayout.WEST, mainChoices[i-1]);
            add(mainChoices[i]);
        }
//        layout.putConstraint(SpringLayout.NORTH, instructions, 10, SpringLayout.SOUTH, playGame);
//        layout.putConstraint(SpringLayout.WEST, instructions, 0, SpringLayout.WEST, playGame);
//        add(instructions);
//
//        layout.putConstraint(SpringLayout.NORTH, highscores, 10, SpringLayout.SOUTH, instructions);
//        layout.putConstraint(SpringLayout.WEST, highscores, 0, SpringLayout.WEST, instructions);
//        add(highscores);
//
//        layout.putConstraint(SpringLayout.NORTH, quit, 10, SpringLayout.SOUTH, highscores);
//        layout.putConstraint(SpringLayout.WEST, quit, 0, SpringLayout.WEST, highscores);
//        add(quit);

        layout.putConstraint(SpringLayout.NORTH, intro, 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, intro, 100, SpringLayout.WEST, this);
        intro.setFont(new Font ("OCR A Std", Font.PLAIN, 13));
        add(intro);
        setVisible(true);
        revalidate();
        repaint();
    }


    public JButton getMainMenu() {
        return mainMenu;
    }

    public JLabel getGoBack () {
        return goBack;
    }

    private BufferedImage generateBG () {
        BufferedImage img = null;
        try {
            img = ImageIO.read(background);
        } catch (IOException e) {}
        return img;
    }

    private BufferedImage getImage (String name) {
        try {
            BufferedImage pic = ImageIO.read (new File("src/pictures/" + name + ".png"));
            return pic;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No such file exists! Sorry mate.", "ErrorMsg", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }
    public void paintComponent (Graphics g) {
        if (g != null) {
            int yCoord = 215 + 105*index;
            g.drawImage(generateBG(), 0, 0, null);
            g.drawImage(getImage("Goose-Brown small"), 400, yCoord, null); // interval is 105 pixels on y axis
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(mainMenu))
            RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().m, "Rearing Ranch");
        else if (e.getSource().equals(mainChoices[0])) {
            RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().d, "Difficulty Chooser");
        } else if (e.getSource().equals((mainChoices[1])))
            RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().i, "Instructions");
        else if (e.getSource().equals(mainChoices[2])) {
            RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().h, "Highscores");
            // Temporary
            RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().m, "Rearing Ranch");
        } else if (e.getSource().equals(mainChoices[3])) {
            //RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().g, "Good Bye!");
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
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
            default:
                break;
        }
        paintComponent(this.getGraphics());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

