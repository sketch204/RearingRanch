package root;

import root.dataclass.Animal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class is used for whatever testing we may need to perform :)
 *
 * Created by sketch204 on 2016-05-10.
 */
class TempTest extends JFrame {
    JFrame frame = new JFrame ("I'm testing!");
    SpringLayout layout = new SpringLayout();

    /**
     * The amount of stables available for the current background.
     */
    private int stablesAvailable = 0;
    /**
     * An arrays of (x,y) positions of each stable on the current background.
     */
    private Point [] stablePositions;
    private Animal [] stock = new Animal [5];
    private int difficulty = 3;
    String gameObjective;

    public void setUpFrame () {
        frame.setSize(1280, 720);
        frame.setLayout(layout);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public TempTest () {
        testTimer();
//        for (int h = 1; h < 70000; h ++) {
//            testGetPositions(h);
//            try {Thread.sleep (10);} catch (InterruptedException e) {}
//        }
//        testing5();
//        testGenerateAnimals();
    }

    public void testing2 () {
        JTextField jtf = new JTextField(" ");
        jtf.setVisible(true);
        System.out.println(jtf.getColumns());
    }

    public void testing1 () {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File ("src/pictures/Chicken-Brown.png.png"));
        } catch (IOException e) {}

        setUpFrame();

//        frame.add (new JButton ("This is text", new ImageIcon("src/pictures/Chicken-Brown.png")));
        JButton button = new JButton("I'm a button");

        button.setBackground(Color.yellow);
        button.setOpaque(true);
        button.setBorderPainted(false);

