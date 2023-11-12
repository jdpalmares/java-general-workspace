
// you can also use imports, for example:
import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class VacationLeaveWeekCounter {
    public int solution(int Y, String A, String B, String W) {
        // write your code in Java SE 8
        String[] monthNameArr = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        String[] dayNameArr = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        int beginMonth = Arrays.asList(monthNameArr).indexOf(A);
        int endMonth = Arrays.asList(monthNameArr).indexOf(B);
        int dayNum = Arrays.asList(dayNameArr).indexOf(W) + 1;

        System.out.println("Start Month: " + beginMonth);
        System.out.println("End Month : " + endMonth);
        System.out.println("Day Number : " + dayNum);

        // Use calendar object to get first week
        Calendar calObjBegin = Calendar.getInstance();
        calObjBegin.set(Y, beginMonth, 1);
        calObjBegin.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calObjBegin.set(beginMonth, calObjBegin.getFirstDayOfWeek());

        TimeZone tz = calObjBegin.getTimeZone();
        ZoneId zoneId = tz.toZoneId();
        LocalDateTime startDate = LocalDateTime.ofInstant(calObjBegin.toInstant(), zoneId);

        Calendar lastDayOfMonth = new GregorianCalendar();
        // Set the date to the day before the 1st day of the next month to get last week
        lastDayOfMonth.set(Y, endMonth, 0);
        Calendar calObjEnd = Calendar.getInstance();
        calObjEnd.set(Y, endMonth, 0);
        calObjEnd.set(endMonth, lastDayOfMonth.get(Calendar.WEEK_OF_YEAR));
        calObjEnd.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        tz = calObjEnd.getTimeZone();
        zoneId = tz.toZoneId();
        LocalDateTime endDate = LocalDateTime.ofInstant(calObjEnd.toInstant(), zoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = startDate.format(formatter);
        System.out.println("Start : " + formatDateTime);

        formatDateTime = endDate.format(formatter);
        System.out.println("End : " + formatDateTime);

        long numWeeks = ChronoUnit.WEEKS.between(startDate, endDate);
        return (int) numWeeks;
    }

    public static void main(String[] args) {
        VacationLeaveWeekCounter rotate = new VacationLeaveWeekCounter();
        System.out.println("Number of weeks = " + rotate.solution(2004, "March", "June", "Wednesday"));

        /*
         * Get first and last week of the month // Get calendar set to current date and
         * time Calendar c = Calendar.getInstance();
         * 
         * // Set the calendar to monday of the current week c.set(Calendar.DAY_OF_WEEK,
         * Calendar.MONDAY);
         * 
         * System.out.println(); // Print dates of the current week starting on Monday
         * DateFormat df = new SimpleDateFormat("EEE dd/MM/yyyy");
         * System.out.println(df.format(c.getTime())); for (int i = 0; i <6; i++) {
         * c.add(Calendar.DATE, 1); } System.out.println(df.format(c.getTime()));
         * System.out.println();
         */
    }
}
