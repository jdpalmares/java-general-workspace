import java.util.Arrays;
  
class ArithSlicesCtr {
      
    static final int MAX = 1000001;
  
    static int numofAP(int a[], int n)
    {
        int minarr = Integer.MAX_VALUE;
        int maxarr = Integer.MIN_VALUE;;
  
        for (int i = 0; i < n; i++) {
            minarr = Math.min(minarr, a[i]);
            maxarr = Math.max(maxarr, a[i]);
        }
  
        int dp[] = new int[n];
        int sum[] = new int[MAX];
  
        int ans = n + 1;
  
        for (int d = (minarr - maxarr); d <= (maxarr - minarr); d++) {
            Arrays.fill(sum, 0);

            for (int i = 0; i < n; i++) {
                dp[i] = 1;
                if (a[i] - d >= 1 && a[i] - d <= 1000000)
                    dp[i] += sum[a[i] - d];  
                ans += dp[i] - 1;
                sum[a[i]] += dp[i];
            }
        }
        return ans;
    }
      
    // Driver code
    public static void main(String[] args)
    {
        int arr[] = { 1, 2, 3 };
        int n = arr.length;
          
        System.out.println(numofAP(arr, n));
    }
}