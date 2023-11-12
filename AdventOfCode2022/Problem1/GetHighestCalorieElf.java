package AdventOfCode2022.Problem1;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GetHighestCalorieElf {

   public static void main(String[] args) throws FileNotFoundException {
      URL path = GetHighestCalorieElf.class.getResource("input.txt");
      File file = new File(path.getFile());

      int elfCount = 1; // from first elf
      int highestCalTotal1 = 0, highestCalTotal2 = 0, highestCalTotal3 = 0;
      int elfPositionWithHighestCal1 = 1, elfPositionWithHighestCal2 = 2, elfPositionWithHighestCal3 = 3;

      try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
         int subTotal = 0;
         while (sc.hasNextLine()) {
            String lineVal = sc.nextLine();
            System.out.println("Line value = " + lineVal);
            if (!lineVal.equals("")) {
               subTotal += Integer.valueOf(lineVal);
               System.out.println("subtotal currently = " + subTotal);
            } else {
               System.out.println("subtotal = " + subTotal);
               System.out.println("elfCount = " + elfCount);
               if (subTotal > highestCalTotal1) {
                  highestCalTotal3 = highestCalTotal2;
                  elfPositionWithHighestCal3 = elfPositionWithHighestCal2;
                  highestCalTotal2 = highestCalTotal1;
                  elfPositionWithHighestCal2 = elfPositionWithHighestCal1;
                  highestCalTotal1 = subTotal;
                  elfPositionWithHighestCal1 = elfCount;
               } else if (subTotal > highestCalTotal2) {
                  highestCalTotal3 = highestCalTotal2;
                  elfPositionWithHighestCal3 = elfPositionWithHighestCal2;
                  highestCalTotal2 = subTotal;
                  elfPositionWithHighestCal2 = elfCount;
               } else if (subTotal > highestCalTotal3) {
                  highestCalTotal3 = subTotal;
                  elfPositionWithHighestCal3 = elfCount;
               }
               subTotal = 0;
               elfCount++;
            }
         }
         if (subTotal > highestCalTotal1) {
            highestCalTotal3 = highestCalTotal2;
            elfPositionWithHighestCal3 = elfPositionWithHighestCal2;
            highestCalTotal2 = highestCalTotal1;
            elfPositionWithHighestCal2 = elfPositionWithHighestCal1;
            highestCalTotal1 = subTotal;
            elfPositionWithHighestCal1 = elfCount;
         } else if (subTotal > highestCalTotal2) {
            highestCalTotal3 = highestCalTotal2;
            elfPositionWithHighestCal3 = elfPositionWithHighestCal2;
            highestCalTotal2 = subTotal;
            elfPositionWithHighestCal2 = elfCount;
         } else if (subTotal > highestCalTotal3) {
            highestCalTotal3 = subTotal;
            elfPositionWithHighestCal3 = elfCount;
         }
         elfCount++;

         sc.close(); // closes the scanner

         System.out.println("Elf with 1st Highest Calorie = " + elfPositionWithHighestCal1);
         System.out.println("1st Highest Calorie Total = " + highestCalTotal1);

         System.out.println("Elf with 2nd Highest Calorie = " + elfPositionWithHighestCal2);
         System.out.println("2nd Highest Calorie Total = " + highestCalTotal2);

         System.out.println("Elf with 3rd Highest Calorie = " + elfPositionWithHighestCal3);
         System.out.println("3rd Highest Calorie Total = " + highestCalTotal3);

         int totalTop3Cal = highestCalTotal1 + highestCalTotal2 + highestCalTotal3;
         System.out.println("Total calories of top 3 = " + totalTop3Cal);
      }
   }
}