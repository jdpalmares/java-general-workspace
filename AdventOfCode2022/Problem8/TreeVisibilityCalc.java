package AdventOfCode2022.Problem8;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TreeVisibilityCalc {

   public static void main(String[] args) throws FileNotFoundException {
      URL path = TreeVisibilityCalc.class.getResource("forestgridinput.txt");
      File file = new File(path.getFile());

      List<List<Integer>> treeGrid = new ArrayList<List<Integer>>();
      try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
         int ans1 = 0;
         int ans2 = 0;
         while (sc.hasNextLine()) {
            String treeLine = sc.nextLine();
            System.out.println("Line value = " + treeLine);

            List<Integer> treelines = new ArrayList<Integer>();
            for (int i = 0; i < treeLine.length(); i++) {
               treelines.add(Character.getNumericValue(treeLine.charAt(i)));
            }
            System.out.println("current tree line value = " + treelines);
            treeGrid.add(treelines);
         }
         ans1 = countVisibleTreesOutsideGrid(treeGrid);
         System.out.println("how many trees are visible from outside the grid = " + ans1);
         ans2 = getHighestScenicScore(treeGrid);
         System.out.println("Highest Scenic Score = " + ans2);
      }
   }

   // problem 1
   static int countVisibleTreesOutsideGrid(List<List<Integer>> treeGrid) {
      int treeSubTotal = 0;

      for (int currentTreeLine = 1; currentTreeLine < treeGrid.size() - 1; currentTreeLine++) {
         List<Integer> treeLine = treeGrid.get(currentTreeLine);
         for (int currentTree = 1; currentTree < treeLine.size() - 1; currentTree++) {
            Integer tree = treeLine.get(currentTree);
            // check left
            boolean leftVisib = true;
            for (int leftChk = currentTree - 1; leftChk >= 0; leftChk--) {
               if (tree <= treeLine.get(leftChk)) {
                  leftVisib = false;
               }
            }
            if (leftVisib) {
               treeSubTotal++;
               continue;
            }
            // check right
            boolean rightVisib = true;
            for (int rightChk = currentTree + 1; rightChk < treeLine.size(); rightChk++) {
               if (tree <= treeLine.get(rightChk)) {
                  rightVisib = false;
               }
            }
            if (rightVisib) {
               treeSubTotal++;
               continue;
            }
            // check up
            boolean upVisib = true;
            for (int upChk = currentTreeLine - 1; upChk >= 0; upChk--) {
               if (tree <= treeGrid.get(upChk).get(currentTree)) {
                  upVisib = false;
               }
            }
            if (upVisib) {
               treeSubTotal++;
               continue;
            }
            // check down
            boolean downVisib = true;
            for (int downChk = currentTreeLine + 1; downChk < treeGrid.size(); downChk++) {
               if (tree <= treeGrid.get(downChk).get(currentTree)) {
                  downVisib = false;
               }
            }
            if (downVisib) {
               treeSubTotal++;
               continue;
            }
         }
      }
      treeSubTotal += (treeGrid.size() * 2); // add 1st and last tree rows
      treeSubTotal += (treeGrid.get(0).size() * 2); // add 1st and last tree columns
      treeSubTotal += -4; // to remove double counting on corners
      return treeSubTotal;
   }

   // problem 2
   static int getHighestScenicScore(List<List<Integer>> treeGrid) {
      int currentHighestScenicScore = 0;

      for (int currentTreeLine = 1; currentTreeLine < treeGrid.size() - 1; currentTreeLine++) {
         List<Integer> treeLine = treeGrid.get(currentTreeLine);
         for (int currentTree = 1; currentTree < treeLine.size() - 1; currentTree++) {
            Integer tree = treeLine.get(currentTree);
            // check left
            int leftScore = 0;
            for (int leftChk = currentTree - 1; leftChk >= 0; leftChk--) {
               leftScore++;
               if (tree <= treeLine.get(leftChk)) {
                  break;
               }
            }
            // check right
            int rightScore = 0;
            for (int rightChk = currentTree + 1; rightChk < treeLine.size(); rightChk++) {
               rightScore++;
               if (tree <= treeLine.get(rightChk)) {
                  break;
               }
            }
            // check up
            int upScore = 0;
            for (int upChk = currentTreeLine - 1; upChk >= 0; upChk--) {
               upScore++;
               if (tree <= treeGrid.get(upChk).get(currentTree)) {
                  break;
               }
            }
            // check down
            int downScore = 0;
            for (int downChk = currentTreeLine + 1; downChk < treeGrid.size(); downChk++) {
               downScore++;
               if (tree <= treeGrid.get(downChk).get(currentTree)) {
                  break;
               }
            }
            int subTotalScore = leftScore * rightScore * upScore * downScore;
            if (subTotalScore > currentHighestScenicScore) {
               currentHighestScenicScore = subTotalScore;
            }
         }
      }

      return currentHighestScenicScore;
   }
}