        frame.add(button);
    }

    public void testing3 () {
        setUpFrame();

//        JComponent button = new JButton(new ImageIcon ("src/pictures/InalGotov-Season.swf"));
        JTextField text = new JTextField("1GhZwWD");
        text.setFont(new Font ("Chalkboard SE", 0, 20));
        text.setBackground(new Color (255,0, 19));
        text.setForeground(new Color (255, 255, 255));
        text.setText(text.getText() + " 09 hbujdipsa");
        text.setBackground(new Color (0, 0, 0));
        text.setForeground(new Color (255, 0, 0));

//        button.setPreferredSize(new Dimension (800, 600));

//        button.addActionListener((ActionListener) RearingRanchDriver.getWindow());
//        layout.putConstraint(SpringLayout.WEST, button, 100, SpringLayout.WEST, frame);
//        layout.putConstraint(SpringLayout.NORTH, button, 100, SpringLayout.NORTH, frame);
        frame.add(text);
//        frame.add(button);
        frame.setVisible(true);
    }

    public void testing4 () {
        setUpFrame();

        JLabel label = new JLabel ("Jokerplay");
        Color colorBG = new Color (34, 34, 34), colorFG = new Color (189, 189, 189);

        label.setBackground(colorBG);
        label.setForeground(colorBG);
        label.setFont(new Font ("Chalkboard SE", 0, 20));
        label.setToolTipText("Lolzipop");
        label.setBorder(BorderFactory.createLineBorder(colorFG, 3, true));

        frame.add(label);
        frame.setVisible(true);
    }

    public void testing5 () {
        setUpFrame();
        JButton button = null;
        try {
            button = new JButton(new ImageIcon((ImageIO.read(new File ("src/pictures/buttons/difficultyChooser/EasyButton.png"))).getScaledInstance(150,35,0)));
        } catch (IOException e) {}
        frame.add(button);
        frame.setVisible(true);
    }

    private int generateBackground(int bGNum) {
//        int bGNum = (int)(Math.random() * 4) + 1;

        switch (bGNum) {
            case 1:
                stablesAvailable = 6;
                stablePositions = new Point [stablesAvailable];
                stablePositions [0] = new Point (309, 475);
                stablePositions [1] = new Point (595, 438);
                stablePositions [2] = new Point (961, 382);
                stablePositions [3] = new Point (677, 520);
                stablePositions [4] = new Point (1004, 535);
                stablePositions [5] = new Point (1279, 521);
                break;
            case 2:
                stablesAvailable = 7;
                stablePositions = new Point [stablesAvailable + 2];
                stablePositions [0] = new Point (338, 406);
                stablePositions [1] = new Point (757, 389);
                stablePositions [2] = new Point (1058, 436);
                stablePositions [3] = new Point (283, 503);
                stablePositions [4] = new Point (939, 523);
                stablePositions [5] = new Point (1279, 478);
                stablePositions [6] = new Point (580, 512);
                stablePositions [7] = new Point (1, 106);
                stablePositions [8] = new Point (864, 85);
                break;
            case 3:
                stablesAvailable = 7;
                stablePositions = new Point [stablesAvailable];
                stablePositions [0] = new Point (462, 318);
                stablePositions [1] = new Point (746, 340);
                stablePositions [2] = new Point (1052, 286);
                stablePositions [3] = new Point (1267, 416);
                stablePositions [4] = new Point (405, 480);
                stablePositions [5] = new Point (789, 525);
                stablePositions [6] = new Point (1043, 470);
                break;
            case 4:
                stablesAvailable = 7;
                stablePositions = new Point [stablesAvailable];
                stablePositions [0] = new Point (431, 237);
                stablePositions [1] = new Point (714, 282);
                stablePositions [2] = new Point (1030, 309);
                stablePositions [3] = new Point (358, 367);
                stablePositions [4] = new Point (606, 450);
                stablePositions [5] = new Point (1279, 503);
                stablePositions [6] = new Point (959, 548);
                break;
        }
        return stablesAvailable;
    }

    public Point [] getPosition (int amount) {
        if (stablesAvailable < 1) return null;
        ArrayList <Point> points = new ArrayList<Point>();
        int index = -1;
        // Initialize the ArrayList, for 'set' to work
        for (int h = 0; h < amount; h ++)
            points.add (null);
        if (stablePositions.length == 9) {
            // Will have the starter index for the returning ArrayList.
            // If request > 3 then allocate for the amount of top spaces there are
            int starter = (amount > 3) ? amount - 3 : 0;
            // Contains the returning coordinates
            points = new ArrayList<Point>(amount + starter);
            // Initialize ArrayList, for 'set' to work
            for (int h = 0; h < amount + starter; h ++)
                points.add (null);
            // Randomizes points on screen
            for (int h = starter; h < amount; h ++) { // Random > 2
                do { // Generate position                                     - 3 here to subtract the 2 last points (they're stalls).
                    index = (int) ((Math.random() * (((stablePositions.length - 3) - 3) + 1)) + 3); // Min = 3 | Max = 6
                } while (points.contains(new Point (stablePositions[index]))); // Repetition checker
                points.set (h, new Point (stablePositions[index]));
            }
            // Generate for the allocated spaces, If none exist, then will not run.
            for (int h = 0; h < amount - 3; h++) {
                int counter = 0;
                do { // Generate position
                    index = (int) (Math.random() * 3); // Min = 0 | Max = 2
                } while (points.contains(new Point (stablePositions[index])));
                points.set (h, new Point(stablePositions[index]));
                if (index == 0)
                    points.set (h + amount, new Point(stablePositions[stablePositions.length - 2]));  // Allocate left stable
                else if (index == 1)
                    points.set (h + amount, new Point (-1,-1));  // Center position, no stable needed.
                else if (index == 2)
                    points.set (h + amount, new Point(stablePositions[stablePositions.length - 1]));  // Allocate right stable
            }
        } else {  // If not second background
            int min = 0, max = stablePositions.length - 1, amountTop = 1, amountBottom = 0;
            // Default run (amount == 1): Random position on screen.
            if (amount > 1) // Case (amount): 2, 3, 4, 5, 6
                max = 2;
            if (amount == 2 || amount == 3) { // Case (amount): 2, 3
                amountBottom = amount - 1; // 1 || 2
            } else if (amount == 4 || amount == 5) { // Case (amount): 4, 5
                amountTop = 2;
                amountBottom = 2;
            } if (amount > 4) { // Case (amount): 5, 6
                amountBottom = 3;
            } if (amount == 6) { // Case (amount): 6
                amountTop = 3;
            }
            // Generate for requested amount of positions from top row.
            for (int h = 0; h < amountTop; h++) {
                do {
                    index = (int) ((Math.random() * ((max - min) + 1)) + min);
                } while (points.contains(new Point (stablePositions[index])));
                points.set (h, new Point (stablePositions [index]));
            }
            // Generate for requested amount of positions from bottom rows.
            for (int h = amountTop; h < amountTop + amountBottom; h++) {
                do {
                    index = (int) ((Math.random() * (((stablePositions.length - 1) - 3) + 1)) + 3);
                } while (points.contains(new Point (stablePositions[index])));
                points.set (h, new Point (stablePositions [index]));
            }
        }
        ArrayList<Point> tempHolder = new ArrayList<>(points);
        for (int h = 0; h < points.size(); h ++) {
            if (tempHolder.subList(h+1, tempHolder.size()).contains(points.get(h))) {
                System.out.println("Fucking McFuck Fuck!!! FuckNugget");
                System.exit (270);
            }
        }
        return points.toArray(new Point [points.size()]);
    }

    public void testGetPositions (int bGNum) {
        generateBackground(2);
        Point[] p;
        for (int k = 0; k <= 5000; k ++) {
            for (int h = 1; h < 7; h++) {
                p = getPosition(h);
                for (int j = 0; j < p.length; j++)
                    System.out.print("(" + p[j].x + ", " + p[j].y + ")");
                System.out.println();
            }
            try {Thread.sleep (1);} catch (InterruptedException e) {}
        }
    }

    protected void generateAnimals () {
        // {Chicken, goose, sheep, horse, cow, goat}
        String [] [] animalColors = {{"Chicken", "Brown", "White"}, {"Goose", "Brown", "White"}, {"Sheep", "Brown", "White"}, {"Horse", "Black", "White", "Brown"},
                {"Cow", "BlackOn-Brown", "BlackOn-White", "BrownOn-White", "WhiteOn-Black", "WhiteOn-Brown"}, {"Goat", "Brown", "White", "Gray"}};
        // If difficulty != 1 -> Min: 3-4, Max: 6 || If difficulty == 1 -> Min: 1, Max: 6
        int [] animalsChosen = new int [(difficulty == 2) ? (int)((Math.random() * ((6 - (6 - 2)) + 1)) + (6 - 2)) : (int)((Math.random() * ((6 - (6 - 3)) + 1)) + (6 - 3))];
        int starter = 0, priorityAnimal = (difficulty == 2) ? (int)(Math.random()*6) : -1;

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
//                stock[h] = new Animal(animalColors[animalsChosen[h]][index], animalColors[animalsChosen[h]][0], p[h], p[h + stock.length - 1]); // Create new Animal
                stock[h] = new Animal(animalColors[animalsChosen[h]][index], animalColors[animalsChosen[h]][0], new Point (0 ,0), null); // Create new Animal
                // Animal Counter here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
            starter = p.length - stock.length; // Set starter, so that the loop below skips what has been assigned already
        }
        // Generate the rest of the animals.
        for (int h = starter; h < stock.length; h++) {
            int index = (int)(Math.random()*(animalColors[animalsChosen[h]].length - 1))+1; // Generate Random Color
//            stock[h] = new Animal(animalColors[animalsChosen[h]][index], animalColors[animalsChosen[h]][0], p[h]); // Create new Animal
            stock[h] = new Animal(animalColors[animalsChosen[h]][index], animalColors[animalsChosen[h]][0], new Point (0, 0)); // Create new Animal
            // Animal Counter here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }
    }

    public void testGenerateAnimals () {
        generateAnimals();
        for (int h = 0; h < stock.length; h ++)
            System.out.println(stock[h].toString());
        System.out.println(stock.length);
    }

    private int generateResult (String priority) {
        // Something's not right here
        // It returned a 0
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
            int objective = (int) (Math.random() * 3) + 1;
            int total = (int)((Math.random() * ((20 - 10) + 1)) + 10);

            if (objective == 1) {
                gameObjective = "If here we have " + stock.length + " animals, and in another barn we have " + (total - stock.length) + " animals, how many animals do we have in total?";
                return total;
            } else if (objective == 2) {
                gameObjective = "If here we have " + stock.length + " animals, and in another barn we have " + (total - stock.length) + " animals how many more animals do we have in the other barn?";
                return (total - stock.length) - stock.length;
            } else if (objective == 3) {
                gameObjective = "If we have " + total + " animals in total, and we can see " + stock.length + " animals here, how many animals do we have in the other barn?";
                return total - stock.length;
            }
        }
        return 0;
    }

    public void testgenerateResults () {
        System.out.println(generateResult("Horse"));
        System.out.println(gameObjective);
    }

    public void testTimer () {
        setUpFrame();
//        int max = 610;

        System.out.println("Well, I started");
        Timer timer = new Timer ();

        frame.add(timer.getVisual());

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(timer);

        frame.setVisible(true);

        while (true) {
            if (timer.getTime() == 1000) {
                break;
            }
        }

//        while (true) {
//            try { Thread.sleep (10); } catch (InterruptedException e) {}
//            System.out.println(timer.getTime());
//            if (timer.getTime() >= 600 && timer.getTime() <= max) {
//                timer.pauseTimer();
//                max = (int) timer.getTime() - 1;
//                for (int h = 1; h <= 3; h ++) {
//                    System.out.println(h);
//                    try {Thread.sleep(1000);}catch(InterruptedException e) {}
//                }
//                timer.continueTimer();
//            }
//        }
    }

    public static void main(String[] args) {
//        new TempTest();
//        System.out.println(System.currentTimeMillis());
//        long current = System.currentTimeMillis();
//        try {Thread.sleep(6000);} catch (InterruptedException e) {}
//        System.out.println(System.currentTimeMillis() - current);
//        System.out.println(System.currentTimeMillis());
//        System.out.println(System.currentTimeMillis());
    }
}
