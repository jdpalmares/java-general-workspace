package AdventOfCode2022.Problem12;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ShortestPathTrek {

   public static void main(String[] args) throws FileNotFoundException {
      URL path = ShortestPathTrek.class.getResource("pathinput.txt");
      File file = new File(path.getFile());

      try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
         Map<String, Integer> mapGrid = new HashMap<String, Integer>();
         String startCoordinate = null, endCoordinate = null;
         int yCoordinate = 0;
         while (sc.hasNextLine()) {
            String pathRow = sc.nextLine();
            // System.out.println("Line Value = " + pathRow); // for debugging

            int xCoordinate = 0;
            for (char c : pathRow.toCharArray()) {
               String coordinate = "" + xCoordinate + "," + yCoordinate;
               if (c == 'S') {
                  startCoordinate = coordinate;
                  mapGrid.put(startCoordinate, 'a' - 1);
               } else if (c == 'E') {
                  endCoordinate = coordinate;
                  mapGrid.put(endCoordinate, 'z' + 1);
               } else {
                  mapGrid.put(coordinate, (int) c);
               }
               xCoordinate++;
            }
            yCoordinate++;
         }

         // System.out.println("startCoordinate = " + startCoordinate); //for debugging
         // System.out.println("endCoordinate = " + endCoordinate); //for debugging
         // mapGrid.entrySet().forEach(entry -> { //for debugging
         // System.out.println(entry.getKey() + " " + entry.getValue());
         // });

         int anspart1 = pathfind(startCoordinate, endCoordinate, mapGrid);
         System.out.println("Answer Value Part 1 = " + (anspart1 - 2)); // remove to start and end point

         int anspart2 = Integer.MAX_VALUE;
         for (String c : mapGrid.keySet()) {
            if (mapGrid.get(c) == 'a')
               anspart2 = Math.min(anspart2, pathfind(c, endCoordinate, mapGrid));
         }
         System.out.println("Answer Value Part 2 = " + (anspart2 - 2)); // remove to start and end point
      }
   }

   static int pathfind(String start, String end, Map<String, Integer> mapGrid) { // basic DFS also need to study more
      Map<String, Integer> gCost = new HashMap<String, Integer>();
      Map<String, String> parent = new HashMap<String, String>();
      LinkedList<String> queue = new LinkedList<String>();
      gCost.put(start, 0);
      queue.add(start);
      while (queue.size() > 0) {
         String cur = queue.poll();
         if (cur.equals(end)) {
            ArrayList<String> path = new ArrayList<>();
            while (parent.containsKey(cur)) {
               path.add(cur);
               cur = parent.get(cur);
            }
            // System.out.println("Answer direction = " + path.toString()); //for debugging
            return path.size();
         }
         for (String c : getAllNeighbors(cur)) {
            // skip if outside bounds or if height is more than one above current
            if (!mapGrid.containsKey(c) || mapGrid.get(c) > mapGrid.get(cur) + 1)
               continue;
            int tentativeG = gCost.get(cur) + 1;
            if (tentativeG < gCost.getOrDefault(c, Integer.MAX_VALUE)) {
               gCost.put(c, tentativeG);
               parent.put(c, cur);
               queue.add(c);
            }
         }
      }
      return Integer.MAX_VALUE;
   }

   static List<String> getAllNeighbors(String coordinate) {
      List<String> listCoordinates = new ArrayList<String>();

      String[] coordinateDetails = coordinate.split(",");
      int xCoord = Integer.valueOf(coordinateDetails[0].trim());
      int yCoord = Integer.valueOf(coordinateDetails[1].trim());
      for (int yOff = -1; yOff < 2; yOff++) {
         for (int xOff = -1; xOff < 2; xOff++) {
            if (xOff == 0 ^ yOff == 0) { // if not diagonal or self
               String offCoordinate = "" + (xCoord + xOff) + "," + (yCoord + yOff);
               listCoordinates.add(offCoordinate);
            }
         }
      }
      return listCoordinates;
   }
}