import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is used for whatever testing we may need to perform :)
 *
 * Created by sketch204 on 2016-05-10.
 */
public class TempTest {
    JFrame frame = new JFrame ("I'm testing!");
    SpringLayout layout = new SpringLayout();

    public void setUpFrame () {
        frame.setSize(1280, 720);
        frame.setLayout(layout);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public TempTest () {
        testing3 ();
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
        JButton button = new JButton("I'm a button");

        layout.putConstraint(SpringLayout.WEST, button, 100, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, button, 100, SpringLayout.NORTH, frame);
        frame.add(button);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TempTest();
    }
}
