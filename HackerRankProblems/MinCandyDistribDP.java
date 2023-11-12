import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MinCandyDistribDP {
    // You can get rid of iterating forward when searching for descending sequence.
    // For 4 descending elements you'll get 4,3,2,1 in S array, which are the same
    // as 1,2,3,4. For descending order on each iteration add current
    // len_of_desc_seq to total sum. And you don't need any arrays,
    // just keep track of current descending sequence length.
    // Time complexity O(N), space complexity O(1), single iteration over array.
    static long candies(int n, int[] arr) {
        int descending_seq = 0;
        long sum = 0;
        int prev_c = 0;
        int prev_num_of_candies = 0;
        for (int c : arr) {
            if (c >= prev_c) {
                if (descending_seq > 0) {
                    // adjust max value if descending > ascending sequence
                    if (descending_seq >= prev_num_of_candies) {
                        sum += 1 + descending_seq - prev_num_of_candies;
                    }
                    // last of descending = local minimum
                    prev_num_of_candies = 1;
                    descending_seq = 0;
                }
                if (c > prev_c) {
                    ++prev_num_of_candies;
                } else {
                    // optimal if previous value is the same
                    prev_num_of_candies = 1;
                }
                sum += prev_num_of_candies;
            } else {
                ++descending_seq;
                // For 3 descending numbers in a row this summing strategy
                // will increment like sum+=1+2+3 which is the same as
                // more usual and expected sum+=3+2+1
                sum += descending_seq;
            }
            prev_c = c;
        }
        // If we finished on descending order, update last local max
        if (descending_seq >= prev_num_of_candies) {
            sum += 1 + descending_seq - prev_num_of_candies;
        }
        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Codes\\JavaWorkspace\\testOutput.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        long result = candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
