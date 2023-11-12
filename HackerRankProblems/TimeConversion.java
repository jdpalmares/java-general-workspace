// Problem 3: Time Conversion
// Suppose that you are given a time in 12-hour AM/PM format, convert it to military (24-hour) time.
// Note:
// - 12:00:00AM on a 12-hour clock is 00:00:00 on a 24-hour clock.
// - 12:00:00PM on a 12-hour clock is 12:00:00 on a 24-hour clock.
// Here’s what you have to do: Complete the function timeConversion .
// The function takes the following parameter(s): string s : time in 12-hour format.
// Expected output/return: string : time in 24-hour format.
// Notes: The function assumes that all time inputs are in a valid time format. string s is represented by the time format ( hh:mm:ssAM or hh:mm:ssPM ).

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//removed all unused imports and just specified ones that are used

class TimeConversion {

   // removed static declaration since usage in main was not static
   // (caused warnings and a general bad coding convention/practice)
   public String timeConversion(String s) {
      // initalize counters for the important string indexes
      int strLen = s.length(), ampmIndx = strLen - 2;
      // format input string into a string that can be formatted by SimpleDateFormat
      String formattableStr = s.subSequence(0, ampmIndx) + " " + s.subSequence(ampmIndx, strLen);
      SimpleDateFormat inFormat = new SimpleDateFormat("hh:mm:ss aa"); // 12 hour input format
      SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm:ss"); // 24 hour output format
      try {
         Date dateAns = inFormat.parse(formattableStr);
         String ans = outFormat.format(dateAns);
         System.out.println("Actual output/return: " + ans); // print answer for reference
         return ans;
      } catch (ParseException e) {
         e.printStackTrace();
         return null;
      }
   }

   public static void main(String[] args) {
      TimeConversion cls = new TimeConversion();

      /** Test Cases */

      cls.timeConversion("07:05:45PM"); // Expected output/return: 19:05:45
      cls.timeConversion("12:30:01AM"); // Expected output/return: 00:30:01
      cls.timeConversion("11:59:59PM"); // Expected output/return: 23:59:59
   }

}