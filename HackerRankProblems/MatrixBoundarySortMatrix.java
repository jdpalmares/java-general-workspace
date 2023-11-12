import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

//Overall time complexity - O(3n) - queue, sort, and put back
//Overall space complexity - O(3n) - deque array, array for sort, answer array
public class MatrixBoundarySortMatrix {
    private static int[][] boundarySortMatrix(int matrix[][], int m, int n) {
        int[][] ans = matrix;
        int prioQSize = 0;
        // determine how many borders to iterate
        if (m >= n) {
            if (n % 2 == 0)
                prioQSize = n / 2;
            else
                prioQSize = (n / 2) + 1;
        } else {
            if (m % 2 == 0)
                prioQSize = m / 2;
            else
                prioQSize = (m / 2) + 1;
        }
        // put all border numbers in a dequeue
        // time complex O(2n); n to queue all and n to sort
        // space complex O(2n); n for queue and n for array to sort
        ArrayList<Deque<Integer>> sortMatrix = new ArrayList<Deque<Integer>>();
        for (int i = 0; i < prioQSize; i++) {
            Deque<Integer> borderQueue = new ArrayDeque<Integer>();
            int mCtr = i;
            int nCtr = i;
            while (mCtr < m - i) {
                if ((mCtr == i) || (nCtr == i) || (nCtr == (n - 1 - i)) || (mCtr == (m - 1 - i)))
                    borderQueue.add(matrix[mCtr][nCtr]);
                nCtr++;
                if (nCtr == n - i) {
                    mCtr++;
                    nCtr = i;
                }
            }
            // sort dequeue after every iteration
            Integer[] a = borderQueue.toArray(new Integer[0]);
            Arrays.sort(a);
            borderQueue.clear();
            for (Integer x : a) {
                borderQueue.add(x);
            }
            sortMatrix.add(borderQueue);
        }
        System.out.println("Sorted Boundaries - " + sortMatrix.toString());
        // put it back to the answer 2d matrix
        // time complex O(n);
        // space complexity O(n) - extra just for answer but can use parameter
        for (int i = 0; i < prioQSize; i++) {
            int mCtr = i;
            int nCtr = i;
            while (mCtr < m - i) {
                if (mCtr == i) {// first layer put smallest
                    ans[mCtr][nCtr] = sortMatrix.get(i).pollFirst();
                } else if (nCtr == i) { // for vertical left put largest
                    ans[mCtr][nCtr] = sortMatrix.get(i).pollLast();
                } else if (nCtr == (n - 1 - i)) { // for vertical right put smallest
                    ans[mCtr][nCtr] = sortMatrix.get(i).pollFirst();
                } else if (mCtr == (m - 1 - i)) { // for last layer put largest
                    ans[mCtr][nCtr] = sortMatrix.get(i).pollLast();
                }
                nCtr++;
                if (nCtr == n - i) {
                    mCtr++;
                    nCtr = i;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {

        int grid[][] = { { 1, 2, 3, 4, 0 }, { 1, 1, 1, 1, 2 }, { 1, 2, 2, 2, 4 }, { 1, 9, 3, 1, 7 } };
        System.out.println("Original matrix - " + Arrays.deepToString(grid));
        int[][] ans = boundarySortMatrix(grid, grid.length, grid[0].length);

        System.out.println("Boundary sorted matrix - " + Arrays.deepToString(ans));
    }
}
