import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class testPalin {
    boolean checkPalindrome(String inputString) {
        String comp = "";
        for (int i = inputString.length() - 1; i >= 0; i--) {
            comp += "" + inputString.charAt(i);
        }
        if (inputString.equalsIgnoreCase(comp))
            return true;
        else
            return false;
    }

    int[] isZigzag(int[] numbers) {
        int[] ans = new int[numbers.length - 2];
        for (int i = 0; i < numbers.length - 2; i++) {
            if ((numbers[i] < numbers[i + 1] && numbers[i + 1] > numbers[i + 2])
                    || (numbers[i] > numbers[i + 1] && numbers[i + 1] < numbers[i + 2]))
                ans[i] = 1;
            else
                ans[i] = 0;
        }

        return ans;
    }

    int countOnSubarrays(int[] a, int[][] queries) {
        int count = 0;
        for (int i = 0; i < queries.length; i++) {
            int beginDex = queries[i][0];
            int lastDex = queries[i][1];
            int numComp = queries[i][2];
            for (int j = beginDex; j <= lastDex; j++) {
                if (a[j] == numComp)
                    count++;
            }
        }
        return count;
    }

    int removeOneDigit(String s, String t) {
        BiFunction<String, String, Boolean> xLessY = (x, y) -> x.compareTo(y) < 0;
        long ans = IntStream.range(0, s.length()).filter(i -> Character.isDigit(s.charAt(i)))
                .mapToObj(i -> s.substring(0, i) + s.substring(i + 1)).filter(ss -> xLessY.apply(ss, t)).count();
        return (int) ans;

        // int ans = 0;
        // for (int i = 0; i < s.length(); i++) {
        // if (Character.isDigit(s.charAt(i))) {
        // String subsString = s.substring(0, i) + s.substring(i + 1);
        // if (subsString.compareTo(t) < 0 || t.compareTo(subsString) < 0)
        // ans++;
        // }
        // }
        // return ans;

    }

    char[][] binaryMatrixQueries(char[][] m, int[][] q) {
        char[][] ans = m;
        for (int i = 0; i < q.length; i++) {
            if (q[i][0] == 1) {

            } else if (q[i][0] == 2) {

            } else if (q[i][0] == 3) {

            }
        }

        return ans;
    }

    public String[] splitStringEvery(String s, int interval) {
        int arrayLength = (int) Math.ceil(((s.length() / (double) interval)));
        String[] result = new String[arrayLength];

        int j = 0;
        int lastIndex = result.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            result[i] = s.substring(j, j + interval);
            j += interval;
        } // Add the last bit
        result[lastIndex] = s.substring(j);

        return result;
    }
}
