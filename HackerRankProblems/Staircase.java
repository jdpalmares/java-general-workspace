// Problem 2: Staircase
// Given a staircase of size n = 10
// T
// TT
// TTT
// TTTT
// TTTTT
// TTTTTT
// TTTTTTT
// TTTTTTTT
// TTTTTTTTT
// TTTTTTTTTT
// Where: Both base and height are equal to n . Each level is drawn using letter T and spaces.
// The last line (or the base) should not have any leading and trailing spaces.
// Here’s what you have to do: Complete the function staircase .
// The function takes the following parameter(s): int n : an integer
// Expected output/return: Print a staircase as described above.
//removed all imports since it was unnecessary

class Staircase {

   // removed static declaration since usage in main was not static
   // (caused warnings and a general bad coding convention/practice)
   public void staircase(Integer n) {
      int TCtr = n; // use a counter to determine how many Ts to be printed in a line
      // use a nested for loop for readability and save space complexity
      for (int i = 1; i <= n; i++) {
         for (int j = 1; j <= n; j++) {
            if (j >= TCtr)
               System.out.print("T");
            else
               System.out.print(" ");
         }
         TCtr--;
         System.out.println();
      }
   }

   public static void main(String[] args) {
      Staircase cls = new Staircase();

      /** Test Cases */

      // Expected output/return:
      // The staircase based on the given integer value.
      cls.staircase(10);
      cls.staircase(50);
      cls.staircase(101);
   }

}
