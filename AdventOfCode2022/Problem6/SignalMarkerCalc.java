package AdventOfCode2022.Problem6;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class SignalMarkerCalc {

   public static void main(String[] args) throws FileNotFoundException {
      URL path = SignalMarkerCalc.class.getResource("signalInput.txt");
      File file = new File(path.getFile());

      try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {

         int ctr = 0;
         while (sc.hasNextLine()) {
            String signal = sc.nextLine();
            System.out.println("Line value = " + signal);
            ctr = countCharacterProcessBeforeFindingMarker(signal);
         }

         System.out.println("Answer after moves are finished = " + ctr);
      }
   }

   static int countCharacterProcessBeforeFindingMarker(String signal) {
      // 4 for problem 1, 14 for probem 2
      for (int i = 0; i <= signal.length() - 14; i++) {
         String subSignal = signal.substring(i, i + 14);
         System.out.println("subSignal value = " + subSignal);
         Set<Character> chunk = subSignal.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
         System.out.println("chunk value = " + chunk);
         if (chunk.size() == 14) {
            return i + 14;
         }
      }
      return 0;
   }

   // most efficient way to check for duplicates need to study
   // private static boolean isUnique(String inputString) {
   // long[] used = new long[1024];
   // for (char c : inputString.toCharArray()) {
   // if ((used[c >>> 6] & (1 << c)) > 0) {
   // return false;
   // }
   // used[c >>> 6] |= 1 << c;
   // }
   // return true;
   // }

   // easier way to implement due to amount of characters
   // private static boolean isUnique2(String inputString) {
   // boolean[] used = new boolean[65536];
   // for (char c : inputString.toCharArray()) {
   // if (used[c]) {
   // return false;
   // }
   // used[c] = true;
   // }
   // return true;
   // }
}
