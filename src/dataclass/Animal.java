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
 *       Tamir: 0
 *       Inal: 0.5
 */
public class Animal {
    private final String type, color;
    private BufferedImage picture;

    /**
     * Creates an instance of this class and assigns the type and color of this animal
     * as well as determines the appropriate picture file for this animal.
     * @param color The color to be assigned to this animal.
     * @param type The type that this animal will take.
     */
    public Animal (String color, String type) {
        this.color = color;
        this.type = type;
        try {
            picture = ImageIO.read(new File("src/pictures/animals/" + type + "-" + color + ".png"));
        } catch (IOException e) {}
    }

    /**
     * Returns the color of this animal.
     * @return return the color of this animal.
     */
    public String getColor () {
        return color;
    }

    /**
     * Returns the type that this animal belongs to.
     * @return The type that this animal belongs to.
     */
    public String getType () {
        return type;
    }

    /**
     * Retruns the picture file that is associated with this animal.
     * @return The BufferedImage that is associated with this animal.
     */
    public BufferedImage getPicture () {
        return picture;
    }
}

