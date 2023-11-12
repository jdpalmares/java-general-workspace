import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

//completed in 31 minutes
public class MinDistPairArray {

    // Complete the minimumDistances function below.
    static int minimumDistances(int[] a) {
        int arrLen = a.length;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < arrLen; i++)
            hm.put(a[i], hm.get(a[i]) == null ? 1 : hm.get(a[i]) + 1);

        int ans = -1;
        for (SortedMap.Entry<Integer, Integer> x : hm.entrySet()) {
            int numOccur = x.getValue();
            if (numOccur > 1) {
                int tempShrtDistEaNum = 0;
                // get first index of the number
                for (int i = 0; i < arrLen; i++) {
                    if (a[i] == x.getKey()) {
                        // get shortest next occurrence of the number
                        for (int j = i + 1; j < arrLen; j++) {
                            if (a[j] == x.getKey()) {
                                int distPair = j - i;
                                // set the first distance
                                if (tempShrtDistEaNum == 0) {
                                    tempShrtDistEaNum = distPair;
                                } else { // compare each distance for every paired num
                                    if (tempShrtDistEaNum > distPair) {
                                        tempShrtDistEaNum = distPair;
                                    }

                                }
                                // optimize performance
                                i = j - 1; // to skip unecessary loops
                                break; // to break inner loop if pair is found
                            }
                        }
                    }
                }
                if (ans == -1) {
                    ans = tempShrtDistEaNum;
                } else {
                    if (ans > tempShrtDistEaNum) {
                        ans = tempShrtDistEaNum;
                    }
                }
            }

        }
        return ans;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int result = minimumDistances(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
