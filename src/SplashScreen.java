import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    /** <b> frame</b> JFrame that contains the JPanel. */
    private JFrame frame;
    /** <b> panel</b> JPanel that contains the image JLabel. */
    private JPanel panel;
    /** <b> image</b> JLabel that contains the image gif. */
    private JLabel image;

    /** SplashScreen constructor adds the image label to the panel, which gets added to the frame. The window is
     * created and activated.
     *
     * <br> Try/Catch:
     * <br>     Used to ensure that the image is loaded correctly and does not produce errors.
     *
     * @Exception e - Exception in case error is produced.
     */
    public SplashScreen (String animation) {
        try {
            panel = new JPanel();
            frame = new JFrame();

            frame.setUndecorated(true);
            frame.setAlwaysOnTop (true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(1280, 720));
            frame.setLocationRelativeTo(null);

            panel.setSize(new Dimension(1280, 720));

            image = new JLabel(new ImageIcon("src/animations/" + animation + ".gif"));
            panel.add(image);
            frame.add (panel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "<html> Splash Screen screen gif could not be found. <br> Program closing...", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        ExecutorService executor = Executors.newCachedThreadPool();
        Timer t = new Timer() {
            @Override
            protected void time () {
                while (isAlive) {
                    if (timing)
                        time++;
                    if (time >= 600) {
                        displaySplashScreen();
                        RearingRanchDriver.launch();
                        killTimer();
                        if (animation.equals("GoodByeScreen"))
                            System.exit(0);
                    }
                    try { Thread.sleep(10);}catch(InterruptedException e) {}
                }
            }
        };
        executor.submit(t);
        if (animation.equals("GoodByeScreen"))
            RearingRanchDriver.getWindow().setVisible(false);
        displaySplashScreen();
    }

    public void displaySplashScreen() {
        frame.setVisible(!frame.isVisible());
    }
}
