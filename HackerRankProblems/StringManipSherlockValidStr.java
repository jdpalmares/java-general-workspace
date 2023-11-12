// Sherlock considers a string to be valid if all characters of the string appear the 
// same number of times. It is also valid if he can remove just  character at  index in the string,
// and the remaining characters will occur the same number of times. Given a string , 
// determine if it is valid. If so, return YES, otherwise return NO.

// Example
// This is a valid string because frequencies are .
// This is a valid string because we can remove one  and have  of each character in the 
// remaining string.
// This string is not valid as we can only remove  occurrence of . 
// That leaves character frequencies of .

// Function Description
// Complete the isValid function in the editor below.
// isValid has the following parameter(s):
// string s: a string
// Returns string: either YES or NO
// Input Format
// A single string .

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StringManipSherlockValidStr {
    // Complete the isValid function below.
    static String isValid(String s) {
        String ans = "YES";

        // Tally all characters into a map (arranged by character asc)
        Map<Character, Integer> charCounter = new HashMap<>();
        for (char ch : s.toCharArray()) {
            int ct = charCounter.containsKey(ch) ? charCounter.get(ch) : 0;
            charCounter.put(ch, (ct + 1));
        }

        // Collate All tally values (arranged by number asc)
        Map<Integer, Integer> ctrCounter = new HashMap<>();
        for (HashMap.Entry<Character, Integer> charCtr : charCounter.entrySet()) {
            int ct = ctrCounter.containsKey(charCtr.getValue()) ? ctrCounter.get(charCtr.getValue()) : 0;
            ctrCounter.put(charCtr.getValue(), (ct + 1));
        }

        System.out.println("charCounter map: " + charCounter.toString());
        System.out.println("ctrCounter map: " + ctrCounter.toString());
        System.out.println("ctrCounter size: " + ctrCounter.size());

        // not a valid string if there are more than 2 tallies that are different
        if (ctrCounter.size() > 2) {
            ans = "NO";
        } else {
            if (ctrCounter.size() == 2) { // checker if there is only 1 tally value
                Integer[] x = ctrCounter.values().toArray(new Integer[0]);
                // check if pair tallies have greater than 1 frequency (only 1 can be deleted)
                if (x[0] > 1 && x[1] > 1) {
                    ans = "NO";
                } else {
                    // get tally values
                    Integer[] y = ctrCounter.keySet().toArray(new Integer[0]);
                    if (y[0] != 1) {
                        if (Math.abs(y[0] - y[1]) > 1)
                            ans = "NO";
                    } else {
                        if (x[0] > 1) {
                            ans = "NO";
                        }
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        String s = "aaaaabc";

        String result = isValid(s);

        System.out.println("Is the string valid: " + result);
    }
}

// 3, 5, 14

// 2, 7, 13, 14, 15