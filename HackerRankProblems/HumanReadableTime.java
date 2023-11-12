public class HumanReadableTime {
    public static String makeReadable(int seconds) {
        // Check for illegal input
        if (seconds > 359999)
            throw new IllegalArgumentException();
        return String.format("%02d:%02d:%02d", seconds / 3600, seconds % 3600 / 60, seconds % 60);
    }
}