// There are a number of plants in a garden. Each of the plants has been treated with some amount of pesticide. After each day, 
// if any plant has more pesticide than the plant on its left, being weaker than the left one, it dies.

// You are given the initial values of the pesticide in each of the plants. Determine the number of days after which no plant dies, 
// i.e. the time after which there is no plant with more pesticide content than the plant to its left.

// Example p = [3,6,2,7,5]

// Use a 1-indexed array. On day 1, plants 2 and 4 die leaving p = [3,2,5] . On day 2, plant 3 in  dies leaving p = [3,2] . 
// There is no plant with a higher concentration of pesticide than the one to its left, so plants stop dying after day 2.

// Function Description
// Complete the function poisonousPlants in the editor below.
// poisonousPlants has the following parameter(s): int p[n]: the pesticide levels in each plant
// Returns int: the number of days until plants no longer die from pesticide

// Input Format
// The first line contains an integer , the size of the array .
// The next line contains  space-separated integers .

// Sample Input
// 7
// 6 5 8 4 7 10 9

// Sample Output 2

import java.io.*;
import java.util.*;

public class StackPoisonousPlants {

   static class Pair {
      int val, count;

      public Pair(int val, int count) {
         this.val = val;
         this.count = count;
      }
   }

   // Complete the poisonousPlants function below.
   static int poisonousPlants(int[] p) {
      Stack<Pair> stack = new Stack<>();
      int cnt = 0;
      for (int i = p.length - 1; i >= 0; i--) {
         int temp = 0;
         while (!stack.empty() && p[i] < stack.peek().val) {
            temp++;
            Pair pair = stack.pop();
            temp = Math.max(temp, pair.count);
         }
         cnt = Math.max(cnt, temp);
         stack.push(new Pair(p[i], temp));
      }

      return cnt;

   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      // BufferedWriter bufferedWriter = new BufferedWriter(new
      // FileWriter(System.getenv("OUTPUT_PATH")));

      // int n = scanner.nextInt();
      // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      // int[] p = new int[n];

      // String[] pItems = scanner.nextLine().split(" ");
      // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      // for (int i = 0; i < n; i++) {
      // int pItem = Integer.parseInt(pItems[i]);
      // p[i] = pItem;
      // }

      int[] p = new int[] { 6, 5, 8, 4, 7, 10, 9 };

      int result = poisonousPlants(p);

      System.out.println("Answer = " + result);
      // bufferedWriter.write(String.valueOf(result));
      // bufferedWriter.newLine();

      // bufferedWriter.close();

      // scanner.close();
   }
}
