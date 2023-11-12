// Problem 1: Diagonal Difference
// Assuming that you are given a square matrix of size N x N , calculate the absolute difference between the sums of its diagonals.
// For example, given a square matrix arr :
// 1 2 3
// 4 5 6
// 9 8 9
// The left-to-right diagonal = 1 + 5 + 9 = 15 . The right-to-left diagonal = 3 + 5 + 9 = 17 . 
// Their absolute difference is |15 - 17| = 2 .
// Here’s what you have to do:
// Complete the function diagonalDifference
// The function takes the following parameter(s): int arr[n][m] : an array of integers
// Expected output/return: int : the absolute diagonal difference
// Note: |x| is the absolute value of x

import java.util.Arrays;
import java.util.List;
//removed all unused imports and just specified ones that are used

class DiagonalDifference {

   // removed static declaration since usage in main was not static
   // (caused warnings and a general bad coding convention/practice)
   public int diagonalDifference(List<List<Integer>> arr) {
      int wholeListLen = arr.size();
      int l2rdSum = 0, l2rdCtr = 0;
      int r2ldSum = 0, r2ldCtr = wholeListLen - 1;

      for (List<Integer> insArr : arr) {
         // error check if 2d array/list is not a square matrix
         if (insArr.size() != wholeListLen)
            throw new IllegalArgumentException();

         // get subtotal of left-to-right and right-to-left diagonals
         l2rdSum += insArr.get(l2rdCtr);
         r2ldSum += insArr.get(r2ldCtr);

         // update counter indexes to get proper diagonals
         l2rdCtr++;
         r2ldCtr--;
      }

      int ans = Math.abs(l2rdSum - r2ldSum);
      System.out.println("Actual output/return: " + ans); // print answer for reference
      return ans;
   }

   public static void main(String[] args) {
      DiagonalDifference cls = new DiagonalDifference();

      /** Test Cases */

      // Expected output/return: 2
      cls.diagonalDifference(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(9, 8, 9)));

      // Expected output/return: 15
      cls.diagonalDifference(Arrays.asList(Arrays.asList(11, 2, 4), Arrays.asList(4, 5, 6), Arrays.asList(10, 8, -12)));

      // Expected output/return: 80
      cls.diagonalDifference(Arrays.asList(Arrays.asList(32, 15, 3, -1, 4), Arrays.asList(11, 20, 7, 5, 7),
            Arrays.asList(2, 3, 1, -8, -1), Arrays.asList(5, 3, 5, 16, 0), Arrays.asList(5, 12, 21, 91, 29)));
   }

}
