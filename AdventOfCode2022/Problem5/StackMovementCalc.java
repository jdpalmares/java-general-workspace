package AdventOfCode2022.Problem5;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

//need more study especially on parsing input
//https://www.reddit.com/r/adventofcode/comments/zcxid5/2022_day_5_solutions/
public class StackMovementCalc {

   public static void main(String[] args) throws FileNotFoundException {
      URL path = StackMovementCalc.class.getResource("movesInput.txt");
      File file = new File(path.getFile());
      List<List<Character>> stacks = fillUpStacks();

      try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {

         String ans = "";
         while (sc.hasNextLine()) {
            String crateMovement = sc.nextLine();
            System.out.println("Line value = " + crateMovement);

            // get movement info
            int moveQuantity = getPosition(crateMovement, "move ");
            System.out.println("Move how many = " + moveQuantity);
            int fromStack = getPosition(crateMovement, "from ") - 1;
            System.out.println("fromStack = " + fromStack);
            int toStack = getPosition(crateMovement, "to ") - 1;
            System.out.println("toStack = " + toStack);

            List<Character> fromStackRemove = stacks.get(fromStack);
            System.out.println("from stack structure = " + fromStackRemove);
            int fromStackRemoveSize = fromStackRemove.size() - 1;
            System.out.println("size of from stack = " + fromStackRemoveSize);
            List<Character> toStackAdd = stacks.get(toStack);
            System.out.println("to stack structure = " + toStackAdd);

            // for problem 1
            // for (int i = fromStackRemoveSize; i > fromStackRemoveSize - moveQuantity;
            // i--) {
            // for problem 2
            for (int i = (fromStackRemoveSize + 1) - moveQuantity; i <= fromStackRemoveSize; i++) {
               Character crateChar = fromStackRemove.get(i);
               System.out.println("crateChar to be moved = " + crateChar);
               toStackAdd.add(crateChar);
            }
            fromStackRemove = fromStackRemove.subList(0, ((fromStackRemoveSize + 1) - moveQuantity));
            System.out.println("from stack structure after movement = " + fromStackRemove);
            System.out.println("to stack structure after movement = " + toStackAdd);

            stacks.set(fromStack, fromStackRemove);
            stacks.set(toStack, toStackAdd);
         }
         StringBuilder ansBuilder = new StringBuilder();
         for (int i = 0; i < stacks.size(); i++) {
            List<Character> currStack = stacks.get(i);
            ansBuilder.append(currStack.get(currStack.size() - 1));
         }

         System.out.println("Answer after moves are finished = " + ansBuilder.toString());
      }
   }

   static List<List<Character>> fillUpStacks() {
      List<List<Character>> stacks = new ArrayList<List<Character>>();
      List<Character> stack1 = new ArrayList<Character>(
            Arrays.asList(new Character[] { 'D', 'L', 'J', 'R', 'V', 'G', 'F' }));
      stacks.add(stack1);
      List<Character> stack2 = new ArrayList<Character>(
            Arrays.asList(new Character[] { 'T', 'P', 'M', 'B', 'V', 'H', 'J', 'S' }));
      stacks.add(stack2);
      List<Character> stack3 = new ArrayList<Character>(
            Arrays.asList(new Character[] { 'V', 'H', 'M', 'F', 'D', 'G', 'P', 'C' }));
      stacks.add(stack3);
      List<Character> stack4 = new ArrayList<Character>(
            Arrays.asList(new Character[] { 'M', 'D', 'P', 'N', 'G', 'Q' }));
      stacks.add(stack4);
      List<Character> stack5 = new ArrayList<Character>(Arrays.asList(new Character[] { 'J', 'L', 'H', 'N', 'F' }));
      stacks.add(stack5);
      List<Character> stack6 = new ArrayList<Character>(
            Arrays.asList(new Character[] { 'N', 'F', 'V', 'Q', 'D', 'G', 'T', 'Z' }));
      stacks.add(stack6);
      List<Character> stack7 = new ArrayList<Character>(Arrays.asList(new Character[] { 'F', 'D', 'B', 'L' }));
      stacks.add(stack7);
      List<Character> stack8 = new ArrayList<Character>(
            Arrays.asList(new Character[] { 'M', 'J', 'B', 'S', 'V', 'D', 'N' }));
      stacks.add(stack8);
      List<Character> stack9 = new ArrayList<Character>(Arrays.asList(new Character[] { 'G', 'L', 'D' }));
      stacks.add(stack9);
      return stacks;
   }

   static String calcMoveCrate(List<char[]> stacks, String crateMovement) {
      String ans = "";

      return ans;
   }

   static int getPosition(String input, String keyWord) {
      int afterCharPos = input.lastIndexOf(keyWord) + keyWord.length();
      char inputChar = input.charAt(afterCharPos);
      if (afterCharPos + 1 < input.length()) {
         char doubeDigitCheckNum = input.charAt(afterCharPos + 1);
         if (Character.getNumericValue(doubeDigitCheckNum) == -1) {
            return Character.getNumericValue(inputChar);
         } else {
            String doubledigitNum = "" + inputChar + doubeDigitCheckNum;
            return Integer.parseInt(doubledigitNum);
         }
      } else {
         return Character.getNumericValue(inputChar);
      }
   }
}

// JAVA SOLUTION FOR STUDYING
// List<String> lines = Files.readAllLines(Path.of("input.txt"));
// List<LinkedList<String>> stack = new ArrayList<>();
// for(int i = 0; i < 9; i++) {
// stack.add(new LinkedList<>());
// }

// // Parse crates:
// String line;
// while(!(line = lines.remove(0)).equals("")) {
// for(int i = 0; i < 9; i++) {
// String c = StringUtils.rightPad(line, 20).substring((i*3)+i+1, (i*3)+i+2);
// if(!c.equals(" ")) stack.get(i).add(c);
// }
// }

// // Process moves:
// while(!lines.isEmpty()) {
// int[] nums = Arrays.stream(lines.remove(0).split(" "))
// .filter(StringUtils::isNumeric)
// .mapToInt(Integer::parseInt)
// .toArray();

// LinkedList<String> toMove = new LinkedList<>();
// for(int i = 0; i < nums[0]; i++) {
// // Part 1: toMove.add(stack.get(nums[1]-1).remove());
// toMove.addFirst(stack.get(nums[1]-1).remove());
// }
// for(int i = 0; i < nums[0]; i++) {
// stack.get(nums[2]-1).addFirst(toMove.remove());
// }
// }

// //Print result:
// stack.stream().map(LinkedList::peek).forEach(System.out::print);