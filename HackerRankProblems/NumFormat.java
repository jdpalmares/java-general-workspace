// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class NumFormat {
    public String solution(String S) {
        final String WHITESPACEHYPHENREGEX = "[\\-\\s]";
        // Remove all current whitespace and hyphen from number
        String CleanNum = S.replaceAll(WHITESPACEHYPHENREGEX, "");
        StringBuilder finNum = new StringBuilder("");
        // Add new string for building the final num
        for (int i = 0; i < CleanNum.length(); i++) {
            // check for third digit
            if ((i + 1) % 3 == 0) {
                finNum.append(CleanNum.charAt(i));
                if (i + 1 != CleanNum.length())
                    finNum.append('-');
            } else {
                finNum.append(CleanNum.charAt(i));
            }
        }
        // Check for 1-digit number and make 2 2-digit numbers instead
        String finNumStr = finNum.toString();
        int finNumStrLen = finNumStr.length();
        int scnd2LastDig = finNumStrLen - 2; // for better readability
        int thrd2LastDig = finNumStrLen - 3; // for better readability
        if (finNumStr.lastIndexOf('-') == scnd2LastDig) {
            finNum.setCharAt(scnd2LastDig, finNum.charAt(thrd2LastDig));
            finNum.setCharAt(thrd2LastDig, '-');
            finNumStr = finNum.toString();
        }
        return finNumStr;
    }
}
