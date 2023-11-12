import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// The following program prints,
// a)  “Happy Face” text when you feed a child with “Ice Cream” and
// b)  “Angry Face” text when you feed a child with “Salad” and
// c)  “Normal Face” text when you feed a child with “Milk”
// d)  "Error Face" text if you do not feed child with either "Ice Cream", "Salad" or "Milk"

public class FedChild {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Feed Child With Ice Cream, Salad or Milk To See Child's Face Reaction : ");
        String food = reader.readLine();
        if (food.equalsIgnoreCase("Ice Cream")) {
            System.out.println("Happy" + " " + "Face");
        } else if (food.equalsIgnoreCase("Salad")) {
            System.out.println("Angry" + " " + "Face");
        } else if (food.equalsIgnoreCase("Milk")) {
            System.out.println("Normal" + " " + "Face");
        } else {
            System.out.println("Error" + " " + "Face");
        }
    }
    
    // There are different ways you can refactor above code and remove/replace “if else if” condition and print the same output.
    // Write code snippets of minimum 3 possible ways you can think the above code can be refactored and explain each approach.

    public static void main2(String[] args) throws IOException { //using switch
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Feed Child With Ice Cream, Salad or Milk To See Child's Face Reaction : ");
        String food = reader.readLine();
        switch (food.toLowerCase()) {
            case "ice cream":
                System.out.println("Happy" + " " + "Face");
                break;
            case "salad":
                System.out.println("Angry" + " " + "Face");
                break;
            case "milk":
                System.out.println("Normal" + " " + "Face");
                break;
            default:
                System.out.println("Error" + " " + "Face");
        }
    }

    public static void main3(String[] args) throws IOException { //using hashmap
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Feed Child With Ice Cream, Salad or Milk To See Child's Face Reaction : ");
        String food = reader.readLine();
        Map<String, String> faceMap = new HashMap<>();
        faceMap.put("ice cream", "Happy" + " " + "Face");
        faceMap.put("salad", "Angry" + " " + "Face");
        faceMap.put("milk", "Normal" + " " + "Face");

        System.out.println(faceMap.getOrDefault(food.toLowerCase(), "Error" + " " + "Face"));
    }

    public static void main4(String[] args) throws IOException { //using 2 arrayLists and ternary operator
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Feed Child With Ice Cream, Salad or Milk To See Child's Face Reaction : ");
        String food = reader.readLine();
        List<String> foodList = new ArrayList<>();
        List<String> faceList = new ArrayList<>();
        foodList.add("ice cream");
        faceList.add("Happy" + " " + "Face");
        foodList.add("salad");
        faceList.add("Angry" + " " + "Face");
        foodList.add("milk");
        faceList.add("Normal" + " " + "Face");

        int foodIdx = foodList.indexOf(food.toLowerCase());

        String childFace = (foodIdx != -1) ? faceList.get(foodIdx) : "Error" + " " + "Face";
        System.out.println(childFace);
    }

    public static void main5(String[] args) throws IOException { //using ternary operators
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Feed Child With Ice Cream, Salad or Milk To See Child's Face Reaction : ");
        String food = reader.readLine();

        System.out.println(
            food.equalsIgnoreCase("Ice Cream") ? "Happy" + " " + "Face" :
            food.equalsIgnoreCase("Salad") ? "Angry" + " " + "Face" :
            food.equalsIgnoreCase("Milk") ? "Normal" + " " + "Face" :
            "Error" + " " + "Face"
        );
    }

    enum Food {
        ICE_CREAM("Happy" + " " + "Face"),
        SALAD("Angry" + " " + "Face"),
        MILK("Normal" + " " + "Face");

        String face;

        Food(String face) {
            this.face = face;
        }
    }

    public static void main6(String[] args) throws IOException { //using enums
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Feed Child With Ice Cream, Salad or Milk To See Child's Face Reaction : ");
        String food = reader.readLine();

        try {
            Food fedFood = Food.valueOf(food.toUpperCase().trim().replace(" ", "_"));
            System.out.println(fedFood.face);
        } catch (IllegalArgumentException e) {
            System.out.println("Error" + " " + "Face");
        }
    }
}