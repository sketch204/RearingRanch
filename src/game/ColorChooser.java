package root.game;

import root.dataclass.Animal;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * The ColorChooser class is the first stage of the game. It contains 4 buttons, each representing a colour that
 * an animal on screen can have. The user must input the colors that are currently on screen and submit them for
 * checking. If they are current, the user may proceed to the next stage.
 *
 * @author Inal Gotov
 * @version 1.3, 2016-05-15
 * Last Edited: 2016-05-16
 * Hours since 2016-05-15:
 *       Tamir: -
 *       Inal: 7:15
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
    public ColorChooser(int difficulty, long timeOffset) {
        super(difficulty, timeOffset);
    }

    /**
     * Generates the animals based on difficulty and fills the 'stock' array with them.
     */
    @Override
    protected void generateAnimals() {
        // Holds the index within animalColors, of which animals were chosen to be drawn.
        int [] animalsChosen = new int [getStablesAvailable()];

        // {Chicken, goose, sheep, horse, cow, goat}
        String [] [] animalColors = {{"Chicken", "Brown", "White"}, {"Goose", "Brown", "White"}, {"Sheep", "Brown", "White"}, {"Horse", "Black", "White", "Brown"},
                                     {"Cow", "BlackOn-Brown", "BlackOn-White", "BrownOn-White", "WhiteOn-Black", "WhiteOn-Brown"}, {"Goat", "Brown", "White", "Gray"}};

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
        Point[] p = getPosition(stock.length);
        int starter = 0;
        if (p.length > stock.length) {
            int index = (int)(Math.random()*(animalColors[animalsChosen[0]].length - 1))+1;
            String tempHold = animalColors[animalsChosen[0]][index];

            stock[0] = new Animal(tempHold, animalColors[animalsChosen[0]][0], p[0], p[p.length-1]);
            if (animalsChosen[0] == 4)
                tempHold = tempHold.substring(tempHold.indexOf('-') + 1);
            if (!colors.contains(tempHold))
                colors.add(tempHold);
            starter = 1;
        }
        for (int h = starter; h < stock.length; h++) {
            // Chooses a random color of an animal
            int index = (int)(Math.random()*(animalColors[animalsChosen[h]].length - 1))+1;
            String tempHold = animalColors[animalsChosen[h]][index];
            stock[h] = new Animal(tempHold, animalColors[animalsChosen[h]][0], p[h]);
            if (animalsChosen[h] == 4)
                tempHold = tempHold.substring(tempHold.indexOf('-') + 1);
            if (!colors.contains(tempHold))
                colors.add(tempHold);
        }
        gameObjective = "List the main colour of each animal on screen.";
    }

    /**
     * Checks whether the input is legal or not, if legal then proceeds to the next stage.
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
        if (matchesFound == colors.size() && input.size() == colors.size()) {
            winScreen();
        } else
            System.out.println("Nope");
//            wrongAnswer();
    }

    @Override
    protected void createAnimals(Graphics g) {
        for (int h = 0; h < stock.length; h ++) {
            g.drawImage(stock[h].getPicture(), stock[h].getX(), stock[h].getY(), null);
            if (stock[h].stallNeeded()) {
                BufferedImage stall = null;
                int x = 0, y = 0;
                try {
                    if (stock[h].getX() + stock[h].getPicture().getWidth() == 348) {
                        stall = ImageIO.read(new File("src/pictures/backgrounds/stall-left.png"));
                        x = 1; y = 106;
                    } else {
                        stall = ImageIO.read(new File("src/pictures/backgrounds/stall-right.png"));
                        x = 894; y = 85;
                    }
                } catch (IOException e) {
                    System.out.println("Please re-install this application!");
                    e.printStackTrace();
                }
                g.drawImage(stall, x, y, null);
            }
        }
    }

    @Override
    protected ImageIcon[] generateButtons() {
        buttons = new JButton[4];
        ImageIcon [] icons = {new ImageIcon ("src/pictures/buttons/stage1/Icon-Black.png"), new ImageIcon ("src/pictures/buttons/stage1/Icon-Gray.png"),
                              new ImageIcon ("src/pictures/buttons/stage1/Icon-Brown.png"), new ImageIcon ("src/pictures/buttons/stage1/Icon-White.png")};
        return icons;
    }
}