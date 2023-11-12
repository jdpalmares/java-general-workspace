package AdventOfCode2022.Problem11;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MonkeySimulator {

   // Create file
   static class Monkey {
      int monkeyIndex;
      List<Long> startingItems = new ArrayList<Long>();
      String operatorSign;
      int operatorNum;
      int testDivBy;
      int trueMonkeyTo;
      int falseMonkeyTo;
      int inspectCount = 0;

      void inspectAndThrowItemsToOtherMonkey(List<Monkey> monkeys, int lcm) {
         if (!startingItems.isEmpty()) {
            for (Long item : startingItems) {
               long worryLevel = 0;
               if (operatorSign.equals("squared")) {
                  worryLevel = item * item;
               } else if (operatorSign.equals("*")) {
                  worryLevel = item * operatorNum;
               } else if (operatorSign.equals("+")) {
                  worryLevel = item + operatorNum;
               }
               // worryLevel = worryLevel / 3; // only needed for part1
               worryLevel = worryLevel % lcm; // only needed for part2
               if (worryLevel % testDivBy == 0) {
                  // System.out.println("true Throw item value (" + worryLevel + ") to monkey = "
                  // + trueMonkeyTo);
                  monkeys.get(trueMonkeyTo).startingItems.add(worryLevel);
               } else {
                  // System.out.println("false Throw item value (" + worryLevel + ") to monkey = "
                  // + falseMonkeyTo);
                  monkeys.get(falseMonkeyTo).startingItems.add(worryLevel);
               }
               inspectCount++;
            }
            monkeys.get(monkeyIndex).startingItems.clear();
         }
      }

      void printMonkeyInfo() {
         System.out.println("Info for monkey number = " + monkeyIndex + ": ");
         System.out.print("Starting items: ");
         for (Long item : this.startingItems) {
            System.out.print("" + item + ", ");
         }
         System.out.println("");
         System.out.print("Operation: new = old ");
         if (operatorSign.equals("squared")) {
            System.out.print("* old ");
         } else {
            System.out.print(operatorSign + " " + operatorNum);
         }
         System.out.println("");
         System.out.println("Test: divisible by " + testDivBy);
         System.out.println("    If true: throw to monkey " + trueMonkeyTo);
         System.out.println("    If false: throw to monkey " + falseMonkeyTo);
      }
   }

   public static void main(String[] args) throws FileNotFoundException {
      URL path = MonkeySimulator.class.getResource("monkeyinput.txt");
      File file = new File(path.getFile());

      List<Monkey> monkeys = new ArrayList<Monkey>();
      try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
         int monkeyCount = 0;
         while (sc.hasNextLine()) {
            String monkeyDef = sc.nextLine();
            System.out.println("Line value = " + monkeyDef);

            if (monkeyDef.startsWith("Monkey ")) {
               Monkey monkeyI = new Monkey();
               monkeyI.monkeyIndex = monkeyCount;
               while (sc.hasNextLine() && !monkeyDef.equals("")) {
                  monkeyDef = sc.nextLine();
                  System.out.println("Monkey definition value = " + monkeyDef);
                  String startingItemsIndicator = "  Starting items: ";
                  String operationDef = "  Operation: new = old ";
                  String testDef = "  Test: divisible by ";
                  String trueMonkeyDef = "    If true: throw to monkey ";
                  String falseMonkeyDef = "    If false: throw to monkey ";
                  if (monkeyDef.startsWith(startingItemsIndicator)) {
                     String items[] = monkeyDef.substring(startingItemsIndicator.length()).split(",");
                     for (String item : items) {
                        Long itemVal = Long.valueOf(item.trim());
                        if (itemVal != 0) {
                           monkeyI.startingItems.add(itemVal);
                        }
                     }
                  } else if (monkeyDef.startsWith(operationDef)) {
                     String operateValue = monkeyDef.substring(operationDef.length());
                     if (operateValue.equals("* old")) {
                        monkeyI.operatorSign = "squared";
                     } else {
                        String[] operatePair = operateValue.split(" ");
                        monkeyI.operatorSign = operatePair[0];
                        monkeyI.operatorNum = Integer.valueOf(operatePair[1].trim());
                     }
                  } else if (monkeyDef.startsWith(testDef)) {
                     String operateValue = monkeyDef.substring(testDef.length());
                     monkeyI.testDivBy = Integer.valueOf(operateValue.trim());
                  } else if (monkeyDef.startsWith(trueMonkeyDef)) {
                     String trueMonkeyValue = monkeyDef.substring(trueMonkeyDef.length());
                     monkeyI.trueMonkeyTo = Integer.valueOf(trueMonkeyValue.trim());
                  } else if (monkeyDef.startsWith(falseMonkeyDef)) {
                     String falseMonkeyValue = monkeyDef.substring(falseMonkeyDef.length());
                     monkeyI.falseMonkeyTo = Integer.valueOf(falseMonkeyValue.trim());
                  }
               }
               monkeyCount++;
               monkeyI.printMonkeyInfo();
               monkeys.add(monkeyI);
            }
         }
         long top1activeMonkeyScore = 0;
         long top2activeMonkeyScore = 0;
         int roundCheckpoint = 1000;
         List<Integer> checks = new ArrayList<Integer>();
         System.out.print("lcm of ");
         for (Monkey monkey : monkeys) {
            checks.add(monkey.testDivBy);
            System.out.print("" + monkey.testDivBy + " ");
         }
         int lcm = lcm(checks, 0);
         System.out.println(" = " + lcm);
         for (int round = 1; round <= 10000; round++) {
            for (Monkey monkey : monkeys) {
               monkey.inspectAndThrowItemsToOtherMonkey(monkeys, lcm);
            }
            // printMonkeyInventory(round, monkeys); only needed for part1
            if (round == 1 || round == 20 || round == roundCheckpoint) {
               printMonkeyInspectCount(round, monkeys);
               if (round == roundCheckpoint) {
                  roundCheckpoint += 1000;
               }
            }
         }
         for (Monkey monkey : monkeys) {
            if (monkey.inspectCount > top1activeMonkeyScore) {
               top2activeMonkeyScore = top1activeMonkeyScore;
               top1activeMonkeyScore = monkey.inspectCount;
            } else if (monkey.inspectCount > top2activeMonkeyScore) {
               top2activeMonkeyScore = monkey.inspectCount;
            }
         }
         long ans = top1activeMonkeyScore * top2activeMonkeyScore;
         System.out.println("Answer value of " + top1activeMonkeyScore + " * " + top2activeMonkeyScore + " = "
               + ans);
      }
   }

   static void printMonkeyInventory(int round, List<Monkey> monkeys) {
      System.out.println("After round " + round + ", the monkeys are holding items with these worry levels:");
      int monkeyCtr = 0;
      for (Monkey monkey : monkeys) {
         System.out.print("Monkey " + monkeyCtr + ": ");
         for (Long item : monkey.startingItems) {
            System.out.print("" + item + ", ");
         }
         System.out.println("");
         monkeyCtr++;
      }
   }

   static void printMonkeyInspectCount(int round, List<Monkey> monkeys) {
      System.out.println("After round " + round + ", the monkeys are holding items with these worry levels:");
      int monkeyCtr = 0;
      for (Monkey monkey : monkeys) {
         System.out.print("Monkey " + monkeyCtr + " inspected items ");
         System.out.print("" + monkey.inspectCount + " times ");
         System.out.println("");
         monkeyCtr++;
      }
   }

   static int lcm(List<Integer> nums, int index) {
      if (index == nums.size() - 1)
         return nums.get(index);
      int a = nums.get(index);
      int b = lcm(nums, index + 1);
      return (a * b) / gcd(a, b);
   }

   static int gcd(int a, int b) {
      return b == 0 ? a : gcd(b, a % b);
   }
}