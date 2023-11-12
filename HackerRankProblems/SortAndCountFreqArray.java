// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class SortAndCountFreqArray {
    public int solution(int[] A) {
        // write your code in Java SE 8
        int arrLen = A.length;
        if (A == null || arrLen == 0 || arrLen % 2 == 0)
            throw new IllegalArgumentException();
        if (arrLen == 1)
            return A[0];
        A = countingSort(A);
        int numOccur, numIndex = 0, numLastPos;
        while (true) {
            numOccur = countOccurrences(A, A[numIndex]);
            if (numOccur % 2 != 0)
                return A[numIndex];
            numLastPos = last(A, numIndex, arrLen - 1, A[numIndex], arrLen);
            numIndex = numLastPos + 1;
        }
    }

    private static int[] countingSort(int[] numbers) {
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max)
                max = numbers[i];
        }

        int[] sortedNumbers = new int[max + 1];

        for (int i = 0; i < numbers.length; i++) {
            sortedNumbers[numbers[i]]++;
        }

        int insertPosition = 0;

        for (int i = 0; i <= max; i++) {
            for (int j = 0; j < sortedNumbers[i]; j++) {
                numbers[insertPosition] = i;
                insertPosition++;
            }
        }
        return numbers;
    }

    private static int countOccurrences(int arr[], int x) {
        int res = 0;
        for (int i = 0; i < arr.length; i++)
            if (x == arr[i])
                res++;
        return res;
    }

    private static int last(int arr[], int low, int high, int x, int n) {
        if (high >= low) {
            int mid = low + (high - low) / 2;
            if ((mid == n - 1 || x < arr[mid + 1]) && arr[mid] == x)
                return mid;
            else if (x < arr[mid])
                return last(arr, low, (mid - 1), x, n);
            else
                return last(arr, (mid + 1), high, x, n);
        }
        return -1;
    }
}
