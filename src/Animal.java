import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * The Animal class contains information on a single instance of an animal. The information
 * includes things such as the type of animal, its color as well as its associated picture file.
 *
 * @author Inal Gotov.
 * @version 1.1, 2016-05-09.
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir: 0:00
 *       Inal: 1:00
 */
public class Animal {
    /** Holds the type of this animal. */
    private String type;
    /** Holds the color of this animal. */
    private String color;
    /** Holds the on-screen (x, y) location of this animal. */
    private Point animal;
    /** Holds the on-screen (x, y) location of this animal's stall. If set to (-1, -1) then no stall is associated with this animal. */
    private Point stall = new Point (-1, -1);
    /** Holds the image file associated with this animal. */
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

    /**
     * Returns the type of this animal.
     * @return The type of this animal.
     * */
    public String getType() {
        return type;
    }

    /** Reformats the position of this animal, by moving the drawing point in its appropriate, diagonally opposite location. */
    private Point setPosition (Point oldPosition) {
        return new Point (oldPosition.x - picture.getWidth(), oldPosition.y - picture.getHeight());
    }

    /**
     * States whether a stall is needed for this animal.
     * @return Returns true if, and only if, a stall location has been set for this animal
     * */
    public boolean stallNeeded () {
        return stall.x != -1;
    }

    /**
     * Returns the x position of this animal.
     * @return The x position of this animal.
     */
    public int getY() {
        return animal.y;
    }

    /**
     * Returns the y position of this animal.
     * @return The y position of this animal.
     */
    public int getX () {
        return animal.x;
    }

    /**
     * Returns the image file that is associated with this animal.
     * @return The BufferedImage that is associated with this animal.
     */
    public BufferedImage getPicture () {
        return picture;
    }

    /**
     * Formulates and returns a string representation of this class.
     * @return A string representation of this class.
     * */
    @Override
    public String toString() {
        return "This is a " + color + " " + type;
    }
}

