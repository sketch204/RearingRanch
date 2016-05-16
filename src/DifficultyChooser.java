import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

/**
 * DifficultyChooser class will display the user with three difficulty levels: easy, medium, and hard, that they must choose
 * from. Once they choose, the game will proceed.
 *
 * @author Tamir Arnesty
 * @version 1 2016-05-15
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir: 0.5
 *       Inal: 0.5
 */
public class DifficultyChooser extends JPanel implements ActionListener {
    private JButton button = new JButton ("I'm a button");

    public DifficultyChooser () {
        prepareGUI ();
    }

    private void prepareGUI () {
        button.addActionListener(this);
        add (button);
    }

    public void initiatePlay (int difficulty) {
        int currentStage = 0;
        GameStage [] stages = {new ColorChooser(difficulty), new AnimalClassifier(difficulty), new Arithmetics(difficulty)};
        System.out.println(stages[0].getHeight());
        System.out.println(RearingRanchDriver.getWindow().getHeight());
        RearingRanchDriver.getWindow().setPanel(stages[0], "Choose the Colour!");
        while (true) {
            if (!stages[0].isActive() && currentStage == 0) {
                RearingRanchDriver.getWindow().setPanel(stages[1], "What's the Animal?");
                currentStage ++;
            } else if (!stages[1].isActive() && currentStage == 1) {
                RearingRanchDriver.getWindow().setPanel(stages[2], "Count them up!");
                currentStage ++;
            } else if (!stages[2].isActive())
                break;
        }
        // I'm thinking show highscores?
        // i think we should discuss this class
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(button))
            initiatePlay(0);
    }
}
