import javax.swing.*;
import java.awt.*;

/**
 * This is the master frame program, it will contain all the JPanels inside it
 * Created by sketch204 on 2016-05-10.
 */
public class MasterFrame extends JFrame {
    public MasterFrame () {
        super ("Rearing Ranch");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setVisible(true);

        GameStage gm = new GameStage(0, getContentPane()) {
            @Override
            protected void generateAnimals() {

            }

            @Override
            protected boolean inputLegal() {
                return false;
            }
        };
        add(gm);
        repaint();
    }

    public static void main(String[] args) {
        new MasterFrame();
    }
}
