// You will be given a list of 32 bit unsigned integers. Flip all the bits ( and ) and 
// return the result as an unsigned integer.

// Function Description Complete the flippingBits function in the editor below.
// flippingBits has the following parameter(s): int n: an integer

// Returns int: the unsigned decimal integer result
// Input Format
// The first line of the input contains , the number of queries.
// Each of the next  lines contain an integer, , to process.

import java.io.*;

public class FlipBitXor {

    // Complete the flippingBits function below.
    static long flippingBits(long n) {
        long bit32Uint = (long) Math.pow(2, 32) - 1;
        System.out.println("Solution 1 = " + (bit32Uint ^ n));
        System.out.println("Solution 2 = " + ((bit32Uint + 1) + (~n)));
        return bit32Uint - n;
    }

    public static void main(String[] args) throws IOException {
        long n = 2147483647;

        long result = flippingBits(n);

        System.out.println(n + "'s 32 bit complement = " + result);
    }
}
