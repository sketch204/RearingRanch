package game;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The ColorChooser class is the first stage of the game. It contains 4 buttons, each representing a colour that
 * an animal on screen can have. The user must input the colors that are currently on screen and submit them for
 * checking. If they are corrent, the user may proceed to the next stage.
 *
 * @author Inal Gotov
 * @version 1.3, 2016-05-15.
 * Last Edited: 2016-05-16
 * Hours since 2016-05-15:
 *       Tamir: -
 *       Inal: 2:45
 */
public class ColorChooser extends GameStage {
    /**
     * Contains the colors that are currently on screen.
     * Used for checking legality of input.
     */
    private ArrayList<String> colors = new ArrayList<String>();

    /**
     * Creates an instance of the ColorChooser game stage. Creates a new GameStage panel that is fit for the Color Chooser stage of the game.
     * @param difficulty The difficulty on which this stage will be played on.
     */
    public ColorChooser(int difficulty) {
        super(difficulty);
    }

    @Override
    protected void generateAnimals() {
        int [] animalsChoosen = new int [0];

        // {Chicken, goose, sheep, horse, cow, goat
        String [] [] animalColors = {{"Brown", "White"}, {"Black", "Brown", "White"}, {"Brown", "White"}, {"White", "Brown"},
                              {"BlackOn-Brown", "BlackOn-White", "BrownOn-White", "WhiteOn-Black", "WhiteOnBrown"}, {"Brown", "White", "Black"}};

        if (difficulty == 1){ // Animals used: 1-2; chicken, goose
            animalsChoosen = new int [(int)(Math.random()*2)];
        } else if (difficulty == 2) { // Animals used: 2-3; chicken, goose, sheep, horse
            animalsChoosen = new int [(int)(Math.random()*2) + 1];
        } else if (difficulty == 3) { // Animals used: 3-4; chicken, goose, sheep, horse, cow, goat
            animalsChoosen = new int [(int)(Math.random()*2) + 2];
        }

        for (int h = 0; h < animalsChoosen.length; h++) {
            animalsChoosen[h] = (int)(Math.random()*2);
        }

        /*
        Generate for the amount of stables there are.
        Random Color
        Generate an ArrayList of Colors generated, for quicker input checks.
        */
    }

    @Override
    protected boolean inputLegal() {
        setIsActive(false);
        System.out.println("Knock Knock, Color Chooser: Legality is not a thing yet :(");
        return false;
    }

    @Override
    protected void createAnimals(Graphics g) {
        g.drawImage(stock[0].getPicture(), 0, 0, null);
    }

    @Override
    protected ImageIcon[] generateButtons () {
        buttons = new JButton[4];
        ImageIcon [] icons = {new ImageIcon ("src/pictures/Button-Icon/stage1/Icon-Black.png"), new ImageIcon ("src/pictures/Button-Icon/stage1/Icon-Gray.png"),
                              new ImageIcon ("src/pictures/Button-Icon/stage1/Icon-Brown.png"), new ImageIcon ("src/pictures/Button-Icon/stage1/Icon-White.png")};
        return icons;
    }
}