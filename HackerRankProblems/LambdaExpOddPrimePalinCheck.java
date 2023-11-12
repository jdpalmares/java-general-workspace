// This Java 8 challenge tests your knowledge of Lambda expressions!

// Write the following methods that return a lambda expression performing a specified action:
// PerformOperation isOdd():
//     The lambda expression must return  if a number is odd or  if it is even.
// PerformOperation isPrime(): 
//     The lambda expression must return  if a number is prime or  if it is composite.
// PerformOperation isPalindrome(): 
//     The lambda expression must return  if a number is a palindrome or  if it is not.

// Input Format Input is handled for you by the locked stub code in your editor.

// Output Format The locked stub code in your editor will print  lines of output.

import java.io.*;
import java.util.*;

interface PerformOperation {
    boolean check(int a);
}

class MyMath {
    public boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

    public PerformOperation isOdd() {
        return (int a) -> a % 2 != 0;
    }

    public PerformOperation isPrime() {
        return (int a) -> java.math.BigInteger.valueOf(a).isProbablePrime(1);
        // Alternate solution
        // return (int a) -> {
        // if (a < 2) return false;
        // for (int i = 2; i * i <= a; i++)
        // if (a % i == 0) return false;
        // return true;
        // };
    }

    public PerformOperation isPalindrome() {
        return (int a) -> Integer.toString(a).equals(new StringBuilder(Integer.toString(a)).reverse().toString());
        // Alternate solution and might be faster
        // return (int a) -> {
        // String str = Integer.toString(a);
        // int i = 0;
        // int j = str.length() - 1;
        // for ( ; i <= j; ++i, --j) {
        // if (str.charAt(i) != str.charAt(j)) return false;
        // }
        // return true;
        // };
    }
}

public class LambdaExpOddPrimePalinCheck {

    public static void main(String[] args) throws IOException {
        MyMath ob = new MyMath();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        while (T-- > 0) {
            String s = br.readLine().trim();
            StringTokenizer st = new StringTokenizer(s);
            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (ch == 1) {
                op = ob.isOdd();
                ret = ob.checker(op, num);
                ans = (ret) ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.isPrime();
                ret = ob.checker(op, num);
                ans = (ret) ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = ob.isPalindrome();
                ret = ob.checker(op, num);
                ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

            }
            System.out.println(ans);
        }
    }
}