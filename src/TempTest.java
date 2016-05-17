import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is used for whatever testing we may need to perform :)
 *
 * Created by sketch204 on 2016-05-10.
 */
class TempTest {
    JFrame frame = new JFrame ("I'm testing!");
    SpringLayout layout = new SpringLayout();

    public void setUpFrame () {
        frame.setSize(1280, 720);
        frame.setLayout(layout);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public TempTest () {
        testing4 ();
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

    public static void main(String[] args) {
        new TempTest();
    }
}
