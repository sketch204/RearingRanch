package root;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

    public void setUpFrame () {
        frame.setSize(1280, 720);
        frame.setLayout(layout);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public TempTest () {
//        for (int h = 1; h < 5; h ++) {
//            testGetPositions(h);
//            try {Thread.sleep (10);} catch (InterruptedException e) {}
//        }
        testing5();
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

    public static void main(String[] args) {
        new TempTest();
//        ArrayList<Integer> list = new ArrayList<Integer>(new Integer [10]);
//        System.out.println(list.size());
    }
}
