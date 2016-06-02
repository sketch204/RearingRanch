package root.game;

import root.dataclass.Animal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
 *       Tamir: 0
 *       Inal: 2:00
 */

public class Arithmetics extends GameStage {
    int result = -1;

    /**
     * Creates an instance of the Arithmetics game stage. Creates a new GameStage panel that is fit for the Arithemtics stage of the game.
     * @param difficulty The difficulty on which this stage will be played on.
     */
    public Arithmetics(int difficulty) {
        super(difficulty);
    }

    private int generateResult (String priority) {
        if (difficulty == 1) {
            gameObjective = "How many animals can you see right now?";
            return stock.length;
        } else if (difficulty == 2) {
            int counter = 0;
            for (int h = 0; h < stock.length; h ++)
                if (stock[h].getType().equals(priority))
                    counter ++;
            gameObjective = "How many " + priority.toLowerCase() + " can you see right now?";
            return counter;
        } else if (difficulty == 3) {
            int objective = (int) (Math.random() * 2);
            int total = (int)((Math.random() * ((20 - 10) + 1)) + 10);

            // idk about this.
            // Those sentence are not made for this
            String [][] messageVariations = {{"If here w", "Here w", "If w", "W"}, {", and ", " and ", ""}};

            if (objective == 1) {
                gameObjective = "If here we have " + stock.length + " animals, and in another barn we have " + (total - stock.length) + " animals, how many animals do we have in total?";
                gameObjective = "Here we have " + stock.length + " animals. In another barn we have " + (total - stock.length) + " animals. How many animals do we have in total?";
                return total;
            } else if (objective == 2) {
                gameObjective = "If here we have " + stock.length + " animals, and in another barn we have " + (total - stock.length) + " animals how many more animals do we have in the other barn?";
                gameObjective = "Here we have " + stock.length + " animals. In another barn we have " + (total - stock.length) + " animals. How many more animals do we have in the other barn?";
                return (total - stock.length) - stock.length;
            } else if (objective == 3) {
                gameObjective = "If we have " + total + " animals in total, and we can see " + stock.length + " animals here, how many animals do we have in total?";
                return total - stock.length;
            }
        }

        return 0;
    }

    @Override
    protected void generateAnimals () {
        // {Chicken, goose, sheep, horse, cow, goat}
        String [] [] animalColors = {{"Chicken", "Brown", "White"}, {"Goose", "Brown", "White"}, {"Sheep", "Brown", "White"}, {"Horse", "Black", "White", "Brown"},
                {"Cow", "BlackOn-Brown", "BlackOn-White", "BrownOn-White", "WhiteOn-Black", "WhiteOn-Brown"}, {"Goat", "Brown", "White", "Gray"}};
        // If difficulty != 1 -> Min: 3-4, Max: 6 || If difficulty == 1 -> Min: 1, Max: 6
        int [] animalsChosen = new int [(difficulty == 2) ? (int)((Math.random() * ((6 - (6 - 2)) + 1)) + (6 - 2)) : (int)((Math.random() * ((6 - (6 - 3)) + 1)) + (6 - 3))];
        int starter = 0, priorityAnimal = (difficulty == 2) ? (int)(Math.random()*5) : -1;

        if (difficulty == 2) {
            int randomGenerated = 0;
            for (int h = 0; h < animalsChosen.length; h++) {
                if ((int) (Math.random() * 2) + 1 == 2 && randomGenerated < 2) {
                    do {
                        animalsChosen[h] = (int) (Math.random() * 6);
                    } while (animalsChosen[h] == priorityAnimal);
                    randomGenerated++;
                } else {
                    animalsChosen[h] = priorityAnimal;
                }
            }
        } else {
            // Fill it with a random animal based on difficulty
            for (int h = 0; h < animalsChosen.length; h++) {
                animalsChosen[h] = (int) (Math.random() * 6);
            }
        }

        // Generate random animals, create and fill the 'stock' array
        stock = new Animal[animalsChosen.length];
//        Point[] p = getPosition(stock.length);
        Point[] p = new Point[0];
        // If stalls are needed (will happen if background == 2 and animalsChosen.length > 3
        if (p.length > stock.length) {
            for (int h = 0; h < p.length - stock.length; h ++) {
                int index = (int)(Math.random()*(animalColors[animalsChosen[h]].length - 2))+1; // Generate Random Color
                stock[h] = new Animal(animalColors[animalsChosen[h]][index], animalColors[animalsChosen[h]][0], p[h], p[h + stock.length - 1]); // Create new Animal
            }
            starter = p.length - stock.length; // Set starter, so that the loop below skips what has been assigned already
        }
        // Generate the rest of the animals.
        for (int h = starter; h < stock.length; h++) {
            int index = (int)(Math.random()*(animalColors[animalsChosen[h]].length - 1))+1; // Generate Random Color
            stock[h] = new Animal(animalColors[animalsChosen[h]][index], animalColors[animalsChosen[h]][0], p[h]); // Create new Animal
        }
        result = generateResult(animalColors[priorityAnimal][0]);
    }

    @Override
    protected void inputLegal() {
        System.out.println("Knock Knock, Color Chooser: Legality is not a thing yet :(");
    }

    @Override
    protected void createAnimals(Graphics g) {
        // a lot of temporary

        for (int h = 0; h < stock.length; h ++) {
            g.drawImage(stock[h].getPicture(), stock[h].getX(), stock[h].getY(), null);
            System.out.println("Swear on my electricity I drew the " + stock[h].getType() + "!");
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
                    System.out.println("STALL NOT FOUND GODDAMMIT!!!!!");
                }
                g.drawImage(stall, x, y, null);
            }
        }
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