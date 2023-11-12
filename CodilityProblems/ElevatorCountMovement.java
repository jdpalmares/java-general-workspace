import java.util.LinkedList;
import java.util.Queue;

public class ElevatorCountMovement {
    public static void main(String args[]) {
      ElevatorCountMovement t = new ElevatorCountMovement();
      int[] A = {40, 40, 100, 80, 20};
      int[] B = {3, 3, 2, 2, 3};
      int M = 3;
      int X = 5;
      int Y = 200;
        System.out.println(t.solution(A, B, M, X, Y));
    }

    public int solution(int[] A, int[] B, int M, int X, int Y) {
        Queue<People> waitingQueue = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            waitingQueue.offer(new People(A[i], B[i]));
        }
        return getInElv(waitingQueue, X, Y, 0);
    }

    private int getInElv(Queue<People> queue, int X, int Y, int count) {
        int xSum = 0;
        int ySum = 0;
        int floor = 1;

        while (!queue.isEmpty()) {
            Queue<People> elevatorQueue = new LinkedList<>();
            People waitPeople = queue.poll();
            elevatorQueue.add(waitPeople);
            if (xSum + 1 <= X || ySum + waitPeople.getWeight() <= Y) {
                xSum += 1;
                ySum += waitPeople.getWeight();
            }

            while (!elevatorQueue.isEmpty()) {
                People p = elevatorQueue.poll();
                if (floor != p.getGoal()) {
                    floor = p.getGoal();
                    count++;
                }
            }
            if (elevatorQueue.isEmpty()) {
                count++;
                return getInElv(queue, X, Y, count);
            }
        }
        return count;
    }

    public class People {
        private int weight;
        private int goal;

        public People(int weight, int goal) {
            this.weight = weight;
            this.goal = goal;
        }

        public int getWeight() {
            return weight;
        }

        public int getGoal() {
            return goal;
        }

        @Override
        public String toString() {
            return "People{" +
                    "weight=" + weight +
                    ", goal=" + goal +
                    '}';
        }
    }
}