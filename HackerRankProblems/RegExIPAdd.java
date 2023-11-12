// Write a class called MyRegex which will contain a string pattern. 
// You need to write a regular expression and assign it to the pattern such that 
// it can be used to validate an IP address. Use the following definition of an IP address:
// IP address is a string in the form "A.B.C.D", where the value of A, B, C, and D may range 
// from 0 to 255. Leading zeros are allowed. The length of A, B, C, or D can't be greater than 3.

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String IP = in.next();
            System.out.println(IP.matches(new MyRegex().pattern));
        }

    }
}

class MyRegex {
    // Group as a whole and put starter indicator ^
    String grouperFirst = "^(";
    // range check from 000 to 255 (with leading zeroes okay)
    // first set is 000 to 100 with leading zeroes and check for single or double
    // digit
    // second set is from 200 to 249 checking (required all 3 digits)
    // last set is from 250-255 (required all 3 digits)
    String firstOctetCheck = "([01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])";
    // Combine dot(.) checker with firstOctetCheck and repeat 3 times
    String secondToLastOctetCheck = "([.]([01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])){3,3}";
    // Confirm Group as a whole and put ender indicator $
    String grouperLast = ")$";
    String pattern = grouperFirst + firstOctetCheck + secondToLastOctetCheck + grouperLast;
}