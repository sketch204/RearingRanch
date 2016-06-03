package root;

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

    public static void main (String [] args) {
        new SplashScreen("SplashScreen");
//        new RearingRanchDriver();
        // Never mind this, i wanted to try cheat codes, for jokes and fun :)
        // I'll finish it sometime later
//        if (args[0].equalsIgnoreCase("ns") || args[0].equalsIgnoreCase("nextStage"))
//            window.d.nextStage();

        // If you're interested, 'args' holds whatever you type in the output console (no Scanner required :)
        // For some reason it gives me illogical errors that I've never gotten before.
    }
}
