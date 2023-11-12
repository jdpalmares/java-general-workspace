// The absolute difference is the positive difference between two values  and , 
// is written  or  and they are equal. If  and , . Given an array of integers, 
// find the minimum absolute difference between any two elements in the array.

// Example. 
// There are  pairs of numbers:  and . The absolute differences for these pairs are ,  and .
// The minimum absolute difference is .

// Function Description
// Complete the minimumAbsoluteDifference function in the editor below. 
// It should return an integer that represents the minimum absolute difference between any 
// pair of elements. minimumAbsoluteDifference has the following parameter(s):
// int arr[n]: an array of integers
// Returns int: the minimum absolute difference found
// Input Format
// The first line contains a single integer , the size of .
// The second line contains  space-separated integers, .

import java.io.*;
import java.util.PriorityQueue;

public class GreedyMinAbsDiffArray {

    // Complete the minimumAbsoluteDifference function below.
    static int minimumAbsoluteDifference(int[] arr) {
        PriorityQueue<Integer> sortArr = new PriorityQueue<Integer>();

        // sort array while removing possible duplicates
        for (int i = 0; i < arr.length; i++) {
            sortArr.add(arr[i]);
        }

        int ans = Integer.MAX_VALUE;
        int subtrahend = sortArr.poll();

        while (!sortArr.isEmpty()) {
            int minuend = sortArr.poll();
            int diff = Math.abs(subtrahend - minuend);
            if (ans > diff)
                ans = diff;
            subtrahend = minuend;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {

        int[] arr = new int[] { -59, -36, -13, 1, -53, -92, -2, -96, -54, 75 };

        int result = minimumAbsoluteDifference(arr);

        System.out.println("Minimum Absolute difference of the array: " + result);
    }
}
