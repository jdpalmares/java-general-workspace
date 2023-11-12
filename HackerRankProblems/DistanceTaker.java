// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class DistanceTaker {
    public int solution(int X, int Y, int D) {
        // write your code in Java SE 8
        int inputLimit = 1000000000; // one billion
        if ((X > inputLimit || X < 1) || (Y > inputLimit || Y < 1) || (D > inputLimit || D < 1))
            throw new IllegalArgumentException();

        // get actual distance from point a to point b
        int actualJumpDist = Y - X;
        // if actual distance is divisible by jump, divide. Else, divide and add 1
        // (to make it greater)
        int jumpTimes = actualJumpDist / D; // for readable code
        return (actualJumpDist % D == 0) ? jumpTimes : jumpTimes + 1;
    }
}
