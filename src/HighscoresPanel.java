import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.io.*;
import java.util.ArrayList;

/**
 * The HighscoresPanel class displays the top 10 entries of an ArrayList of Player
 * with their name, difficulty level, time, and score onto the screen. Button handling and choices
 *
 * @author Tamir Arnesty
 * @version 1 2016-05-30
 * Last Edited: 2016-05-30
 * Hours since 2016-05-11:
 *       Tamir: 10:00
 *       Inal: -
 */
public class HighscoresPanel extends JPanel implements ActionListener, KeyListener, Printable{

    /** <b> display </b> */
    private static boolean display = false;
    /** <b> layout </b> Instance of LayoutManager SpringLayout is used to organize GUI Components onto the screen. */
    private SpringLayout layout = new SpringLayout();
    /** <b> levelChoices </b> JButton array to store buttons to view each difficulty's leaderboard. */
    private JButton [] levelChoices = new JButton[3];
    /** <b> options </b> JButton array to store buttons to print, clear highscores, or return to Main Menu. */
    private JButton [] options = new JButton[3];
    /** <b> index </b>  Stores the current index of the button in focus. */
    private int index = 0;

    private static ArrayList<Player> displayList;

    /** HighscoresPanel constructor creates a window that is 720p standard and sets the layout to SpringLayout, invokes
     * prepareGUI and display methods to load information and componennts onto the screen. If the highscores file does not
     * exist, it is created. */
    public HighscoresPanel () {
        super ();

        setLayout(layout);
        setSize(1280, 720);

        System.out.println("--------------------" + Highscores.scores.exists());
        if (!Highscores.scores.exists()) {
            System.out.println("after check" + Highscores.scores.exists());
            Highscores.create(); }

        if (Highscores.scores.exists()) {
            System.out.print("hohohohhohhoho");
        System.out.print("created file"); }

        prepareGUI();
        display(1, false);
    }

