package root;

import root.game.GameStage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sketch204 on 2016-05-26.
 */
public class RearingRanchTester {

    public RearingRanchTester () {

    }

    public void testGetPosition () {
        GameStage gs = new GameStage() {
            @Override
            protected void createAnimals(Graphics g) {

            }

            @Override
            protected void generateAnimals() {

            }

            @Override
            protected ImageIcon[] generateButtons() {
                buttons = new JButton[1];
                ImageIcon [] icons = {new ImageIcon ("src/pictures/buttons/stage1/Icon-Black.png")};
                return icons;
            }

            @Override
            protected void inputLegal() {

            }
        };
        gs.setVisible(false);
        for (int h = 0; h < 5; h ++) {
            for (int j = 1; j <= 4; j ++) {
                // Test if your super ingenious algorithm works
                // Its not that stables is 0
                // I'm sleepy
//                System.out.println(gs.testGetPosition(j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        RearingRanchTester rrt = new RearingRanchTester();
        rrt.testGetPosition();
    }
}
