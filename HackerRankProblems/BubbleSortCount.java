import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BubbleSortCount {

    // Complete the countSwaps function below.
    static void countSwaps(int[] a) {
        int swapCtr = 0;
        int arrLen = a.length;
        // Do bubble sort ascending
        for (int i = 0; i < arrLen; i++) {
            for (int j = 0; j < arrLen - 1; j++) {
                // Swap current pos to next pos if former is greater
                if (a[j] > a[j + 1]) {
                    swapCtr++;
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        System.out.println("Array is sorted in " + swapCtr + " swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[arrLen - 1]);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        countSwaps(a);

        scanner.close();
    }
}
