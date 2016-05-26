package game;

import dataclass.Animal;

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

/*
 * So the problem is that the ArrayList 'colors' which i use to store the color currently on the screen is difficult to initialize.
 * The problem is that when you call super (in the constructor) none of the instance variables are initialized (idk why, that's just how it works), but
 * inside the super a call to generateAnimals is made. generateAnimals uses the colors arrayList for its purposes. If the arrayList is initialized at
 * declaration, then once you reach to generateAnimals, it just sees a null and throws an exception. Easy you say, just initialize it inside
 * generateAnimals, and here's the mystery: after i initialize it inside the method, once the call to super has completed, the value that i assigned to
 * colors is gone, so after the 'super' line, colors is still null. JVM Mysteries...
 *
 * In case you though of initializing colors inside the constructor, call to super must be the first line of the constructor.
 *
 * I thought it is because the stack trace leads to initialization inside parent class, not child (GameStage, not ColorChooser), therefore the value gets lost when you reach child
 * but i've tried making a method that initializes it inside child and not parent. It no work.
 *
 * Honestly I'm lost, the braindead solution is to simply declare colors in GameStage but something in my subconsciousness tells me that i shouldn't do that, tell me if you think of anything.
 *
 * Oh and also... ROSTER!
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

    @Override
    protected void generateAnimals() {
        int [] animalsChosen = new int [getStablesAvailable()];

        // {Chicken, goose, sheep, horse, cow, goat}
        String [] [] animalColors = {{"Chicken", "Brown", "White"}, {"Goose", "Black", "Brown", "White"}, {"Sheep", "Brown", "White"}, {"Horse", "White", "Brown"},
                              {"Cow", "BlackOn-Brown", "BlackOn-White", "BrownOn-White", "WhiteOn-Black", "WhiteOnBrown"}, {"Goat", "Brown", "White", "Black"}};

        if (difficulty == 1){ // Animals used: 1-2; chicken, goose
            animalsChosen = new int [(int)(Math.random()*2)];
        } else if (difficulty == 2) { // Animals used: 2-3; chicken, goose, sheep, horse
            animalsChosen = new int [(int)(Math.random()*2) + 1];
        } else if (difficulty == 3) { // Animals used: 3-4; chicken, goose, sheep, horse, cow, goat
            animalsChosen = new int [(int)(Math.random()*2) + 2];
        }

        for (int h = 0; h < animalsChosen.length; h++) {
            animalsChosen[h] = (int)(Math.random()*2);
        }

        stock = new Animal[animalsChosen.length];
        for (int h = 0; h < stock.length; h++) {
            // Chooses a random color of an animal
            int index = (int)(Math.random()*(animalColors[animalsChosen[h]].length - 1))+1;
            String tempHold = animalColors[animalsChosen[h]][index];
            stock[h] = new Animal(tempHold, animalColors[animalsChosen[h]][0]);
            if (!colors.contains(tempHold))
                colors.add(tempHold);
        }

        /*
        Generate for the amount of stables there are.
        Random Color
        Generate an ArrayList of Colors generated, for quicker input checks.
        */
    }


    @Override
    protected boolean inputLegal() {
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
            return true;
        }
        System.out.println("Nope");
        return false;
    }

    @Override
    protected void createAnimals(Graphics g) {
        for (int h = 0; h < stock.length; h ++) {
            Image img = stock[h].getPicture().getScaledInstance(50, 50, 0);
            Point p = getPosition();
            g.drawImage(img, p.x, p.y, null);
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