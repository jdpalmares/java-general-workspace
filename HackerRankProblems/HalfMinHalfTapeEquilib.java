
// you can also use imports, for example:
// import java.util.*;

class HalfMinHalfTapeEquilib {
    private int maxArrLen = 100000;

    public int solution(int[] A) {
        int arrLen = A.length;
        int ans = Integer.MAX_VALUE;

        if (arrLen < 2 || arrLen > maxArrLen)
            throw new IllegalArgumentException();

        // Create a temporary array for the sum per each item in the array parameter
        int[] arrSum = new int[arrLen];
        arrSum[0] = A[0];
        for (int i = 1; i < arrLen; i++) {
            arrSum[i] = A[i] + arrSum[i - 1];
        }

        int allSum = arrSum[arrLen - 1];

        for (int i = 0; i < arrLen - 1; i++) {
            // left side sum - right side sum (total - left side sum)
            int tempDif = Math.abs(arrSum[i] - (allSum - arrSum[i]));
            if (ans > tempDif)
                ans = tempDif;
        }
        return ans;
    }
}
