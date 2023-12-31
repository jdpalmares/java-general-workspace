// You are given an unordered array consisting of consecutive integers  [1, 2, 3, ..., n] without
// any duplicates. You are allowed to swap any two elements. 
// You need to find the minimum number of swaps required to sort the array in ascending order.

// For example, given the array  we perform the following steps:
// i   arr                         swap (indices)
// 0   [7, 1, 3, 2, 4, 5, 6]   swap (0,3)
// 1   [2, 1, 3, 7, 4, 5, 6]   swap (0,1)
// 2   [1, 2, 3, 7, 4, 5, 6]   swap (3,4)
// 3   [1, 2, 3, 4, 7, 5, 6]   swap (4,5)
// 4   [1, 2, 3, 4, 5, 7, 6]   swap (5,6)
// 5   [1, 2, 3, 4, 5, 6, 7]
// It took 5 swaps to sort the array.

// Function Description
// Complete the function minimumSwaps in the editor below. 
// It must return an integer representing the minimum number of swaps to sort the array.

// minimumSwaps has the following parameter(s):
// arr: an unordered array of integers

// Input Format
// The first line contains an integer, , the size of .
// The second line contains  space-separated integers .

// Output Format Return the minimum number of swaps to sort the given array.

import java.io.*;
import java.util.*;

public class ArraysMinSwaps {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int count = 0;
        int i = 0;
        while (i < arr.length) {
            if (arr[i] != i + 1) {
                int temp = arr[i];
                arr[i] = arr[arr[i] - 1];
                arr[temp - 1] = temp;
                count++;
            } else
                i++;
        }
        return count;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
