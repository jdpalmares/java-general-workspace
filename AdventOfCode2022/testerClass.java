package AdventOfCode2022;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class testerClass {

   public static void main(String[] args) {
      String s1 = "abbccsartcc";
      String s2 = "cbdcezxrtcc";
      Set<String> arrSet1 = new HashSet<String>(convertToList(s1));
      Set<String> arrSet2 = new HashSet<String>(convertToList(s2));
      arrSet1.retainAll(arrSet2);
      System.out.println("Similar characters-->" + arrSet1.size());
      System.out.println("Similar characters-->" + arrSet1.toString());

      char score = 'a';
      int charScore = score;
   }

   private static List<String> convertToList(String str) {
      List<String> tempList = new ArrayList<String>();
      char[] arr = str.toCharArray();
      for (char a : arr) {
         tempList.add(String.valueOf(a));
      }
      return tempList;
   }
}