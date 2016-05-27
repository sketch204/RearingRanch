package root.game;

import java.awt.*;
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
    protected void inputLegal() {
        setIsActive(false);
        System.out.println("Knock Knock, Color Chooser: Legality is not a thing yet :(");
    }

    @Override
    protected void createAnimals(Graphics g) {

    }

    @Override
    protected ImageIcon[] generateButtons() {
        buttons = new JButton[6];
        ImageIcon [] icons = {new ImageIcon ("src/pictures/buttons/stage2/Icon-Chicken.png"), new ImageIcon ("src/pictures/buttons/stage2/Icon-Cow.png"),
                new ImageIcon ("src/pictures/buttons/stage2/Icon-Goat.png"), new ImageIcon ("src/pictures/buttons/stage2/Icon-Goose.png"),
                new ImageIcon ("src/pictures/buttons/stage2/Icon-Horse.png"), new ImageIcon ("src/pictures/buttons/stage2/Icon-Sheep.png")};
        return icons;
    }
}