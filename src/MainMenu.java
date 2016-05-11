/**
 * Created by tamirsway on 2016-05-11.
 */

import java.awt.*;
import javax.swing.*;

public class MainMenu extends JPanel {

    SpringLayout layout = new SpringLayout();
    public MainMenu () {
        super(new SpringLayout());
        setLayout(layout);
        setSize(1280, 720);
        setBackground(Color.BLUE);
        prepareGUI();
    }

    private void prepareGUI () {
        JButton highscores = new JButton ("Highscores");
        JButton quit = new JButton ("Quit Game");
        JButton instructions = new JButton("Instructions");
        JButton playGame = new JButton ("Play Game");

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, playGame, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, playGame, 0, SpringLayout.HORIZONTAL_CENTER, this);
        add (playGame);
    }

}