    /** Method prepareGUI adds all JComponents onto the screen and sets their appearance preferences, event listeners, and
     * ToolTips.
     *
     * <br> <b> For Loops: </b>
     * <br> 1st: Adds event listeners, sets borders and ToolTip text, and sets appearance for the window buttons.
     * <br> 2nd: Adds the first and last button in the levelChoices array to the screen.
     *
     */
    private void prepareGUI() {
        /** Text for button ToolTip that displays shortcuts for each button. */
        String [] shortcuts = {"Press e for easy.", "Press m for medium.", "Press h for hard.", "Press c to clear high scores.", "Press r to return to Main Menu.", "Press p to print."};

        try {
            levelChoices = new JButton[]{new JButton(new ImageIcon((ImageIO.read(new File("src/pictures/buttons/difficultyChooser/EasyButton.png"))).getScaledInstance(98, 22, 0))),
                    new JButton(new ImageIcon((ImageIO.read(new File("src/pictures/buttons/difficultyChooser/MediumButton.png"))).getScaledInstance(98, 22, 0))),
                    new JButton(new ImageIcon((ImageIO.read(new File("src/pictures/buttons/difficultyChooser/HardButton.png"))).getScaledInstance(98, 22, 0)))};
        } catch (IOException | NullPointerException e) {
        }

        try {
            options = new JButton[] {new JButton(new ImageIcon((ImageIO.read(new File("src/pictures/buttons/highscores/delete.png"))))),
                    new JButton(new ImageIcon((ImageIO.read(new File("src/pictures/buttons/MainMenuButton.png"))).getScaledInstance(150, 35, 0))),
                    new JButton(new ImageIcon((ImageIO.read(new File("src/pictures/buttons/highscores/printer.png")))))};
        } catch (IOException e) {
            e.printStackTrace();
        }

        levelChoices[0].requestFocus();
        layout.putConstraint(SpringLayout.NORTH, levelChoices[1], 200, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, levelChoices[1], 0, SpringLayout.HORIZONTAL_CENTER, this);
        add(levelChoices[1]);

        for (int i = 0; i < levelChoices.length; i++) {
            options[i].addActionListener(this);
            options[i].addKeyListener(MainMenu.enter);
            options[i].addKeyListener(this);
            options[i].setToolTipText(shortcuts[i+3]);
            options[i].setContentAreaFilled(true);
            options[i].setBorder(BorderFactory.createEmptyBorder());
            levelChoices[i].addActionListener(this);
            levelChoices[i].addKeyListener(MainMenu.enter);
            levelChoices[i].addKeyListener(this);
            levelChoices[i].setToolTipText(shortcuts[i]);
            levelChoices[i].setContentAreaFilled(true);
            levelChoices[i].setBorder(BorderFactory.createEtchedBorder());
        }

        options[1].setBorder(BorderFactory.createEtchedBorder());

        for (int i = 0; i < levelChoices.length; i+= 2) {
            layout.putConstraint(SpringLayout.NORTH, levelChoices[i], 200, SpringLayout.NORTH, this);
            if (i == 0)
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, levelChoices[i], -180, SpringLayout.HORIZONTAL_CENTER, levelChoices[1]);
            else
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, levelChoices[i], 180, SpringLayout.HORIZONTAL_CENTER, levelChoices[1]);
            add (levelChoices[i]);
        }

        layout.putConstraint(SpringLayout.SOUTH, options[0], -50, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, options[0], 200, SpringLayout.HORIZONTAL_CENTER, this);
        add(options[0]);
        layout.putConstraint(SpringLayout.SOUTH, options[1], -50, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, options[1], 0, SpringLayout.HORIZONTAL_CENTER, this);
        add(options[1]);
        layout.putConstraint(SpringLayout.SOUTH, options[2], -50, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, options[2], -200, SpringLayout.HORIZONTAL_CENTER, this);
        add(options[2]);

        revalidate();
        repaint();
    }

    @Override
    public void paintComponent (Graphics g) {

        g.drawImage(MainMenu.getBG(), 0, 0, null);
        g.drawImage(MainMenu.getImage("GameLogo"), 380, 0, null);
        g.drawImage(MainMenu.getImage("Company Logo Scaled").getScaledInstance(75, 75, 0), -5, 5 , null);
        g.drawImage(MainMenu.getImage("Logo Name").getScaledInstance(85, 41, 0), this.getWidth()-90, 5, null);

        if (MainMenu.background.getPath().contains("background1"))
            g.setColor(new Color (255, 255, 255));
        else if ((MainMenu.background.getPath().contains("background2")))
            g.setColor(new Color(43, 167, 133));
        else if (MainMenu.background.getPath().contains("background3"))
            g.setColor(new Color (164, 0, 3));
        else
            g.setColor(new Color(0, 0, 0));

        String difficulty = "Easy";
        if (levelChoices[2].isFocusOwner() || (!displayList.isEmpty() && displayList.get(0).getDifficulty() == 1))
            difficulty = "Hard";
        else if (levelChoices[1].isFocusOwner() || (!displayList.isEmpty() && displayList.get(0).getDifficulty() == 2))
            difficulty = "Medium";
        else {
            if (levelChoices[0].isFocusOwner() || (!displayList.isEmpty() && displayList.get(0).getDifficulty() == 3))
                difficulty = "Easy";
        }

        g.setFont(new Font ("Times New Roman", Font.BOLD, 15));
        g.drawString("You are currently viewing the leaderboard for:", 70, 300);
        g.setFont(new Font ("Times New Roman", Font.ITALIC, 15));
        g.drawString (difficulty, 170, 320);

        g.setFont(new Font ("Times New Roman", Font.BOLD, 20));
        g.drawString("Name", 425, 255);
        g.drawString("Score", 615, 255);
        if (display) {
            for (int i = 0; i < 10; i++) {
                if (i < displayList.size()) {
                    g.drawString("" + (i + 1) + ". ", 390, 280 + i * 30);
                    g.drawString(displayList.get(i).getName(), 425, 280 + i * 30);
                    g.drawString(displayList.get(i).getFormattedTime(), 615, 280 + i * 30);
                    g.drawString(Integer.toString(displayList.get(i).getDifficulty()), 815, 280 + i * 30);
                }
            }
        }
        revalidate();
        repaint();
    }

    /** Method display loads
     * @param difficulty The difficulty of the scoreboard to display.
     */
    public static void display (int difficulty, boolean start) {
        if (start)
            Highscores.write();
        Highscores.load();
        displayList = Highscores.view(difficulty);
        display = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(levelChoices[0])) {
            display(1, false);
            levelChoices[0].requestFocusInWindow();
        } else if (e.getSource().equals(levelChoices[1])) {
            display(2, false);
            levelChoices[1].requestFocusInWindow();
        } else if (e.getSource().equals(levelChoices[2])) {
            display(3, false);
            levelChoices[2].requestFocusInWindow();
        } else if (e.getSource().equals(options[0])) {
            Highscores.delete();

        } else if (e.getSource().equals(options[1])) {
            RearingRanchDriver.getWindow().setPanel(MasterFrame.getM(), "Rearing Ranch");
        } else if (e.getSource().equals(options[2])) {
            printDialog();
        }
        revalidate();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if (index < levelChoices.length) {
                    index++;
                    levelChoices[index].requestFocusInWindow();
                }
                else {
                    index = 0;
                    levelChoices[index].requestFocusInWindow();
                }
            case KeyEvent.VK_LEFT:
                if (index < 0) {
                    index--;
                    levelChoices[index].requestFocusInWindow();
                } else {
                    index = levelChoices.length;
                    levelChoices[index].requestFocusInWindow();
                }
            case KeyEvent.VK_E:
                levelChoices[0].doClick();
                break;
            case KeyEvent.VK_M:
                levelChoices[1].doClick();
                break;
            case KeyEvent.VK_H:
                levelChoices[2].doClick();
                break;
            case KeyEvent.VK_P:
                options[2].doClick();
            case KeyEvent.VK_C:
                options[0].doClick();
            case KeyEvent.VK_R:
                options[1].doClick();
            default:
                    break;
        }
        paintComponent(this.getGraphics());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public void printDialog () {
        PrinterJob print = PrinterJob.getPrinterJob();
        print.setPrintable(this);
        try {
            print.print();
        } catch (PrinterException ex) {      System.out.println ("err");
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0)
            return NO_SUCH_PAGE;

        g.drawImage(MainMenu.getImage("GameLogo").getScaledInstance(183, 65, 0), 213, 20, null);
        g.drawImage(MainMenu.getImage("Company Logo Scaled").getScaledInstance(75, 75, 0), 10, 20 , null);
        g.drawImage(MainMenu.getImage("Logo Name").getScaledInstance(85, 41, 0), (int) pf.getWidth() - 120, 20, null);
        g.setFont(new Font ("Times New Roman", Font.BOLD, 20));

        g.setColor(new Color (217, 58, 68));
        g.drawString ("High Scores", 250, 100);
        g.setFont(new Font ("Times New Roman", Font.BOLD, 12));
        g.drawString("Name", 190, 255);
        g.drawString("Score", 285, 255);
        g.drawString("Difficulty", 375, 255);
            for (int i = 0; i < 10; i++) {
                if (i < Highscores.players.size()) {
                    g.drawString("" + (i + 1) + ". ", 170, 280 + i * 30);
                    g.drawString(Highscores.players.get(i).getName(), 190, 280 + i * 30);
                    g.drawString(Highscores.players.get(i).getFormattedTime(), 290, 280 + i * 30);
                    g.drawString(Integer.toString(Highscores.players.get(i).getDifficulty()), 395, 280 + i * 30);
                }
            }
        return PAGE_EXISTS;
    }
}
