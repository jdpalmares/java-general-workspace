package AdventOfCode2022.Problem4;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

//try AI to build code for solution
//https://github.com/gergo-salyi/advent-of-code-2022/blob/master/src/day02avx2.rs
//https://github.com/max-sixty/aoc-gpt
//https://github.com/max-sixty/aoc-gpt/blob/main/openai.py
public class RedundantPairCount {

   public static void main(String[] args) throws FileNotFoundException {
      URL path = RedundantPairCount.class.getResource("pairInput.txt");
      File file = new File(path.getFile());

      try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
         int subTotal = 0;
         while (sc.hasNextLine()) {
            String[] pairRanges = sc.nextLine().split(",");
            System.out.println("Line value = " + Arrays.toString(pairRanges));
            // subTotal += checkRedundantPairs(pairRanges[0], pairRanges[1]);
            subTotal += checkOverlappingPairs(pairRanges[0], pairRanges[1]);
            System.out.println("Subtotal currently = " + subTotal);
         }
         System.out.println("Subtotal as a whole = " + subTotal);
      }
   }

   static int checkRedundantPairs(String pair1, String pair2) {
      int score = 0;
      String[] pair1Range = pair1.split("-");
      System.out.println("Pair1 range = " + Arrays.toString(pair1Range));
      String[] pair2Range = pair2.split("-");
      System.out.println("Pair2 range = " + Arrays.toString(pair2Range));

      int pair1min = Integer.parseInt(pair1Range[0]);
      System.out.println("Pair1 minimum = " + pair1min);
      int pair1max = Integer.parseInt(pair1Range[1]);
      System.out.println("Pair1 maximum = " + pair1max);
      int pair2min = Integer.parseInt(pair2Range[0]);
      System.out.println("Pair2 minimum = " + pair2min);
      int pair2max = Integer.parseInt(pair2Range[1]);
      System.out.println("Pair2 maximum = " + pair2max);

      if ((pair1min <= pair2min && pair1max >= pair2max) ||
            (pair1min >= pair2min && pair1max <= pair2max)) {
         score++;
         System.out.println("Redundant Pair found! pair1 = " + Arrays.toString(pair1Range) + " and pair2 = "
               + Arrays.toString(pair2Range));
      }
      return score;
   }

   static int checkOverlappingPairs(String pair1, String pair2) {
      int score = 0;
      String[] pair1Range = pair1.split("-");
      System.out.println("Pair1 range = " + Arrays.toString(pair1Range));
      String[] pair2Range = pair2.split("-");
      System.out.println("Pair2 range = " + Arrays.toString(pair2Range));

      int pair1min = Integer.parseInt(pair1Range[0]);
      System.out.println("Pair1 minimum = " + pair1min);
      int pair1max = Integer.parseInt(pair1Range[1]);
      System.out.println("Pair1 maximum = " + pair1max);
      int pair2min = Integer.parseInt(pair2Range[0]);
      System.out.println("Pair2 minimum = " + pair2min);
      int pair2max = Integer.parseInt(pair2Range[1]);
      System.out.println("Pair2 maximum = " + pair2max);

      if (pair1min == pair2min) {
         score++;
      } else if (pair1min < pair2min) {
         if (pair1max >= pair2max) {
            score++;
         } else if (pair1max >= pair2min) {
            score++;
         }
      } else if (pair1min > pair2min) {
         if (pair1max <= pair2max) {
            score++;
         } else if (pair1min <= pair2max) {
            score++;
         }
      }
      if (score > 0) {
         System.out.println("Overlapping Pair found! pair1 = " + Arrays.toString(pair1Range) + " and pair2 = "
               + Arrays.toString(pair2Range));
      }
      return score;
   }
}