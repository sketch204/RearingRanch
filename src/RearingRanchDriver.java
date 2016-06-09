/**
 * This is the driver class of this program.
 *
 * @author Inal Gotov.
 * @version 1, 2016-05-10.
 *
 * Last Edited: 2016-05-15
 * Hours since 2016-05-11:
 *       Tamir: 0:30
 *       Inal: 0:30
 */
public class RearingRanchDriver {
    private static MasterFrame window = null;

    public RearingRanchDriver () {
        window = new MasterFrame();
    }

    public static MasterFrame getWindow() {
        return window;
    }

    public static void launch () {
        new RearingRanchDriver();
    }

    public static void main (String [] args) {
        new SplashScreen("SplashScreen");
    }
}
