// You are given a string containing characters  and  only. 
// Your task is to change it into a string such that there are no matching adjacent characters. 
// To do this, you are allowed to delete zero or more characters in the string.
// Your task is to find the minimum number of required deletions.

// Remove an  at positions  and  to make  in  deletions.

// Function Description
// Complete the alternatingCharacters function in the editor below.
// alternatingCharacters has the following parameter(s):
// string s: a string
// Returns
// int: the minimum number of deletions required
// Input Format
// The first line contains an integer , the number of queries.
// The next  lines each contain a string  to analyze.
import java.io.IOException;

public class StringManipAltChar {
    // Complete the alternatingCharacters function below.
    static int alternatingCharacters(String s) {
        int ans = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {

        String s = "ABABABAB";

        int result = alternatingCharacters(s);

        System.out.println("Min number of times string must be deleted: " + result);
    }
}
