import game.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * DifficultyChooser class will display the user with three difficulty levels: easy, medium, and hard, that they must choose
 * from. Once they choose, the game will proceed.
 *
 * @author Tamir Arnesty
 * @version 1 2016-05-15
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir: 0:30
 *       Inal: 0:30
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
        GameStage[] stages = {new ColorChooser(difficulty), new AnimalClassifier(difficulty), new Arithmetics(difficulty)};
        RearingRanchDriver.getWindow().setPanel(stages[0], "Choose the Colour!");

        // This loop is the reason that nothing showed up when you initiated play
        // The program focused on the loop and waited until it would finish with the loop
        // before displaying and handling the window.

        // I'm thinking, having a background thread that would do that loop and somehow have
        // it switching the stages.

//        while (true) {
//            if (!stages[0].isActive() && currentStage == 0) {
//                RearingRanchDriver.getWindow().setPanel(stages[1], "What's the animal?");
//                currentStage ++;
//            } else if (!stages[1].isActive() && currentStage == 1) {
//                RearingRanchDriver.getWindow().setPanel(stages[2], "Count them up!");
//                currentStage ++;
//            } else if (!stages[2].isActive())
//                break;
//        }

        // I'm thinking show highscores?
        // i think we should discuss this class
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(button))
            initiatePlay(0);
    }
}
