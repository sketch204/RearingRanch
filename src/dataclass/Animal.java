package root.dataclass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * The dataclass.Animal class contains information on a single instance of an stock. The information
 * includes things such as the type of animal, its color as well as its associated picture file.
 *
 * <b>Global Variables </b>
 * <br><b>type </b> This holds the value of the type of this animal.
 * <br><b>color </b> This holds the value of the color of this animal.
 * <br><b>picture </b> This holds an instance of a File class which leads to this animal's associated picture file.
 *
 * @author Inal Gotov.
 * @version 1.1, 2016-05-09.
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir: 0:00
 *       Inal: 1:00
 */
public class Animal {

    private String type, color;
    private Point animal, stall = new Point (-1, -1);
    private BufferedImage picture;

    /**
     * Creates an instance of this class and assigns the type and color of this animal
     * as well as determines the appropriate picture file for this animal.
     * It also sets the position of the animal at which it should be drawn.
     * @param color The color to be assigned to this animal.
     * @param type The type that this animal will take.
     * @param animal The position of the animal on screen.
     */
    public Animal (String color, String type, Point animal) {
        this.color = color;
        this.type = type;
        String filepath = "src/pictures/animals/" + type + "-" + color + ".png";
        try {
            picture = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            System.out.println("There's an error here.");
            System.out.println(filepath);
            e.printStackTrace();
            System.exit(0);
        }
        this.animal = setPosition(animal);
    }

    /**
     * Creates an instance of this class and assigns the type and color of this animal
     * as well as determines the appropriate picture file for this animal.
     * It also sets the position of the animal at which it should be drawn as well as
     * the position of stall that should be drawn on top of it.
     * @param color The color to be assigned to this animal.
     * @param type The type that this animal will take.
     * @param animal The position of the animal on screen.
     * @param stall The position of the stall on screen.
     */
    public Animal (String color, String type, Point animal, Point stall) {
        this.color = color;
        this.type = type;
        this.stall = stall;
        String filepath = "src/pictures/animals/" + type + "-" + color + ".png";
        try {
            picture = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            System.out.println("There's an error here.");
            System.out.println(filepath);
            e.printStackTrace();
            System.exit(0);
        }
        this.animal = setPosition(animal);
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    private Point setPosition (Point oldPosition) {
        return new Point (oldPosition.x - picture.getWidth(), oldPosition.y - picture.getHeight());
    }

    public boolean stallNeeded () {
        return stall.x != -1;
    }

    public int getY() {
        return animal.y;
    }

    public int getX () {
        return animal.x;
    }

    /**
     * Retruns the picture file that is associated with this animal.
     * @return The BufferedImage that is associated with this animal.
     */
    public BufferedImage getPicture () {
        return picture;
    }

    @Override
    public String toString() {
        return "This is a " + color + " " + type;
    }
}

