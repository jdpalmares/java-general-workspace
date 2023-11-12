import java.util.ArrayList;

public class ElevatorCountMovementCsharpAlt {
   /*A = Weights array
   B = Target floor
   M = No of floors
   X = Max capacity
   Y = Max weight
   */
 public int solution(int[] A, int[] B, int M, int X, int Y) {
   int totalStops = 0;
   long totalWeightPerRound = 0;
   int maxPersonsCount = 0;
   ArrayList<Integer> lstFloors = new ArrayList<Integer>();
   int currPerson = 0;
   boolean startLift = false;
   while (currPerson < A.length)
   {
       if ((totalWeightPerRound + A[currPerson]) <= Y && (maxPersonsCount+1) <= X)
       {
           totalWeightPerRound += A[currPerson];
           maxPersonsCount++;
           lstFloors.add(B[currPerson]);
           if (currPerson == A.length - 1)
               startLift = true;

           currPerson++;
       }
       else
       {
           startLift = true;
       }

       if (startLift)
       {
           totalStops += lstFloors.size() + 1;
           lstFloors.clear();
           maxPersonsCount = 0;
           totalWeightPerRound = 0;
           startLift = false;
       }
   }

   return totalStops;
 }

 public static void main(String args[]) {
   ElevatorCountMovementCsharpAlt t = new ElevatorCountMovementCsharpAlt();
   int[] A = {40, 40, 100, 80, 20};
      int[] B = {3, 3, 2, 2, 3};
      int M = 3;
      int X = 5;
      int Y = 200;
   System.out.println(t.solution(A, B, M, X, Y));
}
}