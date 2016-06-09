import java.awt.*;
import java.util.ArrayList;
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
    private static ArrayList<String> animals;

    /**
     * Creates an instance of the AnimalClassifier game stage. Creates a new GameStage panel that is fit for the AnimalClassifier stage of the game.
     * @param difficulty The difficulty on which this stage will be played on.
     */
    public AnimalClassifier(int difficulty, long timeOffset) {
        super(difficulty, timeOffset);
    }

    @Override
    protected void generateAnimals() {
        // Holds the index within animalColors, of which animals were chosen to be drawn.
        int [] animalsChosen = new int [getStablesAvailable()];
        animals = new ArrayList<String>();

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

        // Fill it with a random animal
        for (int h = 0; h < animalsChosen.length; h++) {
            animalsChosen[h] = (int)(Math.random()*(6));
        }

        // Generate random animals, create and fill the 'stock' array
        stock = new Animal[animalsChosen.length];
        Point[] p = getPosition(stock.length);
        int starter = 0;
        // If stalls are needed (will happen if background == 2 and amount-of-animals > 3
        if (p.length > stock.length) {
            for (int h = 0; h < p.length - stock.length; h ++) {
                int index = (int)(Math.random()*(animalColors[animalsChosen[h]].length - 2))+1; // Generate Random Color
                try {
                    stock[h] = new Animal(animalColors[animalsChosen[h]][index], animalColors[animalsChosen[h]][0], p[h], p[h + stock.length]); // Create new Animal
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Temporary
                    System.out.println("Max: " + (p.length - stock.length));
                    System.out.println("Stock: " + stock.length);
                    System.out.println("Points: " + p.length);
                    System.out.println("Index: " + index);
                    System.out.println("Counter: " + h);
                    e.printStackTrace();
                }
                if (!animals.contains(animalColors[animalsChosen[h]][0])) // If such an animal has not been recorded yet, then record it
                    animals.add(animalColors[animalsChosen[h]][0]);
            }
            starter = p.length - stock.length; // Set starter, so that the loop below skips what has been assigned already
        }
        for (int h = starter; h < stock.length; h++) { // Generate the rest of the animals.
            int index = (int)(Math.random()*(animalColors[animalsChosen[h]].length - 1))+1; // Generate Random Color
            stock[h] = new Animal(animalColors[animalsChosen[h]][index], animalColors[animalsChosen[h]][0], p[h]); // Create new Animal
            if (!animals.contains(animalColors[animalsChosen[h]][0])) // If such an animal has not been recorded yet, then record it
                animals.add(animalColors[animalsChosen[h]][0]);
        }
        gameObjective = "Name each animal that can see on screen.";
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
            winScreen();
        } else
            wrongAnswer();
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