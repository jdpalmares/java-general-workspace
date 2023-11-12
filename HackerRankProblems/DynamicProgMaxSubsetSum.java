// Given an array of integers, find the subset of non-adjacent elements with the maximum sum. 
// Calculate the sum of that subset. It is possible that the maximum sum is 0, 
// the case when all elements are negative.

// For example, given an array  we have the following possible subsets. 
// These exclude the empty subset and single element subsets which are also valid.

// Our maximum subset sum is . Note that any individual element is a subset as well.

// As another example, . In this case, it is best to choose no element: return .

// Function Description
// Complete the  function in the editor below. 
// It should return an integer representing the maximum subset sum for the given array.

// maxSubsetSum has the following parameter(s): arr: an array of integers

// Input Format 
// The first line contains an integer, n. The second line contains n space-separated integers arr[i].

// Constraints
// Output Format Return the maximum sum described in the statement.

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DynamicProgMaxSubsetSum {

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        if (arr.length == 0)
            return 0;
        arr[0] = Math.max(0, arr[0]);
        if (arr.length == 1)
            return arr[0];
        arr[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < arr.length; i++) {
            System.out.println("Val 1 to be compared at [" + i + "] = " + arr[i - 1]);
            System.out.println("Val 2 sum " + arr[i] + " + " + arr[i - 2] + " to be compared at [" + i + "] = "
                    + (arr[i] + arr[i - 2]));
            arr[i] = Math.max(arr[i - 1], arr[i] + arr[i - 2]);
        }
        return arr[arr.length - 1];
    }

    public static void main(String[] args) throws IOException {
        String pathToDir = "C:\\Codes\\JavaWorkspace\\DynamicProgMaxSubsetSumTestCase.txt";
        File file = new File(pathToDir);

        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
            int n = sc.nextInt();
            sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = sc.nextLine().split(" ");
            sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            int res = maxSubsetSum(arr);

            System.out.println("Largest Sum of Subset ans = " + res);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
