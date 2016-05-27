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
 * </p>
 * <b>type </b> This holds the value of the type of this animal.
 * </p>
 * <b>color </b> This holds the value of the color of this animal.
 * </p>
 * <b>picture </b> This holds an instance of a File class which leads to this animal's associated picture file.
 *
 * @author Inal Gotov.
 * @version 1.1, 2016-05-09.
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir: 0:00
 *       Inal: 1:00
 */
public class Animal {
    private int x, y, x2 = -1, y2 = -1;
    private BufferedImage picture;

    /**
     * Creates an instance of this class and assigns the type and color of this animal
     * as well as determines the appropriate picture file for this animal.
     * It also sets the position of the animal at which it should be drawn.
     * @param color The color to be assigned to this animal.
     * @param type The type that this animal will take.
     * @param x The x coordinate at which the animal will be drawn.
     * @param y The y coordinate at which the animal will be drawn.
     */
    public Animal (String color, String type, int x, int y) {
        String filepath = "src/pictures/animals/" + type + "-" + color + ".png";
        try {
            picture = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            System.out.println("There's an error here.");
            System.out.println(filepath);
            e.printStackTrace();
            System.exit(0);
        }
        this.x = setX(x);
        this.y = setY(y);
    }

    /**
     * Creates an instance of this class and assigns the type and color of this animal
     * as well as determines the appropriate picture file for this animal.
     * It also sets the position of the animal at which it should be drawn as well as
     * the position of stall that should be drawn on top of it.
     * @param color The color to be assigned to this animal.
     * @param type The type that this animal will take.
     * @param x The x coordinate at which the animal will be drawn.
     * @param y The y coordinate at which the animal will be drawn.
     * @param x2 The x coordinate at which the stall will be drawn.
     * @param y2 The y coordinate at which the stall will be drawn.
     */
    public Animal (String color, String type, int x, int y, int x2, int y2) {
        this.x2 = x2;
        this.y2 = y2;
        String filepath = "src/pictures/animals/" + type + "-" + color + ".png";
        try {
            picture = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            System.out.println("There's an error here.");
            System.out.println(filepath);
            e.printStackTrace();
            System.exit(0);
        }
        this.x = setX(x);
        this.y = setY(y);
    }

    private int setX (int x) {
        return x - picture.getWidth();
    }

    private int setY (int y) {
        return y - picture.getHeight();
    }

    public boolean stallNeeded () {
        return x2 != -1 || y2 != -1;
    }

    public int getY() {
        return y;
    }

    public int getX () {
        return x;
    }

    /**
     * Retruns the picture file that is associated with this animal.
     * @return The BufferedImage that is associated with this animal.
     */
    public BufferedImage getPicture () {
        return picture;
    }
}

