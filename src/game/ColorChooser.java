package root.game;

import root.RearingRanchDriver;
import root.dataclass.Animal;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The ColorChooser class is the first stage of the game. It contains 4 buttons, each representing a colour that
 * an animal on screen can have. The user must input the colors that are currently on screen and submit them for
 * checking. If they are corrent, the user may proceed to the next stage.
 *
 * @author Inal Gotov
 * @version 1.3, 2016-05-15
 * Last Edited: 2016-05-16
 * Hours since 2016-05-15:
 *       Tamir: -
 *       Inal: 5:15
 */

public class ColorChooser extends GameStage {
    /**
     * Contains the colors that are currently on screen.
     * Used for checking legality of input.
     */
    private static ArrayList<String> colors = new ArrayList<String>();
    /**
     * Creates an instance of the ColorChooser game stage. Creates a new GameStage panel that is fit for the Color Chooser stage of the game.
     * @param difficulty The difficulty on which this stage will be played on.
     */
    public ColorChooser(int difficulty) {
        super(difficulty);
//        colors = new ArrayList<>();
        System.out.println(colors.toString());
    }

    /**
     * Generates the animals based on difficulty and fills the 'stock' array with them.
     */
    @Override
    protected void generateAnimals() {
        // Holds the index within animalColors, of which animalswere chosen to be drawn.
        int [] animalsChosen = new int [getStablesAvailable()];

        // {Chicken, goose, sheep, horse, cow, goat}
        String [] [] animalColors = {{"Chicken", "Brown", "White"}, {"Goose", "Black", "Brown", "White"}, {"Sheep", "Brown", "White"}, {"Horse", "White", "Brown"},
                              {"Cow", "BlackOn-Brown", "BlackOn-White", "BrownOn-White", "WhiteOn-Black", "WhiteOnBrown"}, {"Goat", "Brown", "White", "Black"}};

        // Set amount of animals to be chosen based on difficulty
        if (difficulty == 1){ // Animals used: 1-2; chicken, goose
            animalsChosen = new int [(int)(Math.round(Math.random()+1))];
        } else if (difficulty == 2) { // Animals used: 2-3; chicken, goose, sheep, horse
            animalsChosen = new int [(int)(Math.round(Math.random()+1)) + 1];
        } else if (difficulty == 3) { // Animals used: 3-4; chicken, goose, sheep, horse, cow, goat
            animalsChosen = new int [(int)(Math.round(Math.random()+1)) + 2];
        }

        // Fill it with a random animal based on difficulty
        for (int h = 0; h < animalsChosen.length; h++) {
            animalsChosen[h] = (int)(Math.random()*(2*difficulty));
        }

        // Generate random colors, create and fill the 'stock' array
        stock = new Animal[animalsChosen.length];
        for (int h = 0; h < stock.length; h++) {
            // Chooses a random color of an animal
            int index = (int)(Math.random()*(animalColors[animalsChosen[h]].length - 1))+1;
            String tempHold = animalColors[animalsChosen[h]][index];
            stock[h] = new Animal(tempHold, animalColors[animalsChosen[h]][0]);
            if (!colors.contains(tempHold))
                colors.add(tempHold);
        }
    }

    /**
     * Checks whether the input is legal or, if legal then proceeds to the next stage.
     */
    @Override
    protected void inputLegal() {
        ArrayList<String> tempHold = new ArrayList<String>(colors);
        int matchesFound = 0;
        for (int h = 0; h < input.size(); h++) {
            for (int j = 0; j < tempHold.size(); j++) {
                if (input.get(h).getText().equals(tempHold.get(j))){
                    tempHold.remove(j);
                    matchesFound ++;
                    break;
                }
            }
        }
        if (matchesFound == colors.size()) {
            System.out.println("You guessed it!");
            RearingRanchDriver.getWindow().d.nextStage();
        }
        System.out.println("Nope");
    }

    @Override
    protected void createAnimals(Graphics g) {
        for (int h = 0; h < stock.length; h ++) {
            Image img = stock[h].getPicture().getScaledInstance(50, 50, 0);
            Point[] p = getPosition(stock.length);
            g.drawImage(img, p[0].x, p[0].y, null);
        }

    }

    @Override
    protected ImageIcon[] generateButtons () {
        buttons = new JButton[4];
        ImageIcon [] icons = {new ImageIcon ("src/pictures/buttons/stage1/Icon-Black.png"), new ImageIcon ("src/pictures/buttons/stage1/Icon-Gray.png"),
                              new ImageIcon ("src/pictures/buttons/stage1/Icon-Brown.png"), new ImageIcon ("src/pictures/buttons/stage1/Icon-White.png")};
        return icons;
    }
}