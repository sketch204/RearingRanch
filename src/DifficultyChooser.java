import com.sun.corba.se.impl.protocol.INSServerRequestDispatcher;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import game.*;
import javax.swing.*;
import java.awt.*;
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
 *       Tamir: 1:30
 *       Inal: 0:30
 */
public class DifficultyChooser extends JPanel implements ActionListener {

    private JButton easy = new JButton ("Easy");
    private JButton medium = new JButton ("Medium");
    private JButton hard = new JButton ("Hard");
 //   private JButton button = new JButton ("I'm a button");
    /** <br> <b> layout </b> Instance of LayoutManager SpringLayout is used to organize GUI Components onto the screen. */
    private SpringLayout layout = new SpringLayout();
    public DifficultyChooser () {
        super();
        setLayout(layout);
        setSize(1280, 720);
        prepareGUI();
    }

    private void prepareGUI () {
        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);

        easy.setPreferredSize(MainMenu.buttonSize);
        medium.setPreferredSize(MainMenu.buttonSize);
        hard.setPreferredSize(MainMenu.buttonSize);


        layout.putConstraint(SpringLayout.NORTH, easy, 200, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, easy, 130, SpringLayout.WEST, this);
        add (easy);

        layout.putConstraint(SpringLayout.NORTH, medium, 0, SpringLayout.NORTH, easy);
        layout.putConstraint(SpringLayout.WEST, medium, 20, SpringLayout.EAST, easy);
        add (medium);

        layout.putConstraint(SpringLayout.NORTH, hard, 0, SpringLayout.NORTH, medium);
        layout.putConstraint(SpringLayout.WEST, hard, 20, SpringLayout.EAST, medium);
        add (hard);

        Instructions.mainMenu.setPreferredSize(new Dimension(320, 80));

        layout.putConstraint(SpringLayout.SOUTH, Instructions.mainMenu, -200, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST,  Instructions.mainMenu, 400, SpringLayout.WEST, this);
        add(Instructions.mainMenu);

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
        if (e.getSource().equals(easy))
            initiatePlay(0);
    }
}
