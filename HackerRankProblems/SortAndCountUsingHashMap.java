
// you can also use imports, for example:
// import java.util.*;
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class SortAndCountUsingHashMap {
    public int solution(int[] A) {
        // write your code in Java SE 8
        int arrLen = A.length;
        if (A == null || arrLen == 0 || arrLen % 2 == 0)
            throw new IllegalArgumentException();
        if (arrLen == 1)
            return A[0];
        return findUnpairedNum(A, arrLen);
    }

    private static int findUnpairedNum(int a[], int n) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < n; i++)
            hm.put(a[i], hm.get(a[i]) == null ? 1 : hm.get(a[i]) + 1);

        System.out.println("First Unsorted Hashmap Print");
        hm.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });

        for (SortedMap.Entry<Integer, Integer> x : hm.entrySet()) {
            if (x.getValue() % 2 != 0)
                return x.getKey();
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        SortAndCountUsingHashMap rotate = new SortAndCountUsingHashMap();
        int[] a = { 9, 3, 9, 3, 9, 7, 9 };
        System.out.println("Unpaired Number = " + rotate.solution(a));
    }
}