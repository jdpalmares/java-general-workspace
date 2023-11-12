package AdventOfCode2022.Problem9;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TenTailPathCalc {

   public static void main(String[] args) throws FileNotFoundException {
      URL path = TenTailPathCalc.class.getResource("headmovesinput.txt");
      File file = new File(path.getFile());

      int[] headCurPos = new int[] { 0, 0 }; // LRUD
      int[][] tailsCurPos = new int[9][2];
      for (int i = 0; i < 9; i++) {
         tailsCurPos[i] = new int[] { 0, 0 }; // LRUD
      }
      int trueDiff = 2;
      Set<String> tailMoveHistory = new HashSet<String>();
      tailMoveHistory.add(Arrays.toString(tailsCurPos[8]));
      try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
         while (sc.hasNextLine()) {
            String headMoveCmd = sc.nextLine();
            System.out.println("Line value = " + headMoveCmd);

            String[] moveSet = headMoveCmd.split(" ");

            for (int i = 0; i < Integer.parseInt(moveSet[1]); i++) {
               switch (moveSet[0]) {
                  case "L":
                     headCurPos[0]--;
                     break;
                  case "R":
                     headCurPos[0]++;
                     break;
                  case "U":
                     headCurPos[1]++;
                     break;
                  case "D":
                     headCurPos[1]--;
                     break;
               }

               // System.out.println("Current position of head = " +
               // Arrays.toString(headCurPos));
               // System.out.println("Current position of tail = " +
               // Arrays.toString(tailCurPos));
               int tailLength = tailsCurPos.length;
               for (int x = 0; x < tailLength; x++) {
                  int headCurPosX = (x == 0) ? headCurPos[0] : tailsCurPos[x - 1][0];
                  int headCurPosY = (x == 0) ? headCurPos[1] : tailsCurPos[x - 1][1];
                  int tailCurPosX = tailsCurPos[x][0];
                  int tailCurPosY = tailsCurPos[x][1];
                  if (headCurPosX > tailCurPosX) {
                     if (Math.abs(headCurPosX - tailCurPosX) >= trueDiff) { // moving too far right
                        tailsCurPos[x][0]++; // same row
                        tailCurPosX = tailsCurPos[x][0];
                        if (headCurPosY > tailCurPosY) { // diagonal movement
                           tailsCurPos[x][1]++;
                           tailCurPosY = tailsCurPos[x][1];
                        } else if (headCurPosY < tailCurPosY) { // diagonal movement
                           tailsCurPos[x][1]--;
                           tailCurPosY = tailsCurPos[x][1];
                        }
                        if (x == 8) {
                           tailMoveHistory.add(Arrays.toString(tailsCurPos[x]));
                        }
                     }
                  }
                  if (headCurPosX < tailCurPosX) {
                     if (Math.abs(tailCurPosX - headCurPosX) >= trueDiff) { // moving too far left
                        tailsCurPos[x][0]--;// same row
                        tailCurPosX = tailsCurPos[x][0];
                        if (headCurPosY > tailCurPosY) { // diagonal movement
                           tailsCurPos[x][1]++;
                           tailCurPosY = tailsCurPos[x][1];
                        } else if (headCurPosY < tailCurPosY) { // diagonal movement
                           tailsCurPos[x][1]--;
                           tailCurPosY = tailsCurPos[x][1];
                        }
                        if (x == 8) {
                           tailMoveHistory.add(Arrays.toString(tailsCurPos[x]));
                        }
                     }
                  }
                  if (headCurPosY > tailCurPosY) {
                     if (Math.abs(headCurPosY - tailCurPosY) >= trueDiff) { // moving too far up
                        tailsCurPos[x][1]++; // same column
                        tailCurPosY = tailsCurPos[x][1];
                        if (headCurPosX > tailCurPosX) { // diagonal movement
                           tailsCurPos[x][0]++;
                           tailCurPosX = tailsCurPos[x][0];
                        } else if (headCurPosX < tailCurPosX) { // diagonal movement
                           tailsCurPos[x][0]--;
                           tailCurPosX = tailsCurPos[x][0];
                        }
                        if (x == 8) {
                           tailMoveHistory.add(Arrays.toString(tailsCurPos[x]));
                        }
                     }
                  }
                  if (headCurPosY < tailCurPosY) {
                     if (Math.abs(tailCurPosY - headCurPosY) >= trueDiff) { // moving too far down
                        tailsCurPos[x][1]--; // same column
                        tailCurPosY = tailsCurPos[x][1];
                        if (headCurPosX > tailCurPosX) { // diagonal movement
                           tailsCurPos[x][0]++;
                           tailCurPosX = tailsCurPos[x][0];
                        } else if (headCurPosX < tailCurPosX) { // diagonal movement
                           tailsCurPos[x][0]--;
                           tailCurPosX = tailsCurPos[x][0];
                        }
                        if (x == 8) {
                           tailMoveHistory.add(Arrays.toString(tailsCurPos[x]));
                        }
                     }
                  }

               }
               // System.out.println("after move " + moveSet[0] + " " + (i + 1) + " out of " +
               // moveSet[1]);
               // System.out.println("after checking curr position of head = " +
               // Arrays.toString(headCurPos));
               // for (int z = 0; z < 9; z++) {
               // System.out
               // .println("after checking curr position of tail #" + (z + 1) + " = "
               // + Arrays.toString(tailsCurPos[z]));
               // }
            }
            // System.out.println(" subfinal position after moves ");
            // System.out.println("after checking curr position of head = " +
            // Arrays.toString(headCurPos));
            // for (int x = 0; x < 9; x++) {
            // System.out
            // .println("after checking curr position of tail #" + (x + 1) + " = "
            // + Arrays.toString(tailsCurPos[x]));
            // }
            System.out.println("current number of position did tail go at least once = " + tailMoveHistory.size());
         }
      }
   }
}
// Answer is between 2371-2634
// not 2601
// not 2537
// not 2566