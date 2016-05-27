package root.game;

import java.awt.*;
import java.io.File;
import javax.swing.*;

/**
 * The game.GameStage class acts as a parent class for ColorChooser, dataclass.Animal Classifier and
 * Arithmetic classes. It handles all of animal drawing, reading the input from the
 * input bar as well as declaring the means for checking whether that input is legal or not.
 *
 * @author Inal Gotov, modified by: Tamir Arnesty
 * @version 1.3, 2016-05-15.
 * Last Edited: 2016-05-16
 * Hours since 2016-05-15:
 *       Tamir: 0
 *       Inal: 2:00
 */

public class Arithmetics extends GameStage {

    /**
     * Creates an instance of the Arithmetics game stage. Creates a new GameStage panel that is fit for the Arithemtics stage of the game.
     * @param difficulty The difficulty on which this stage will be played on.
     */
    public Arithmetics(int difficulty) {
        super(difficulty);
        buttons = new JButton[]{new JButton()};
    }

    @Override
    protected void generateAnimals() {

    }

    @Override
    protected void inputLegal() {
        System.out.println("Knock Knock, Color Chooser: Legality is not a thing yet :(");
    }

    @Override
    protected void createAnimals(Graphics g) {

    }

    @Override
    protected void createGameButtons() {
        int i1, i2;
        ImageIcon [] icons = generateButtons();
        Dimension size = new Dimension(256, icons[0].getIconHeight());

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

        layout.putConstraint(SpringLayout.WEST, buttons [5], 2, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.SOUTH, buttons [5], 0, SpringLayout.SOUTH, this);
        add(buttons[5]);

        for (int h = 6; h < buttons.length; h ++) {
            layout.putConstraint(SpringLayout.WEST, buttons [h], 0, SpringLayout.EAST, buttons [h-1]);
            layout.putConstraint(SpringLayout.SOUTH, buttons [h], 0, SpringLayout.SOUTH, this);
            add (buttons [h]);
        }

        layout.putConstraint(SpringLayout.WEST, buttons [0], 2, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.SOUTH, buttons [0], 0, SpringLayout.NORTH, buttons[5]);
        add (buttons[0]);

        for (int h = 1; h < buttons.length/2; h++) {
            layout.putConstraint(SpringLayout.WEST, buttons [h], 0, SpringLayout.EAST, buttons [h-1]);
            layout.putConstraint(SpringLayout.SOUTH, buttons [h], 0, SpringLayout.NORTH, buttons[5]);
            add (buttons [h]);
        }
    }

    @Override
    protected ImageIcon[] generateButtons() {
        buttons = new JButton[10];
        ImageIcon[] icons = {new ImageIcon("src/pictures/buttons/stage3/Icon-1.png"), new ImageIcon("src/pictures/buttons/stage3/Icon-2.png"),
                             new ImageIcon("src/pictures/buttons/stage3/Icon-3.png"), new ImageIcon("src/pictures/buttons/stage3/Icon-4.png"),
                             new ImageIcon("src/pictures/buttons/stage3/Icon-5.png"), new ImageIcon("src/pictures/buttons/stage3/Icon-6.png"),
                             new ImageIcon("src/pictures/buttons/stage3/Icon-7.png"), new ImageIcon("src/pictures/buttons/stage3/Icon-8.png"),
                             new ImageIcon("src/pictures/buttons/stage3/Icon-9.png"), new ImageIcon("src/pictures/buttons/stage3/Icon-0.png")};
        return icons;
    }
}