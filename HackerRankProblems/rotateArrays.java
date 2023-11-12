
// you can also use imports, for example:
// import java.util.*;
import java.util.Arrays;
// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class rotateArrays {
    // Function to right rotate arr[]
    // of siz N by D
    void rightRotate(int arr[], int d, int n) {

        // to use as left rotation
        d = n - d;
        d = d % n;
        System.out.println("d final value = " + d);
        int i, j, k, temp;
        int g_c_d = gcd(d, n);
        System.out.println("GCD = " + g_c_d);
        for (i = 0; i < g_c_d; i++) {

            // move i-th values of blocks
            temp = arr[i];
            System.out.println("temp = " + temp);
            j = i;
            System.out.println("j in for = " + j);
            while (true) {
                k = j + d;
                System.out.println("k in while1 = " + k);
                if (k >= n) {
                    k = k - n;
                    System.out.println("k in >=n = " + k);
                }
                if (k == i) {
                    System.out.println("k broke at value = " + k);
                    break;
                }
                arr[j] = arr[k];
                System.out.println("Array after movement in while = " + Arrays.toString(arr));
                j = k;
                System.out.println("j in while = " + j);
            }
            arr[j] = temp;
            System.out.println("Array after movement in for = " + Arrays.toString(arr));
        }
    }

    // UTILITY FUNCTIONS

    // Function to get gcd of a and b
    int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    // Driver program to test above functions
    public static void main(String[] args) {
        test1 rotate = new test1();
        int arr[] = { 1, 2, 3, 4, 5 };
        rotate.rightRotate(arr, 2, arr.length);
        System.out.println("Array after function = " + Arrays.toString(arr));
        int arr2[] = { 3, 8, 9, 7, 6 };
        rotate.rightRotate(arr2, 3, arr2.length);
        System.out.println("Array2 after function = " + Arrays.toString(arr2));
        int arr3[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        rotate.rightRotate(arr3, 2, arr3.length);
        System.out.println("Array3 after function = " + Arrays.toString(arr3));
    }

}