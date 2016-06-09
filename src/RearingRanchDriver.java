/**
 * This is the driver class of this program.
 *
 * @author Inal Gotov.
 * @version 1, 2016-05-10.
 * Last Edited: 2016-06-09
 * Hours since 2016-05-11:
 *       Tamir: 0:30
 *       Inal: 0:30
 */
public class RearingRanchDriver {
    /** The window that this program is in. */
    private static MasterFrame window = null;

    /** Creates a new instance of this program. */
    public RearingRanchDriver () {
        window = new MasterFrame();
    }

    /**
     * Returns the window that this program is contained in.
     * @return The window that this program is contained in.
     */
    public static MasterFrame getWindow() {
        return window;
    }

    /** Launches the actual program. */
    public static void launch () {
        new RearingRanchDriver();
    }

    /**
     * The main method of this program
     * @param args - An array of arguments to be passed to the program.
     */
    public static void main (String [] args) {
        new SplashScreen("SplashScreen");
    }
}
