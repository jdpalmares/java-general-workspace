import java.util.Arrays;
import java.util.Scanner;

class ParticleVelocity {

    public static int particleVelocity(int[] A) {
      int arrlen = A.length;
      int answerLimit = 1000000000; //limit as told in the problem
      if(arrlen > 100) return -1;
      int ans = 0; //number of periods in time when movement of particle was stable
      for (int i = 0; i < arrlen; i++) {
         for (int ctr = 0; i + 2 < arrlen && A[i + 1] - A[i] == A[i + 2] - A[i + 1]; i++) {
               ctr++;
               ans += ctr;
         }
      }
      if(ans > answerLimit) return -1;
      return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] particles = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        scanner.close();
        System.out.println(particleVelocity(particles));
    }

}