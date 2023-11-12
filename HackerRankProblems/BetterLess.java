// Problem 5: The Better Less
// Jasmine, a real-estate agent, has a chart of distinct projected prices for a house over the next several years. She must buy the house in one year 
// and sell it in another year, and she must do so at a loss. However, she wants to minimize her financial loss after reselling.
// Example: Given an array of integers price = [20, 15, 8, 2, 12]
// Her minimum loss is achieved by purchasing on year 1 where price[1] = 15 and then reselling in year 4 where price[4] = 12 .
// Result: 15 - 12 = 3
// Here’s what you have to do: Complete the function betterLess .
// The function takes the following parameter(s): int arr[n] : the prices of the home at each year
// Expected output/return: int : the minimum loss possible
// Notes:  Assuming that all the prices are never of the same value; and, The expected output is not less than or equal to zero.

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
//removed all unused imports and just specified ones that are used

class BetterLess {

   public int betterLess(List<Integer> arr) {
      int arrLen = arr.size();

      // use hashmap to save the index of the prices as well
      HashMap<Integer, Integer> priceMap = new HashMap<Integer, Integer>();
      // use a sorted array to reduce time complexity
      int[] sortedArr = new int[arrLen];

      int tempCtr = 0;
      for (Integer price : arr) {
         priceMap.put(price, tempCtr); // save price and index to hashmap
         sortedArr[tempCtr] = price; // put price elements into an array
         tempCtr++;
      }

      // sort array using library for efficient and readable sorting
      Arrays.sort(sortedArr);

      int ans = Integer.MAX_VALUE;
      boolean priceIncCheck = false;
      for (int i = 1; i < arrLen; i++) {
         // check if index of price is later than compared price
         if (priceMap.get(sortedArr[i]) < priceMap.get(sortedArr[i - 1])) {
            priceIncCheck = true;
            int curDiff = sortedArr[i] - sortedArr[i - 1];
            if (curDiff > 0) { // if difference > 0, get minimum value
               ans = Math.min(ans, curDiff);
            }
         }
      }

      if (!priceIncCheck) // parameter error check if no loss will occur
         throw new IllegalArgumentException();

      System.out.println("Actual output/return: " + ans); // print answer for reference
      return ans;
   }

   public static void main(String[] args) {
      BetterLess cls = new BetterLess();

      /** Test Cases */

      cls.betterLess(Arrays.asList(5, 10, 3));
      // Expected output/return: 2

      cls.betterLess(Arrays.asList(2, 3, 4, 1));
      // Expected output/return: 1

      cls.betterLess(Arrays.asList(814622574, 395073375, 728403717, 351040147, 812032996, 159867325, 904058808,
            328140030, 505001234, 941296590, 539072309));
      // Expected output/return: 2589578
   }

}
