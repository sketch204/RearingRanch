package game;

import javax.swing.*;
import java.awt.*;

public class ColorChooser extends GameStage {

    /**
     * Initiates the playing process by filling the array with random
     * generated instance of animal classes and draws them in their proper positions.
     *
     * @param difficulty
     */
    public ColorChooser(int difficulty) {
        super(difficulty);
    }

    @Override
    protected void generateAnimals() {

    }

    @Override
    protected boolean inputLegal() {
        return false;
    }

    @Override
    protected void createButtons() {
        buttons = new JButton[4];
        Dimension size = new Dimension(320, 140);
        ImageIcon [] icons = {new ImageIcon ("src/pictures/Button-Icon/Icon-Black.png"), new ImageIcon ("src/pictures/Button-Icon/Icon-Gray.png"),
                new ImageIcon ("src/pictures/Button-Icon/Icon-Brown.png"), new ImageIcon ("src/pictures/Button-Icon/Icon-White.png")};


        for (int h = 0; h < 4; h ++) {
            buttons[h] = new JButton (icons[h]);
            buttons[h].addActionListener(this);
            buttons[h].setPreferredSize(size);
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
}