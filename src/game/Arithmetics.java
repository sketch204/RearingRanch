package game;

import javax.swing.*;

/**
 * The game.GameStage class acts as a parent class for ColorChooser, dataclass.Animal Classifier and
 * Arithmetic classes. It handles all of animal drawing, reading the input from the
 * input bar as well as declaring the means for checking whether that input is legal or not.
 *
 * @author Inal Gotov, Modified by: Tamir Arnesty
 * @version 1.3, 2016-05-15.
 * Last Edited: 2016-05-16
 * Hours since 2016-05-15:
 *       Tamir: 0
 *       Inal: 0
 */

// Will have am array list of JLabels, once reached a cap, will no longer add.

public class Arithmetics extends GameStage {

    /**
     * Initiates the playing process by filling the array with random
     * generated instance of animal classes and draws them in their proper positions.
     *
     * @param difficulty
     */
    public Arithmetics(int difficulty) {
        super(difficulty);
        buttons = new JButton[]{new JButton()};
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

    }

    @Override
    protected JLabel createJLabel(String text) {
        return null;
    }
}