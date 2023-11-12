// Karl loves playing games on social networking sites. 
// His current favorite is CandyMaker, where the goal is to make candies.
// Karl just started a level in which he must accumulate candies starting 
// with  machines and  workers. In a single pass, he can make  candies. 
// After each pass, he can decide whether to spend some of his candies to buy 
// more machines or hire more workers. Buying a machine or hiring a worker costs units, 
// and there is no limit to the number of machines he can own or workers he can employ.
// Karl wants to minimize the number of passes to obtain the required number of candies 
// at the end of a day. Determine that number of passes.
// For example, Karl starts with  machine and  workers. The cost to purchase or hire,  
// and he needs to accumulate  candies. He executes the following strategy:

// Make  candies. Purchase two machines. Make  candies. 
// Purchase  machines and hire  workers. Make  candies. Retain all  candies.
// Make  candies. With yesterday's production, Karl has  candies.
// It took  passes to make enough candies.

// Function Description

// Complete the minimumPasses function in the editor below.
// The function must return a long integer representing the minimum number of passes required.
// minimumPasses has the following parameter(s):
// m: long integer, the starting number of machines
// w: long integer, the starting number of workers
// p: long integer, the cost of a new hire or a new machine
// n: long integer, the number of candies to produce

import java.lang.Math;
import java.lang.Long;

public class MakeCandiesGreedy {
    static long minimumPasses(long mchneCtr, long wrkerCtr, long resCost, long goalCandy) {
        long passes = 0;
        long candy = 0;
        long longMax = Long.MAX_VALUE;
        long run = Long.MAX_VALUE;
        long step;

        while (candy < goalCandy) {
            // Determine step of the process
            // Check if high resource or high cost requirement
            step = (mchneCtr > longMax / wrkerCtr) ? 0 : (resCost - candy) / (mchneCtr * wrkerCtr);

            if (step <= 0) {
                // buy out resources (machine or worker)
                long mw = candy / resCost;

                // (determine on where to put resource depending on what is larger)
                // the larger machine, add worker, and vice versa
                if (mchneCtr >= wrkerCtr + mw) {
                    wrkerCtr += mw;
                } else if (wrkerCtr >= mchneCtr + mw) {
                    mchneCtr += mw;
                } else {
                    // Greedy case in which machine and worker will be increased fairly
                    // in order to maximize product values must be the almost the same
                    long total = mchneCtr + wrkerCtr + mw;
                    mchneCtr = total / 2;
                    wrkerCtr = total - mchneCtr;
                }

                // decrease candy by resource cost since it was used for buying resources
                candy %= resCost;
                step = 1;
            }

            // add passes depending on step added
            passes += step;

            if (step * mchneCtr > longMax / wrkerCtr) {
                candy = longMax;
            } else {
                // Generate candy at the end of the step
                candy += step * mchneCtr * wrkerCtr;
                // backtrack to where goal was achieved if cost is high
                // just generate candies until goal is achieved
                long formRun = (long) Math.ceil((goalCandy - candy) / (mchneCtr * wrkerCtr * 1.0));
                // predetermine how many passes/runs needed (useful if cost is high)
                run = Math.min(run, (long) (passes + formRun));
            }
        }

        return Math.min(passes, run);
    }

    public static void main(String[] args) {
        long mchneCtr = 2;
        long wrkerCtr = 1;
        long resCost = 5000;
        long goalCandy = 12;

        long result = minimumPasses(mchneCtr, wrkerCtr, resCost, goalCandy);

        System.out.println("Answer is = " + result);
    }
}
