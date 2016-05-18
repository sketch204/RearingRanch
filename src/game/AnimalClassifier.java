package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * The game.GameStage class acts as a parent class for ColorChooser, dataclass.Animal Classifier and
 * Arithmetic classes. It handles all of animal drawing, reading the input from the
 * input bar as well as declaring the means for checking whether that input is legal or not.
 *
 * @author Inal Gotov, Modified by: Tamir Arnesty
 * @version 1.3, 2016-05-15.
 * Last Edited: 2016-05-16
 * Hours since 2016-05-15:
 *       Tamir: -
 *       Inal: 1:30
 */
public class AnimalClassifier extends GameStage {

    /**
     * Creates an instance of the AnimalClassifier game stage. Creates a new GameStage panel that is fit for the Animal Classifier stage of the game.
     * @param difficulty The difficulty on which this stage will be played on.
     */
    public AnimalClassifier(int difficulty) {
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
    protected void createGameButtons() {
        int i1, i2;
        buttons = new JButton[6];
        Dimension size = new Dimension(220, 140);
        ImageIcon [] icons = {new ImageIcon ("src/pictures/Button-Icon/stage2/Icon-Chicken.png"), new ImageIcon ("src/pictures/Button-Icon/stage2/Icon-Cow.png"),
                              new ImageIcon ("src/pictures/Button-Icon/stage2/Icon-Goat.png"), new ImageIcon ("src/pictures/Button-Icon/stage2/Icon-Goose.png"),
                              new ImageIcon ("src/pictures/Button-Icon/stage2/Icon-Horse.png"), new ImageIcon ("src/pictures/Button-Icon/stage2/Icon-Sheep.png")};

        File file = new File ("src/pictures/Button-Icon/stage2/Icon-Goose.png");
        System.out.println(file.exists());

        i1 = icons[0].getDescription().lastIndexOf('-') +1;
        for (int h = 0; h < buttons.length; h ++) {
            // Goose button is not taking its icon, cuz its a pussy!

//            if (h == 3)
//                buttons[h].setText("Goose is a acting like a hoe");
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