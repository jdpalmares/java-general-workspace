package AdventOfCode2022.Problem13;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PacketPairCalc {

   public static void main(String[] args) throws FileNotFoundException {
      URL path = PacketPairCalc.class.getResource("packetpairinput.txt");
      File file = new File(path.getFile());

      try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
         Map<String, Integer> mapGrid = new HashMap<String, Integer>();
         String startCoordinate = null, endCoordinate = null;
         int pairNumber = 1;
         while (sc.hasNextLine()) {
            String packet1Value = sc.nextLine();
            System.out.println("1st packet Value = " + packet1Value); // for debugging

            if (!packet1Value.isEmpty()) {
               String packet2Value = sc.nextLine();
               System.out.println("2nd packet Value = " + packet2Value); // for debugging

            }
         }

         // int anspart1 = pathfind(startCoordinate, endCoordinate, mapGrid);
         // System.out.println("Answer Value Part 1 = " + (anspart1 - 2)); // remove to
         // start and end point

         // int anspart2 = Integer.MAX_VALUE;
         // for (String c : mapGrid.keySet()) {
         // if (mapGrid.get(c) == 'a')
         // anspart2 = Math.min(anspart2, pathfind(c, endCoordinate, mapGrid));
         // }
         // System.out.println("Answer Value Part 2 = " + (anspart2 - 2)); // remove to
         // start and end point
      }
   }

   static int pathfind(String start, String end, Map<String, Integer> mapGrid) {
      return Integer.MAX_VALUE;
   }
}