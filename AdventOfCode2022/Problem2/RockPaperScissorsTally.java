package AdventOfCode2022.Problem2;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

//A for Rock, B for Paper, and C for Scissors.
//The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors) 
//plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).

public class RockPaperScissorsTally {

   public static void main(String[] args) throws FileNotFoundException {
      URL path = RockPaperScissorsTally.class.getResource("rpsinput.txt");
      File file = new File(path.getFile());

      try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
         int subTotal = 0;
         while (sc.hasNextLine()) {
            String[] arrItems = sc.nextLine().split(" ");
            System.out.println("Line value = " + Arrays.toString(arrItems));
            // subTotal += getRPSScore(arrItems[0].charAt(0), arrItems[1].charAt(0));
            subTotal += getRPSScore2(arrItems[0].charAt(0), arrItems[1].charAt(0));
            System.out.println("Subtotal currently = " + subTotal);
         }
         System.out.println("Subtotal as a whole = " + subTotal);
      }
   }

   // The second column, you reason, must be what you should play in response: X
   // for Rock, Y for Paper, and Z for Scissors.
   static int getRPSScore(char opponentHand, char yourHand) {
      int score = 0;
      if (yourHand == 'X') {
         score += 1;
         if (opponentHand == 'A') {
            score += 3;
         } else if (opponentHand == 'C') {
            score += 6;
         }
      } else if (yourHand == 'Y') {
         score += 2;
         if (opponentHand == 'A') {
            score += 6;
         } else if (opponentHand == 'B') {
            score += 3;
         }
      } else if (yourHand == 'Z') {
         score += 3;
         if (opponentHand == 'B') {
            score += 6;
         } else if (opponentHand == 'C') {
            score += 3;
         }
      }
      return score;
   }

   // The score for a single round is the score for the shape you selected
   // (1 for Rock, 2 for Paper, and 3 for Scissors)
   // plus the score for the outcome of the round
   // (0 if you lost, 3 if the round was a draw, and 6 if you won).
   // X means you need to lose, Y means you need to end the round in a draw, and Z
   // means you need to win. Good luck!"
   static int getRPSScore2(char opponentHand, char expectedResult) {
      int score = 0;
      if (opponentHand == 'A') { // rock
         if (expectedResult == 'X') {
            score += 0; // score of result (lose)
            score += 3; // score of shape (scissors)
         } else if (expectedResult == 'Y') {
            score += 3; // score of result (draw)
            score += 1; // score of shape (rock)
         } else if (expectedResult == 'Z') {
            score += 6; // score of result (win)
            score += 2; // score of shape (paper)
         }
      } else if (opponentHand == 'B') { // paper
         if (expectedResult == 'X') {
            score += 0; // score of result (lose)
            score += 1; // score of shape (rock)
         } else if (expectedResult == 'Y') {
            score += 3; // score of result (draw)
            score += 2; // score of shape (paper)
         } else if (expectedResult == 'Z') {
            score += 6; // score of result (win)
            score += 3; // score of shape (scissors)
         }
      } else if (opponentHand == 'C') { // scissors
         if (expectedResult == 'X') {
            score += 0; // score of result (lose)
            score += 2; // score of shape (paper)
         } else if (expectedResult == 'Y') {
            score += 3; // score of result (draw)
            score += 3; // score of shape (scissors)
         } else if (expectedResult == 'Z') {
            score += 6; // score of result (win)
            score += 1; // score of shape (rock)
         }
      }
      return score;
   }
}