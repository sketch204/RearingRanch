package root;

import root.game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * DifficultyChooser class will display the user with three difficulty levels: easy, medium, and hard, that they must choose
 * from. Once they choose, the game will proceed.
 *
 * @author Tamir Arnesty
 * @version 1 2016-05-15
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir: 2:00
 *       Inal: 1:00
 */
public class DifficultyChooser extends JPanel implements ActionListener, KeyListener {
    private int currentStage = -1;

    private int difficulty;
    private Dimension buttonSize = new Dimension((new ImageIcon("src/pictures/buttons/difficultyChooser/EasyButton.png")).getIconWidth(),
                                                 (new ImageIcon("src/pictures/buttons/difficultyChooser/EasyButton.png")).getIconHeight());
    static JButton [] diffButtons = {new JButton (new ImageIcon("src/pictures/buttons/difficultyChooser/EasyButton.png")),
                                      new JButton (new ImageIcon("src/pictures/buttons/difficultyChooser/MediumButton.png")),
                                      new JButton (new ImageIcon("src/pictures/buttons/difficultyChooser/HardButton.png"))};
    /**
     * Holds the file that will act as background through out the whole run of the program.
     */
    private JButton mainMenu = RearingRanchDriver.getWindow().m.getMainMenuButton();
    private JLabel back = RearingRanchDriver.getWindow().m.getGoBack();

    /** <br> <b> layout </b> Instance of LayoutManager SpringLayout is used to organize GUI Components onto the screen. */
    private SpringLayout layout = new SpringLayout();

    public DifficultyChooser () {
        super();
        setLayout(layout);
        setSize(1280, 720);
        prepareGUI();
    }

    private void prepareGUI () {
        /** Text for button ToolTip that displays shortcuts for each button. */
        String [] shortcuts = {"Press e to play Easy.", "Press m to play Medium.", "Press h to play Hard."};

        for (int h = 0; h < diffButtons.length; h++) {
            diffButtons[h].addActionListener(this);
            diffButtons[h].setPreferredSize(buttonSize);
            diffButtons[h].setToolTipText(shortcuts[h]);
            layout.putConstraint(SpringLayout.NORTH, diffButtons[h], 200, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, diffButtons[h], 130 + ((int)buttonSize.getWidth() + 20)*h, SpringLayout.WEST, this);
            add (diffButtons[h]);
        }

        diffButtons[0].requestFocusInWindow();
        /** <br> <b> goBack </b> Instance of JLabel class that displays prompt message to return to main menu once the user
         * finishes reading the instructions above.*/
        JLabel goBack = new JLabel ("<html> Press the button to return to Main Menu.");
        goBack.setFont(new Font ("OCR A Std", Font.PLAIN, 14));

        layout.putConstraint(SpringLayout.SOUTH, goBack, -220, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, goBack, 0, SpringLayout.HORIZONTAL_CENTER, this);
        add(goBack);


        /** <br> <b> mainMenu </b> Instance of JButton class used to return the user to Main Menu.*/
        JButton mainMenu = RearingRanchDriver.getWindow().m.getMainMenuButton();
//        mainMenu.requestFocus();
//        mainMenu.addActionListener(e -> RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().m, "Rearing Ranch"));
//
//        mainMenu.addKeyListener(MainMenu.enter);
//        mainMenu.requestFocusInWindow();
//        layout.putConstraint(SpringLayout.WEST, mainMenu, 1, SpringLayout.WEST, this);
//        layout.putConstraint(SpringLayout.NORTH, mainMenu, 1, SpringLayout.NORTH, this);
        //(this.getWidth()/2)-(mainMenu.getWidth()/2) || 0
        add(mainMenu);
        mainMenu.revalidate();
        mainMenu.repaint();
        mainMenu.setVisible(true);
        list();
        System.out.println(layout.getConstraints(mainMenu).getX().getValue());
        System.out.println(layout.getConstraints(mainMenu).getY().getValue());
    }

    private BufferedImage generateBG () {
        BufferedImage img = null;
        try {
            img = ImageIO.read(MainMenu.background);
        } catch (IOException e) {}
        return img;
    }

    @Override
    protected void paintComponent (Graphics g) {
        g.drawImage(generateBG(), 0, 0, null);
        g.drawImage(MainMenu.getImage("GameLogo"), 380, 0, null);
    }

    private KeyListener enter = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent ke) {
            if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
                ((JButton) ke.getComponent()).doClick();
            }
        }
    };

    public void nextStage () {
        currentStage++;
        switch (currentStage) {
            case 0:
                RearingRanchDriver.getWindow().setPanel(new ColorChooser(difficulty), "Choose the Colour!");
                break;
            case 1:
                RearingRanchDriver.getWindow().setPanel(new AnimalClassifier(difficulty), "What's the animal?");
                break;
            case 2:
                RearingRanchDriver.getWindow().setPanel(new Arithmetics(difficulty), "Count them up!");
                break;
            case 4:
                RearingRanchDriver.getWindow().h.display(difficulty);
                difficulty = 0;
                currentStage = -1;
        }
    }

    public void initiatePlay (int difficulty) {
        int currentStage = 0;
        GameStage[] stages = {new ColorChooser(difficulty), new AnimalClassifier(difficulty), new Arithmetics(difficulty)};
        RearingRanchDriver.getWindow().setPanel(stages[0], "Choose the Colour!");
//        RearingRanchDriver.getWindow().setPanel(stages[1], "Choose the Animal!");
//        RearingRanchDriver.getWindow().setPanel(stages[2], "Do some Math!");


        // This loop is the reason that nothing showed up when you initiated play
        // The program focused on the loop and waited until it would finish with the loop
        // before displaying and handling the window.

        // I'm thinking, having a background thread that would do that loop and somehow have
        // it switching the stages.

//        while (true) {
//            if (!stages[0].isActive() && currentStage == 0) {
//                RearingRanchDriver.getWindow().setPanel(stages[1], "What's the animal?");
//                currentStage ++;
//            } else if (!stages[1].isActive() && currentStage == 1) {
//                RearingRanchDriver.getWindow().setPanel(stages[2], "Count them up!");
//                currentStage ++;
//            } else if (!stages[2].isActive())
//                break;
//        }

        // I'm thinking show highscores?
        // i think we should discuss this class
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(diffButtons[0]))
            difficulty = 1;
        else if (e.getSource().equals(diffButtons[1]))
            difficulty = 2;
        else if (e.getSource().equals(diffButtons[2]))
            difficulty = 3;
        else if (e.getSource().equals(back))
            RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().m, "Rearing Ranch");
        if (difficulty != 0)
            nextStage();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
