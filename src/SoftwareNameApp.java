import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author tamirsway
 */
public class SoftwareNameApp extends JFrame implements ActionListener {

    Timer t = new Timer(125, new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            alpha -= 0.05f; // decrements the fade
            if (alpha <= 0) {
                alpha = 0;
                t.stop();
            }
            repaint();

        }
    }); // how long the fade is

    private float alpha = 1f;

    public SoftwareNameApp() {
        super("Goodbye Screen for now");
        t.start();

        setBackground(Color.red);
        setSize(1280, 800);

        setResizable(false);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        Image logo = new ImageIcon("Company Logo Scaled.png").getImage();

        ///int count = 0;
        int x = (int) (Math.random() * 1000);
        int y = (int) (Math.random() * 500);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.drawImage(logo, x, y, this);

        repaint();

        //   count++;
        try {
            Thread.sleep(125);
        } catch (Exception e) {
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new SoftwareNameApp();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
