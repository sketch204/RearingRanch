/**
 * This is the driver class of this program.
 *
 * @author Inal Gotov.
 * @version 1, 2016-05-10.
 *
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir: 0
 *       Inal: 0.5
 */
public class RearingRanchDriver {
    private static MasterFrame window = null;

    public RearingRanchDriver () {
        window = new MasterFrame();
    }

    public static void main (String [] args) {
        new RearingRanchDriver();
    }

    public static MasterFrame getWindow() {
        return window;
    }
}
