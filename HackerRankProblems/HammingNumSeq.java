
//For Treeset implementation
import java.util.Arrays;
import java.util.TreeSet;
//For PriorityQueue implementation
import java.util.PriorityQueue;

public class HammingNumSeq {

    public static long hamming(int n) {

        // Basic Imp but perf issue
        // long hamNum = 1;
        // int hamCtr = 1;

        // while (n > hamCtr) {
        // hamNum++;
        // if (isHamming(hamNum) == 1)
        // hamCtr++;
        // }
        // return hamNum;
        return getNthHamViaArr(n); // Array Implementation
        // return getNthHamViaTreeSet(n); //Treeset Implementation
        // return getNthHamViaPrioQ(n); //PriorityQueue Implementation
    }

    // Methods needed for basic imp START
    // private static long maxDivide(long a, long b) {
    // while (a % b == 0)
    // a = a / b;
    // return a;
    // }

    // private static long isHamming(long no) {
    // no = maxDivide(no, 2);
    // no = maxDivide(no, 3);
    // no = maxDivide(no, 5);

    // return (no == 1) ? 1 : 0;
    // }
    // Methods needed for basic imp END

    // Dynamic Prog imp but high array cost (3n) using array
    private static long getNthHamViaArr(int n) {
        // To store hamming numbers
        long hamArr[] = new long[n];
        int i2 = 0, i3 = 0, i5 = 0;
        long mult2Val = 2;
        long mult3Val = 3;
        long mult5Val = 5;
        long curHamNum = 1;

        hamArr[0] = 1;

        for (int i = 1; i < n; i++) {
            curHamNum = Math.min(mult2Val, Math.min(mult3Val, mult5Val));

            hamArr[i] = curHamNum;
            if (curHamNum == mult2Val) {
                i2 = i2 + 1;
                mult2Val = hamArr[i2] * 2;
            }
            if (curHamNum == mult3Val) {
                i3 = i3 + 1;
                mult3Val = hamArr[i3] * 3;
            }
            if (curHamNum == mult5Val) {
                i5 = i5 + 1;
                mult5Val = hamArr[i5] * 5;
            }
        }

        // End of for loop (i=1; i<n; i++)
        return curHamNum;
    }

    // Dynamic Prog imp but high array cost (3n) using TreeSet
    private static long getNthHamViaTreeSet(int n) {
        if (n <= 0)
            return -1;
        TreeSet<Long> ts = new TreeSet<>(Arrays.asList(2L, 3L, 5L));
        long smallest = 1;
        for (int i = 1; i < n; i++) {
            smallest = ts.pollFirst();
            ts.add(smallest * 2);
            ts.add(smallest * 3);
            ts.add(smallest * 5);
        }
        return smallest;
    }

    // Dynamic Prog imp but high array cost (3n) using PriorityQueue
    private static long getNthHamViaPrioQ(int n) {
        PriorityQueue<Long> minheap = new PriorityQueue<Long>();
        minheap.add(2L);
        minheap.add(3L);
        minheap.add(5L);
        long curr = 1;
        for (int i = 1; i < n; i++) {
            curr = minheap.poll();
            while (minheap.peek() == curr) {
                curr = minheap.poll();
            }
            minheap.add(curr * 2);
            minheap.add(curr * 3);
            minheap.add(curr * 5);
        }
        return curr;
    }
}