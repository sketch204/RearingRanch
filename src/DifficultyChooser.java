import game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * DifficultyChooser class will display the user with three difficulty levels: easy, medium, and hard, that they must choose
 * from. Once they choose, the game will proceed.
 *
 * @author Tamir Arnesty
 * @version 1 2016-05-15
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir: 2:00
 *       Inal: 0:40
 */
public class DifficultyChooser extends JPanel implements ActionListener {
    private Dimension buttonSize = new Dimension((new ImageIcon("src/pictures/buttons/difficultyChooser/EasyButton.png")).getIconWidth(),
                                                 (new ImageIcon("src/pictures/buttons/difficultyChooser/EasyButton.png")).getIconHeight());
    private JButton [] diffButtons = {new JButton (new ImageIcon("src/pictures/buttons/difficultyChooser/EasyButton.png")),
                                      new JButton (new ImageIcon("src/pictures/buttons/difficultyChooser/MediumButton.png")),
                                      new JButton (new ImageIcon("src/pictures/buttons/difficultyChooser/HardButton.png"))};
    /**
     * Holds the file that will act as background through out the whole run of the program.
     */
    private JButton mainMenu = RearingRanchDriver.getWindow().m.getMainMenu();
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
        for (int h = 0; h < 3; h++) {
            diffButtons[h].addActionListener(this);
            diffButtons[h].setPreferredSize(buttonSize);
            layout.putConstraint(SpringLayout.NORTH, diffButtons[h], 200, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, diffButtons[h], 130 + ((int)buttonSize.getWidth() + 20)*h, SpringLayout.WEST, this);
            add (diffButtons[h]);
        }

        layout.putConstraint(SpringLayout.SOUTH, mainMenu, -120, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, mainMenu, 500, SpringLayout.WEST, this);
        add(mainMenu);

//        layout.putConstraint(SpringLayout.SOUTH, back, -10, SpringLayout.NORTH, mainMenu);
//        layout.putConstraint(SpringLayout.WEST, back, 470, SpringLayout.WEST, this);
//        back.setFont(new Font ("OCR A Std", Font.PLAIN, 14));
//        add(back);
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
    }

    private KeyListener enter = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent ke) {
            if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
                ((JButton) ke.getComponent()).doClick();
            }
        }
    };

    public void initiatePlay (int difficulty) {
        int currentStage = 0;
        GameStage[] stages = {new ColorChooser(difficulty), new AnimalClassifier(difficulty), new Arithmetics(difficulty)};
        RearingRanchDriver.getWindow().setPanel(stages[0], "Choose the Colour!");

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
            initiatePlay(0);
        else if (e.getSource().equals(diffButtons[1]))
            initiatePlay(1);
        else if (e.getSource().equals(diffButtons[2]))
            initiatePlay(2);
        else if (e.getSource().equals(back))
            RearingRanchDriver.getWindow().setPanel(RearingRanchDriver.getWindow().m, "Rearing Ranch");
    }
}
