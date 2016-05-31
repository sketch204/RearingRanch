package root;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import root.Highscores;

import static root.MainMenu.*;

/**
 * The HighscoresPanel class displays the top 10 entries of an ArrayList of Player
 * with their name, difficulty level, time, and score onto the screen. Button handling and choices
 *
 * @author Tamir Arnesty
 * @version 1 2016-05-30
 * Last Edited: 2016-05-30
 * Hours since 2016-05-11:
 *       Tamir: 5:00 (as of 4:26 may 31)
 *       Inal: -
 */
public class HighscoresPanel extends JPanel implements ActionListener, KeyListener{

    /** <br> <b> layout </b> Instance of LayoutManager SpringLayout is used to organize GUI Components onto the screen. */
    private SpringLayout layout = new SpringLayout();
    JButton [] levelChoices = new JButton[5];

    public HighscoresPanel () {
        super ();
        setLayout(layout);
        setSize(1280, 720);
        prepareGUI();
    }

    private void prepareGUI() {
        try {
            levelChoices = new JButton[]{new JButton(new ImageIcon((ImageIO.read(new File("src/pictures/buttons/difficultyChooser/EasyButton.png"))).getScaledInstance(98, 22, 0))),
                    new JButton(new ImageIcon((ImageIO.read(new File("src/pictures/buttons/difficultyChooser/MediumButton.png"))).getScaledInstance(98, 22, 0))),
                    new JButton(new ImageIcon((ImageIO.read(new File("src/pictures/buttons/difficultyChooser/HardButton.png"))).getScaledInstance(98, 22, 0)))};
        } catch (IOException e) {
        } catch (NullPointerException e) {}

        /** Text for button ToolTip that displays shortcuts for each button. */
        String [] shortcuts = {"Press e for easy.", "Press m for medium.", "Press h for hard.", "Press c to clear high scores.", "Press p to print.", "Press r to return to Main Menu."};

        layout.putConstraint(SpringLayout.NORTH, levelChoices[1], 200, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, levelChoices[1], 0, SpringLayout.HORIZONTAL_CENTER, this);
        add(levelChoices[1]);
        levelChoices[0].requestFocusInWindow();

        for (int i = 0; i < levelChoices.length; i++) {
            levelChoices[i].addActionListener(this);
            levelChoices[i].addKeyListener(enter);
            levelChoices[i].addKeyListener(this);
            levelChoices[i].setToolTipText(shortcuts[i]);
            levelChoices[i].setContentAreaFilled(true);
            levelChoices[i].setBorder(BorderFactory.createEtchedBorder());
        }

        for (int i = 0; i < levelChoices.length; i+= 2) {
            layout.putConstraint(SpringLayout.NORTH, levelChoices[i], 200, SpringLayout.NORTH, this);
            if (i == 0)
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, levelChoices[i], -180, SpringLayout.HORIZONTAL_CENTER, levelChoices[1]);
            else
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, levelChoices[i], 180, SpringLayout.HORIZONTAL_CENTER, levelChoices[1]);
            add (levelChoices[i]);
        }


        JButton [] options = {new JButton(new ImageIcon("src/pictures/buttons/highscores/delete")),
                new JButton(new ImageIcon("src/pictures/buttons/highscores/printer")),
                new JButton(new ImageIcon("src/pictures/buttons/MainMenuButton"))};
        layout.putConstraint(SpringLayout.SOUTH, options[0], -100, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, options[0], 100, SpringLayout.HORIZONTAL_CENTER, this);
        add(options[0]);
        layout.putConstraint(SpringLayout.SOUTH, options[1], -100, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, options[1], 0, SpringLayout.HORIZONTAL_CENTER, this);
        add(options[1]);
        layout.putConstraint(SpringLayout.SOUTH, options[2], -100, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, options[2], -100, SpringLayout.HORIZONTAL_CENTER, this);
        add(options[2]);

        String difficulty;
        if (levelChoices[0].isFocusOwner())
            difficulty = "Easy";
        else if (levelChoices[1].isFocusOwner())
            difficulty = "Medium";
        else
            difficulty = "Hard";

        JLabel viewingLevel = new JLabel ("<html> You are currently viewing the leaderboard for: <br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>" + difficulty);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, viewingLevel, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, viewingLevel, 80, SpringLayout.WEST, this);
        viewingLevel.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 16));
        add(viewingLevel);
    }

    @Override
    public void paintComponent (Graphics g) {
        g.drawImage(generateBG(), 0, 0, null);
        g.drawImage(getImage("GameLogo"), 380, 0, null);
        g.drawImage(getImage("Company Logo Scaled").getScaledInstance(75, 75, 0), -5, 5 , null);
        g.drawImage(getImage("Logo Name").getScaledInstance(85, 41, 0), this.getWidth()-90, 5, null);
        g.drawImage(getImage("buttons/highscores/delete"), 800, this.getHeight()-getImage("buttons/highscores/delete").getHeight()-50, null);


        if (background.getPath().contains("background1"))
            g.setColor(new Color (255, 255, 255));
        else if ((background.getPath().contains("background2")))
            g.setColor(new Color(43, 167, 133));
        else if (background.getPath().contains("background3"))
            g.setColor(new Color (164, 0, 3));
        else
            g.setColor(new Color(0, 0, 0));

        g.setFont(new Font ("Times New Roman", Font.BOLD, 20));
        g.drawString ("Name", 415, 255);
        g.drawString("Score", 615, 255);
        for (int i = 0; i < 10; i++) {
            //if (i < Highscores.players.size())
                g.drawString ("" + (i + 1) + ". ", 400, 280 + i*30);
        }
    }

    private BufferedImage generateBG () {
        BufferedImage img = null;
        try {
            img = ImageIO.read(background);
        } catch (IOException e) {}
        return img;
    }

    /**
     * Set this panel as the main panel, and display highscores, for the given difficulty.
     * @param difficulty The difficulty of the scoreboard to display.
     */
    public void display (int difficulty) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(levelChoices[0])) {
            display(1);
            levelChoices[0].requestFocusInWindow();
        } else if (e.getSource().equals(levelChoices[1])) {
            display(2);
            levelChoices[1].requestFocusInWindow();
        } else if (e.getSource().equals(levelChoices[2])) {
            display(3);
            levelChoices[2].requestFocusInWindow();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_E:
                levelChoices[0].doClick();
                break;
            case KeyEvent.VK_M:
                levelChoices[1].doClick();
                break;
            case KeyEvent.VK_H:
                levelChoices[2].doClick();
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
