package game;

import javax.swing.*;
import java.awt.*;

/**
 * The ColorChooser class is the first stage of the game. It contains 4 buttons, each representing a colour that
 * an animal on screen can have. The user must input the colors that are currently on screen and submit them for
 * checking. If they are corrent, the user may proceed to the next stage.
 *
 * @author Inal Gotov
 * @version 1.3, 2016-05-15.
 * Last Edited: 2016-05-16
 * Hours since 2016-05-15:
 *       Tamir: -
 *       Inal: 2:45
 */
public class ColorChooser extends GameStage {

    /**
     * Creates an instance of the ColorChooser game stage. Creates a new GameStage panel that is fit for the Color Chooser stage of the game.
     * @param difficulty The difficulty on which this stage will be played on.
     */
    public ColorChooser(int difficulty) {
        super(difficulty);
    }

    @Override
    protected void generateAnimals() {
        int [] animalsChoosen = new int [0];

        // {Chicken, goose, sheep, horse, cow, goat
        String [] [] animalColors = {{"Brown", "White"}, {"Black", "Brown", "White"}, {"Brown", "White"}, {"White", "Brown"},
                              {"BlackOn-Brown", "BlackOn-White", "BrownOn-White", "WhiteOn-Black", "WhiteOnBrown"}, {"Brown", "White", "Black"}};

        if (difficulty == 1){ // Animals used: 1-2; chicken, goose
            animalsChoosen = new int [(int)(Math.random()*2)];
        } else if (difficulty == 2) { // Animals used: 2-3; chicken, goose, sheep, horse
            animalsChoosen = new int [(int)(Math.random()*2) + 1];
        } else if (difficulty == 3) { // Animals used: 3-4; chicken, goose, sheep, horse, cow, goat
            animalsChoosen = new int [(int)(Math.random()*2) + 2];
        }

        for (int h = 0; h < animalsChoosen.length; h++) {
            animalsChoosen[h] = (int)(Math.random()*2);
        }

        /*
        Generate for the amount of stables there are.
        Random Color
        Generate an ArrayList of Colors generated, for quicker input checks.
        */
    }

    @Override
    protected boolean inputLegal() {
        setIsActive(false);
        System.out.println("Knock Knock, Color Chooser: Legality is not a thing yet :(");
        return false;
    }

    @Override
    protected void createGameButtons() {
        int i1, i2;
        buttons = new JButton[4];
        Dimension size = new Dimension(new ImageIcon ("src/pictures/Button-Icon/stage1/Icon-Black.png").getIconWidth(), new ImageIcon ("src/pictures/Button-Icon/stage1/Icon-Black.png").getIconHeight());
        ImageIcon [] icons = {new ImageIcon ("src/pictures/Button-Icon/stage1/Icon-Black.png"), new ImageIcon ("src/pictures/Button-Icon/stage1/Icon-Gray.png"),
                new ImageIcon ("src/pictures/Button-Icon/stage1/Icon-Brown.png"), new ImageIcon ("src/pictures/Button-Icon/stage1/Icon-White.png")};

        i1 = icons[0].getDescription().lastIndexOf('-') +1;
        for (int h = 0; h < buttons.length; h ++) {
            buttons[h] = new JButton (icons[h]);
            buttons[h].addActionListener(this);
            buttons[h].setSize(size);
            buttons[h].setPreferredSize(size);
            i2 = icons[h].getDescription().lastIndexOf('.');
            buttons[h].setText(icons[h].getDescription().substring(i1, i2));
            buttons[h].setBorder(BorderFactory.createEmptyBorder());
            buttons[h].setContentAreaFilled(true);
        }

        layout.putConstraint(SpringLayout.WEST, buttons [0], 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, buttons [0], getHeight() - buttons[0].getHeight(), SpringLayout.NORTH, this);
        add (buttons[0]);

        for (int h = 1; h < buttons.length; h ++) {
            layout.putConstraint(SpringLayout.WEST, buttons [h], 0, SpringLayout.EAST, buttons [h-1]);
            layout.putConstraint(SpringLayout.NORTH, buttons [h], getHeight() - buttons[0].getHeight(), SpringLayout.NORTH, this);
            add (buttons [h]);
        }
    }
}