package AdventOfCode2022.Problem9;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TailPathCalc {

   public static void main(String[] args) throws FileNotFoundException {
      URL path = TailPathCalc.class.getResource("headmovesinput.txt");
      File file = new File(path.getFile());

      int[] headCurPos = new int[] { 0, 0 }; // LRUD
      int[] tailCurPos = new int[] { 0, 0 }; // LRUD
      Set<String> tailMoveHistory = new HashSet<String>();
      tailMoveHistory.add(Arrays.toString(tailCurPos));
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

               System.out.println("Current position of head = " + Arrays.toString(headCurPos));
               System.out.println("Current position of tail = " + Arrays.toString(tailCurPos));

               int headCurPosX = headCurPos[0];
               int headCurPosY = headCurPos[1];
               int tailCurPosX = tailCurPos[0];
               int tailCurPosY = tailCurPos[1];
               if (headCurPosX > tailCurPosX) {
                  if (Math.abs(headCurPosX - tailCurPosX) >= 2) { // moving too far right
                     tailCurPos[0]++; // same row
                     tailCurPosX = tailCurPos[0];
                     if (headCurPosY > tailCurPosY) { // diagonal movement
                        tailCurPos[1]++;
                        tailCurPosY = tailCurPos[1];
                     } else if (headCurPosY < tailCurPosY) { // diagonal movement
                        tailCurPos[1]--;
                        tailCurPosY = tailCurPos[1];
                     }
                     tailMoveHistory.add(Arrays.toString(tailCurPos).toString());
                  }
               }
               if (headCurPosX < tailCurPosX) {
                  if (Math.abs(tailCurPosX - headCurPosX) >= 2) { // moving too far left
                     tailCurPos[0]--;// same row
                     tailCurPosX = tailCurPos[0];
                     if (headCurPosY > tailCurPosY) { // diagonal movement
                        tailCurPos[1]++;
                        tailCurPosY = tailCurPos[1];
                     } else if (headCurPosY < tailCurPosY) { // diagonal movement
                        tailCurPos[1]--;
                        tailCurPosY = tailCurPos[1];
                     }
                     tailMoveHistory.add(Arrays.toString(tailCurPos).toString());
                  }
               }
               if (headCurPosY > tailCurPosY) {
                  if (Math.abs(headCurPosY - tailCurPosY) >= 2) { // moving too far up
                     tailCurPos[1]++; // same column
                     tailCurPosY = tailCurPos[1];
                     if (headCurPosX > tailCurPosX) { // diagonal movement
                        tailCurPos[0]++;
                        tailCurPosX = tailCurPos[0];
                     } else if (headCurPosX < tailCurPosX) { // diagonal movement
                        tailCurPos[0]--;
                        tailCurPosX = tailCurPos[0];
                     }
                     tailMoveHistory.add(Arrays.toString(tailCurPos).toString());
                  }
               }
               if (headCurPosY < tailCurPosY) {
                  if (Math.abs(tailCurPosY - headCurPosY) >= 2) { // moving too far down
                     tailCurPos[1]--; // same column
                     tailCurPosY = tailCurPos[1];
                     if (headCurPosX > tailCurPosX) { // diagonal movement
                        tailCurPos[0]++;
                        tailCurPosX = tailCurPos[0];
                     } else if (headCurPosX < tailCurPosX) { // diagonal movement
                        tailCurPos[0]--;
                        tailCurPosX = tailCurPos[0];
                     }
                     tailMoveHistory.add(Arrays.toString(tailCurPos).toString());
                  }
               }

               System.out.println("after checking curr position of head = " + Arrays.toString(headCurPos));
               System.out.println("after checking curr position of tail = " + Arrays.toString(tailCurPos));
            }
         }
         System.out.println("how many position did tail go at least once = " + tailMoveHistory.size());
      }
   }
}
