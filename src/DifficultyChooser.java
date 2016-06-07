package root;

import root.game.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static root.MainMenu.*;

/**
 * DifficultyChooser class will display the user with three difficulty levels: easy, medium, and hard, that they must choose
 * from. Once they choose, the game will proceed.
 *
 * @author Tamir Arnesty
 * @version 1 2016-05-15
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir: 2:00
 *       Inal: 1:40
 */
public class DifficultyChooser extends JPanel implements ActionListener, KeyListener {
    private static int currentStage = -1;

    private Dimension buttonSize = new Dimension((new ImageIcon("src/pictures/buttons/difficultyChooser/EasyButton.png")).getIconWidth(),
                                                 (new ImageIcon("src/pictures/buttons/difficultyChooser/EasyButton.png")).getIconHeight());
    static JButton [] diffButtons = {new JButton (new ImageIcon("src/pictures/buttons/difficultyChooser/EasyButton.png")),
                                     new JButton (new ImageIcon("src/pictures/buttons/difficultyChooser/MediumButton.png")),
                                     new JButton (new ImageIcon("src/pictures/buttons/difficultyChooser/HardButton.png"))};
    /** <br> <b> mainMenu </b> Clone of a JButton from MainMenu used to return the user to Main Menu.*/
    private JButton mainMenu = new JButton(MainMenu.mainMenu.getIcon());
    /** <br> <b> layout </b> Instance of LayoutManager SpringLayout is used to organize GUI Components onto the screen. */
    private SpringLayout layout = new SpringLayout();
    /** <b> index </b> Integer that stores the index of mainChoices button array. */
    private int index = 0;

    public DifficultyChooser () {
        super();
        setLayout(layout);
        setSize(1280, 720);
        prepareGUI();
    }

    private void prepareGUI () {
        /** Text for button ToolTip that displays shortcuts for each button. */
        String [] shortcuts = {"Press e to play Easy.", "Press m to play Medium.", "Press h to play Hard."};
        /** <br> <b> goBack </b> Clone of a JLabel class from MainMenu that displays a prompt message to return to main menu. */
        JLabel goBack = new JLabel(MainMenu.goBack.getText());

        for (int h = 0; h < diffButtons.length; h++) {
            diffButtons[h].addActionListener(this);
            diffButtons[h].setBorder(BorderFactory.createEtchedBorder());
            diffButtons[h].setToolTipText(shortcuts[h]);
            diffButtons[h].addKeyListener(this);
            diffButtons[h].addKeyListener(enter);
            layout.putConstraint(SpringLayout.NORTH, diffButtons[h], 200, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, diffButtons[h], 130 + ((int)buttonSize.getWidth() + 20)*h, SpringLayout.WEST, this);
            add (diffButtons[h]);
        }

        diffButtons[index].requestFocusInWindow();

        goBack.setFont(new Font ("OCR A Std", Font.PLAIN, 14));
        layout.putConstraint(SpringLayout.SOUTH, goBack, -220, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, goBack, 0, SpringLayout.HORIZONTAL_CENTER, this);
        add(goBack);

        mainMenu.addActionListener(this);
        mainMenu.setContentAreaFilled(true);
        mainMenu.setBorder(BorderFactory.createEtchedBorder());
        layout.putConstraint(SpringLayout.NORTH, mainMenu, 20, SpringLayout.SOUTH, goBack);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainMenu, 0, SpringLayout.HORIZONTAL_CENTER, this);
        add(mainMenu);

//        winScreen.setSize(100, 30);
//        winScreen.setLayout(new FlowLayout());
//        winScreen.setResizable(false);
//        JLabel label = new JLabel ("You've completed this stage!");
//        winScreen.add(label);
//        JButton nextButton = new JButton("Next Stage!");
//        nextButton.addActionListener(e -> );
    }

    @Override
    protected void paintComponent (Graphics g) {
        g.drawImage(getBG(), 0, 0, null);
        g.drawImage(getImage("GameLogo"), 380, 0, null);
        revalidate();
        repaint();
    }

    private KeyListener enter = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent ke) {
            if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
                ((JButton) ke.getComponent()).doClick();
            }
        }
    };

    public static void nextStage (int difficulty, long timeOffset, String playerName) {
        currentStage++;
        switch (currentStage) {
            case 0:
                RearingRanchDriver.getWindow().setPanel(new ColorChooser(difficulty, 0), "Choose the Colour!");
//                startTime = System.currentTimeMillis();
                break;
            case 1:

                RearingRanchDriver.getWindow().setPanel(new AnimalClassifier(difficulty, timeOffset), "What's the animal?");

                break;
            case 2:
                RearingRanchDriver.getWindow().setPanel(new Arithmetics(difficulty, timeOffset), "Count them up!");
                break;
            case 4:
                RearingRanchDriver.getWindow().setPanel(MasterFrame.getH(), "High Scores");
                new Highscores(playerName, difficulty, timeOffset);
                HighscoresPanel.display(difficulty);
                currentStage = -1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(diffButtons[0]))
            nextStage(1, 0, null);
        else if (e.getSource().equals(diffButtons[1]))
            nextStage(2, 0, null);
        else if (e.getSource().equals(diffButtons[2]))
            nextStage(3, 0, null);
        else if (e.getSource().equals(mainMenu))
            RearingRanchDriver.getWindow().setPanel(MasterFrame.getM(), "Rearing Ranch");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if (index > diffButtons.length-1) {
                    index = 0;
                } else {
                    index++;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (index > 0) {
                    index--;
                } else {
                    index = diffButtons.length-1;
                }
                break;
            case KeyEvent.VK_E:
                diffButtons[0].doClick();
                break;
            case KeyEvent.VK_M:
                diffButtons[0].doClick();
                break;
            case KeyEvent.VK_H:
                diffButtons[0].doClick();
                break;
        }
        diffButtons[index].requestFocusInWindow();
        revalidate();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
