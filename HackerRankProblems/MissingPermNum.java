
// you can also use imports, for example:

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class MissingPermNum {
    public int solution(int[] A) {
        long maxNum = A.length + 1;
        long sumOfMaxNum = (maxNum * (maxNum + 1)) / 2;
        long sumArr = 0;
        for (int i = 0; i < maxNum - 1; i++)
            sumArr += A[i];
        // completed permutation - sum of all elements in the array
        return (int) (sumOfMaxNum - sumArr);
    }

    public static void main(String[] args) {
        /* Let us create the example graph discussed above */
        int a[] = new int[] { 2, 3, 1, 5 };
        System.out.println(new MissingPermNum().solution(a));
    }
}
