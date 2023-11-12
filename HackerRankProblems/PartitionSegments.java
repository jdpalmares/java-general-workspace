// Problem 4: Segment Partitioning
// Provided that you have an array of integers, you are given the task to determine the number of its continuous segments such that:
// - The sum of the integers in the segment is equal to the integer d ; and, The length of the continuous segment is equal to the integer m .
// For example: Consider that you are given an array arr = [2, 2, 1, 3, 2] , find the number of segments where:
// - The sum of the integers of the segment (d) is equal to 4 . With the length of the segment (m) equal to 2 .
// In this case, two segments are meeting the criteria: [2, 2] and [1, 3] . Both segments have the sum of their integers equal to 4 , and length equal to 2 .
// Here’s what you have to do: Complete the function partitionSegments .
// The function takes that following parameter(s):
// - int arr[n] : an array of integers
// - int d : an integer, the required sum of integers in the segment.
// - int m : an integer, the required length of the segment.
// Expected output/return: int : the number of segments that meet the provided criteria.
// Notes: If the segment’s length from its starting point up to the end of array arr is less than m , but the sum of the integers (d) is correct, it is considered valid.

import java.util.Arrays;
import java.util.List;
//removed all unused imports and just specified ones that are used

class PartitionSegments {

   // removed static declaration since usage in main was not static
   // (caused warnings and a general bad coding convention/practice)
   public int partitionSegments(List<Integer> arr, int d, int m) {

      int ans = 0;
      int arrLen = arr.size();

      // get total of sub array of length m
      int subTotal = 0;
      // counters to be used, need separate for tracking algo
      int i = 0, j = 0, k = 0;

      // Check first segment
      for (i = 0; i < m && i < arrLen; i++)
         subTotal += arr.get(i);
      if (subTotal == d)
         ans++;

      // Check remaining segments under length m or arrLen if m > arrLen
      for (j = i; j < arrLen; j++) {
         // use i instead of m in case m > arrLen
         subTotal = subTotal + arr.get(j) - arr.get(j - i);
         if (subTotal == d)
            ans++;
      }

      // Check last segments' subtotal (even if length < m)
      if (arrLen > 1) {
         for (k = arrLen - i; k < arrLen; k++) {
            subTotal = subTotal - arr.get(k);
            if (subTotal == d)
               ans++;
         }
      }

      System.out.println("Actual output/return: " + ans); // print answer for reference
      return ans;
   }

   public static void main(String[] args) {
      PartitionSegments cls = new PartitionSegments();

      /** Test Cases */

      cls.partitionSegments(Arrays.asList(5, 2, 0, 3, 2), 5, 3); // Expected output/return: 3
      cls.partitionSegments(Arrays.asList(1, 2, 1, 3, 2), 3, 2); // Expected output/return: 2
      cls.partitionSegments(Arrays.asList(1, 1, 1, 1, 1, 1), 3, 2); // Expected output/return: 0
      cls.partitionSegments(Arrays.asList(4), 4, 1); // Expected output/return: 1
      cls.partitionSegments(Arrays.asList(21, 3, 100, 3, 2), 5, 100); // Expected output/return: 1
   }

}
