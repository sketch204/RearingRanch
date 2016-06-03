package root;

import javax.swing.*;
import java.awt.*;

/**
 * The SplashScreen class creates and adds an animated image to a panel, which is added to a frame, and plays the gif.
 * Once completed, the game is activated.
 *
 * @author Tamir Arnesty
 * @version 1 2016-06-02.
 *
 * Last Edited: 2016-06-02
 * Hours since 2016-06-02:
 *       Tamir: 0:45 (as of 11 am on date of created)
 *       Inal: -
 */
public class SplashScreen{

    /** <b> animation </b> Stores the name of the animation to determine when to quit. */
    private String animation;
    /** <b> frame </b>JFrame that contains the JPanel. */
    private JFrame frame;
    /** <b> panel </b> JPanel that contains the image JLabel. */
    private JPanel panel;
    /** <b> image </b> JLabel that contains the image gif. */
    private JLabel image = new JLabel();
    /** <b> duration </b>The duration for the gif to be displayed. */
    private int duration = 9000;
    /** <b> startTime </b> The system time in milliseconds when the splash screen begins playing. */
    private long startTime = 0;


    /** SplashScreen constructor adds the image label to the panel, which gets added to the frame. The window is
     * created and activated.
     *
     * <br> Try/Catch:
     * <br>     Used to ensure that the image is loaded correctly and does not produce errors.
     *
     * @Exception e - Exception in case error is produced.
     */
    public SplashScreen (String animation) {
        this.animation = animation;
        try {
            startTime = System.currentTimeMillis();
            frame = new JFrame();
            frame.setUndecorated(true);
            frame.setAlwaysOnTop (true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel = (JPanel) frame.getContentPane();
            frame.setSize(new Dimension(1280, 720));
            image.setIcon(new ImageIcon("src/animations/" + animation + ".gif"));
            panel.add(image);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "<html> Splash Screen screen gif could not be found. <br> Program closing...", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        doSplashScreen();
    }



    /** doSplashScreen runs the animation for the specified duration.
     *
     * <br> <b> If Statements: </b>
     * <br>     1st: Determines when the time is greater than the duration to exit the loop
     *
     * <br> <b> Loops: </b>
     * <br> <b> While Loop: </b>  used to run the animation for the specified duration.
     */
    public void doSplashScreen()
    {
        while(true)
        {
            if(System.currentTimeMillis() - startTime >= duration)
                break;
            panel.repaint();
        }
        frame.setVisible(false);
        if (!animation.equals("GoodByeScreen")) {
            new RearingRanchDriver();
            startTime = 0;
        }
        else
            System.exit(0);
    }
}
