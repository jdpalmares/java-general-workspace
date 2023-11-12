import java.util.HashSet;

public class ElevatorCountMovementBackup {
   /*A = Weights array
   B = Target floor
   M = No of floors
   X = Max capacity
   Y = Max weight
   */
 public int solution(int[] A, int[] B, int M, int X, int Y) {
  int length = A.length;
  int i = 0;
  int stops = 0;
  while(i < length) {
      long groupWeight = 0;
      int cap = 0;

      HashSet<Integer> uniqueFloors = new HashSet<Integer>();
      while(cap < X && i < length && groupWeight + A[i] <= Y) {
          groupWeight = groupWeight + A[i];
          uniqueFloors.add(B[i]);
          i++;
          cap++;
      }
      stops = stops + uniqueFloors.size() + 1;
  }
  return stops;
 }

 public static void main(String args[]) {
   ElevatorCountMovementBackup t = new ElevatorCountMovementBackup();
   int[] A = {40, 40, 100, 80, 20};
      int[] B = {3, 3, 2, 2, 3};
      int M = 3;
      int X = 5;
      int Y = 200;
   System.out.println(t.solution(A, B, M, X, Y));
}
}