package zalora.assignment.duckie.twitsplit.utility;

public class Utils {
    public static String getPresentDateFromMilliseconds(long millisecond) {
        long currentTime = System.currentTimeMillis();
        long marginTime = currentTime - millisecond;

        int days = (int) (marginTime / 1000 / 3600 / 24);
        if (days > 0) {
            return String.format("%dd", days);
        }

        int hours = (int) (marginTime / 1000 /3600);
        if (hours > 0) {
            return String.format("%dh", hours);
        }

        int minutes = (int) (marginTime / 1000 / 60);
        if (minutes > 0) {
            return String.format("%dm", minutes);
        }

        return "1m";
    }
}