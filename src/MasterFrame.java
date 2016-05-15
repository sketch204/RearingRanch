import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
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
    Container current = new Panel();
    //MainMenu m = new MainMenu();

    public MasterFrame () {
        super ("Rearing Ranch");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        MainMenu m = new MainMenu();
        GoodByeScreen s = new GoodByeScreen();
        /*GameStage gm = new GameStage(0) {

        GameStage gm = new GameStage(0, getContentPane()) {
            @Override
            protected void generateAnimals() {

            }

            @Override
            protected boolean inputLegal() {
                return false;
            }
        };*/

        current = new MainMenu();
        /*new MainMenu().playGame.addActionListener(this);
        new MainMenu().instructions.addActionListener(this);
        new MainMenu().highscores.addActionListener(this);
        new MainMenu().quit.addActionListener(this);*/
        add(current);
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
            remove(current);
            add(new Instructions());
        } else if (ae.getActionCommand().equals("Quit Game")) {
            System.exit(0);
        }
        revalidate();
        repaint();
    }
}
