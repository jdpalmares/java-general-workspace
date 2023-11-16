import java.util.HashSet;
import java.util.Set;

// You have been provided the “Robot” and “Main” classes below.
// Using these classes, you have been tasked with writing an algorithm to guide a robot through a maze.
// An example of a possible maze configuration is provided below.
// You are not permitted to alter the “Robot” class, and can only add code to the main() method provide in the Main class.


public class Robot {
        //Please do not write any new code in this class
        //A brief description is provided within each method
        //describing its function
    
        public boolean isPathClear() {
            //returns true if no wall is in front of the robot
            //returns false if a wall is in front of the robot
        }
    
        public void turnLeft() {
            //rotates the robot left by 90 degrees (counterclockwise)
        }
    
        public void moveForward() {
            //moves the robot forward one square
            //throws error if the robot hits a wall
        }
        
        public boolean hasExited() {
            //return true if the robot has exited the maze
        }
}

class Main {
    public static void main(String[] args) {
        Robot rob = new Robot();
        //Your code here
        //put current hashcode as position since there is no certainty where the robot starts nor
        //any specifications about the positionining of the maze. Hence, cannot use x-y coordinates
        Set<String> visited = new HashSet<>();
        while (!rob.hasExited()) {
            String currentPosition = "Pos" + System.identityHashCode(rob);
            if (visited.contains(currentPosition)) {
                rob.turnLeft(); // If visited, turn left
            } else if (rob.isPathClear()) {
                // Move forward, mark the square as visited, and record the movement
                rob.moveForward();
                visited.add(currentPosition);
            } else {
                rob.turnLeft();
            }
        }
    }
}
