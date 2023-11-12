// There is a large pile of socks that must be paired by color. 
// Given an array of integers representing the color of each sock, 
// determine how many pairs of socks with matching colors there are.

// There is one pair of color  and one of color . 
// There are three odd socks left, one of each color. The number of pairs is .
// Function Description Complete the sockMerchant function in the editor below.
// sockMerchant has the following parameter(s):
// int n: the number of socks in the pile
// int ar[n]: the colors of each sock
// Returns
// int: the number of pairs
import java.util.HashSet;

public class CountPairSocks {
    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
        HashSet<Integer> colors = new HashSet<>();
        int pairs = 0;

        for (int i = 0; i < n; i++) {
            if (!colors.contains(ar[i])) {
                colors.add(ar[i]);
            } else {
                pairs++;
                colors.remove(ar[i]);
            }
        }

        return pairs;

    }

    public static void main(String[] args) {
        int sockCount = 9;
        int[] sockArr = new int[] { 10, 20, 20, 10, 10, 30, 50, 10, 20 };

        int result = sockMerchant(sockCount, sockArr);

        System.out.println("Answer is = " + result);
    }
}
