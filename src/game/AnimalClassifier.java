package root.game;

import root.RearingRanchDriver;
import root.dataclass.Animal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
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
     * Contains the animals that are currently on screen.
     * Used for checking legality of input.
     */
    private static ArrayList<String> animals = new ArrayList<String>();

    /**
     * Creates an instance of the AnimalClassifier game stage. Creates a new GameStage panel that is fit for the Animal Classifier stage of the game.
     * @param difficulty The difficulty on which this stage will be played on.
     */
    public AnimalClassifier(int difficulty) {
        super(difficulty);
        System.out.println(animals.toString());
        System.out.println(stock.length);
    }

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
            animalsChosen = new int [(int)(Math.round(Math.random()+1)) + 2];
        } else if (difficulty == 3) { // Animals used: 3-4; chicken, goose, sheep, horse, cow, goat
            animalsChosen = new int [(int)(Math.round(Math.random()+1)) + 4];
        }

        // Fill it with a random animal based on difficulty
        for (int h = 0; h < animalsChosen.length; h++) {
            animalsChosen[h] = (int)(Math.random()*(2*difficulty));
        }

        // Generate random animals, create and fill the 'stock' array
        stock = new Animal[animalsChosen.length];
        Point[] p = getPosition(stock.length);
        int starter = 0;
        if (p.length > stock.length) {
            for (int h = 0; h < p.length - stock.length; h ++) {
                int index = (int)(Math.random()*(animalColors[animalsChosen[0]].length - 1))+1;
                String tempHold = animalColors[animalsChosen[0]][index];

                stock[0] = new Animal(tempHold, animalColors[animalsChosen[0]][0], p[0].x, p[0].y, p[stock.length-1].x, p[stock.length-1].y);
                if (!animals.contains(animalColors[animalsChosen[h]][0]))
                    animals.add(animalColors[animalsChosen[h]][0]);
            }
            starter = p.length - stock.length;
        }
        for (int h = starter; h < stock.length; h++) {
            // Chooses a random color of an animal
            int index = (int)(Math.random()*(animalColors[animalsChosen[h]].length - 1))+1;
            String tempHold = animalColors[animalsChosen[h]][index];
            stock[h] = new Animal(tempHold, animalColors[animalsChosen[h]][0], p[h].x, p[h].y);
            if (!animals.contains(animalColors[animalsChosen[h]][0]))
                animals.add(animalColors[animalsChosen[h]][0]);
        }
    }

    @Override
    protected void inputLegal() {
        ArrayList<String> tempHold = new ArrayList<String>(animals);
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
        if (matchesFound == animals.size() && input.size() == animals.size()) {
            System.out.println("You guessed it!");
            RearingRanchDriver.getWindow().d.nextStage();
        } else
            System.out.println("Nope");
    }

    @Override
    protected void createAnimals(Graphics g) {
        for (int h = 0; h < stock.length; h ++) {
            g.drawImage(stock[h].getPicture(), stock[h].getX(), stock[h].getY(), null);
            if (stock[h].stallNeeded()) {
                BufferedImage stall = null;
                int x = 0, y = 0;
                try {
                    if (stock[h].getX() == 338) {
                        stall = ImageIO.read(new File("src/picture/backgrounds/stall-left.png"));
                        x = 1; y = 106;
                    } else {
                        stall = ImageIO.read(new File("src/picture/backgrounds/stall-right.png"));
                        x = 894; y = 85;
                    }
                } catch (IOException e) {}
                g.drawImage(stall, x, y, null);
            }
        }
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