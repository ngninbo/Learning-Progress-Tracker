package tracker.util;

import java.util.Scanner;

public class TrackerUtil {

    public static final String NOTIFICATION_MSG_FORMAT = "To: %s\n" +
            "Re: Your Learning Progress\n" +
            "Hello, %s! You have accomplished our %s course!\n";
    public static final String NOTIFICATION_SUCCEED_MSG = "Total %s students have been notified.\n";

    public static String requestUserInput() {
        return new Scanner(System.in).nextLine();
    }
}
