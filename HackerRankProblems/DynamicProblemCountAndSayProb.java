import java.io.*;
import java.util.*;

/*
 * 1. 1 
 * 2. 11 
 * 3. 21 
 * 4. 1211 
 * 5. 111221 
 * 6. 312211
 */

class DynamicProblemCountAndSayProb {
   public static void main(String[] args) {
      // System.out.println(getTerm(0));
      System.out.println(getTerm(1));
      System.out.println(getTerm(2));
      System.out.println(getTerm(5));
      System.out.println(getTerm(6));
      System.out.println(getTerm(7));
      System.out.println(getTerm(30));
      System.out.println(getTerm(50));
   }

   private static String getTerm(int n) {
      // Write your solution here.
      if (n < 1 || n > 100)
         throw new IllegalArgumentException();
      String[] ans = new String[n];
      for (int i = 0; i < n; i++) {
         if (i == 0) {
            ans[i] = "1";
         } else {
            ans[i] = "";
            String temp = ans[i - 1];
            int tempCtr = 1;
            for (int j = 0; j < temp.length(); j++) {
               while (j < temp.length() - 1 && temp.charAt(j) == temp.charAt(j + 1)) {
                  tempCtr++;
                  j++;
               }
               ans[i] += "" + tempCtr + temp.charAt(j);
               tempCtr = 1;
            }
         }
      }
      return "Answer at term " + n + " = " + ans[n - 1];

      // Alternate RegEx Solution
      // String currSeq = "1";

      // // Pattern to match the repetitive digits
      // String regexPattern = "(.)\\1*";
      // Pattern pattern = Pattern.compile(regexPattern);

      // for (int i = 1; i < n; ++i) {
      // Matcher m = pattern.matcher(currSeq);
      // StringBuffer nextSeq = new StringBuffer();

      // // each group contains identical and adjacent digits
      // while (m.find()) {
      // nextSeq.append(m.group().length() + String.valueOf(m.group().charAt(0)));
      // }
      // // prepare for the next iteration
      // currSeq = nextSeq.toString();
      // }

      // return currSeq;
   }
}