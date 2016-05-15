/**
 * This is the driver class of this program.
 *
 * @author Inal Gotov.
 * @version 1, 2016-05-10.
 *
 */
public class RearingRanchDriver {
    private static MasterFrame window = null;

    public RearingRanchDriver () {
        setWindow(new MasterFrame());
    }

    public static void main (String [] args) {
        new RearingRanchDriver();
    }

    public static MasterFrame getWindow() {
        return window;
    }

    public static void setWindow(MasterFrame window) {
        RearingRanchDriver.window = window;
    }
}
