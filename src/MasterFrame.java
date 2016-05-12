import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the master frame program, it will contain all the JPanels inside it
 * Created by sketch204 on 2016-05-10.
 */
public class MasterFrame extends JFrame implements ActionListener {
    public MasterFrame () {
        super ("Rearing Ranch");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       // setLayout(new FlowLayout());

        MainMenu m = new MainMenu();
        SoftwareNameApp s = new SoftwareNameApp();
        GameStage gm = new GameStage(0, getContentPane()) {
            @Override
            protected void generateAnimals() {

            }

            @Override
            protected boolean inputLegal() {
                return false;
            }
        };
        add(m);
        setVisible(true);
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Highscores")) {

        } else if (ae.getActionCommand().equals("Instructions")) {

        } else if (ae.getActionCommand().equals("")) {

        }
    }
}
