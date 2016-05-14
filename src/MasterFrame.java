import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the master frame program, it will contain all the JPanels inside it
 *
 * @author Inal Gotov, modified by Tamir Arnesty.
 * @version 1.2, 2016-05-10.
 */
public class MasterFrame extends JFrame implements ActionListener {

    /**
     * Creates an instance of a JFrame starting with a SplashScreen.
     */
    public MasterFrame () {
        super ("Rearing Ranch");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       // setLayout(new FlowLayout());

        MainMenu m = new MainMenu();
        GoodByeScreen s = new GoodByeScreen();
        GameStage gm = new GameStage(0) {
            @Override
            protected void generateAnimals() {

            }

            @Override
            protected boolean inputLegal() {
                return false;
            }
        };
        add(s);
        setVisible(true);
        revalidate();
        repaint();
    }

    /**
     * Handles all the button clicks and ques each JPanel change.
     * @param ae Holds the value of the button that was clicked.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Highscores")) {

        } else if (ae.getActionCommand().equals("Instructions")) {

        } else if (ae.getActionCommand().equals("")) {

        }
    }
}
