package AdventOfCode2022.Problem3;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RucksackPrioCal {

   public static void main(String[] args) throws FileNotFoundException {
      URL path = RucksackPrioCal.class.getResource("rucksackitems.txt");
      File file = new File(path.getFile());

      try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
         int subTotal = 0;
         while (sc.hasNextLine()) {
            String lineVal = sc.nextLine();
            System.out.println("Line value = " + lineVal);
            int middeString = lineVal.length() / 2; // get the middle of the String
            String[] compartments = { lineVal.substring(0, middeString), lineVal.substring(middeString) };
            subTotal += scoreRSPrio(compartments[0], compartments[1]);
         }
         System.out.println("Total score --> " + subTotal);
         sc.close(); // closes the scanner

      }
   }

   static int scoreRSPrio(String compartment1, String compartment2) {
      // Lowercase item types a through z have priorities 1 through 26.
      // Uppercase item types A through Z have priorities 27 through 52.
      String scoreBasis = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

      int score = 0;
      Set<String> compartment1Set = new HashSet<String>(convertToList(compartment1));
      Set<String> compartment2Set = new HashSet<String>(convertToList(compartment2));
      compartment1Set.retainAll(compartment2Set);
      System.out.println("Similar characters--> " + compartment1Set.toString());
      String prioChar = getSetString(compartment1Set);
      score = scoreBasis.indexOf(prioChar) + 1;
      System.out.println("Score on that rucksack --> " + score);
      return score;
   }

   private static List<String> convertToList(String str) {
      List<String> tempList = new ArrayList<String>();
      char[] arr = str.toCharArray();
      for (char a : arr) {
         tempList.add(String.valueOf(a));
      }
      return tempList;
   }

   private static String getSetString(Set<String> inputSet) {
      String result = "";
      Iterator<String> iterate = inputSet.iterator();
      // Accessing elements
      while (iterate.hasNext()) {
         result = iterate.next();
      }
      return result;
   }
}