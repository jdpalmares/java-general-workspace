package AdventOfCode2022.Problem10;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CommCycleCalc {

   public static void main(String[] args) throws FileNotFoundException {
      URL path = CommCycleCalc.class.getResource("radiocpuinput.txt");
      File file = new File(path.getFile());

      try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
         int radioCycleValue = 1;
         int currentRadioCycle = 1;
         int ans = 0;
         int signalStrengthCheckpoint = 20;
         int currentDelayDueToAddx = 0;
         Map<Integer, Integer> addxMap = new HashMap<Integer, Integer>();
         StringBuilder signalSBuilder = new StringBuilder();
         while (currentRadioCycle < 241) {
            if (currentDelayDueToAddx <= 0 && sc.hasNextLine()) {
               String radioCmd = sc.nextLine();
               // System.out.println("Line value = " + radioCmd);

               if (!radioCmd.equals("noop")) {
                  addxMap.put(currentRadioCycle + 2, Integer.valueOf(radioCmd.split(" ")[1]));
                  currentDelayDueToAddx++;
               }
            } else {
               currentDelayDueToAddx--;
            }
            if (addxMap.containsKey(currentRadioCycle)) {
               radioCycleValue += addxMap.get(currentRadioCycle);
               // System.out.println("Radio value at end " + currentRadioCycle + " = " +
               // radioCycleValue);
            }

            if (currentRadioCycle == signalStrengthCheckpoint) {
               // System.out.println(
               // "Signal Strength at " + currentRadioCycle + " = " + (radioCycleValue *
               // signalStrengthCheckpoint));
               ans += (radioCycleValue * signalStrengthCheckpoint);
               // System.out.println("Subtotal Signal Strength at " + currentRadioCycle + " = "
               // + ans);
               signalStrengthCheckpoint += 40;
            }
            setPixelsBuilder(radioCycleValue, currentRadioCycle, signalSBuilder);
            currentRadioCycle++;
         }
         // print problem1
         System.out.println("Total Signal Strength at each checkpoint = " + ans);
         // print problem2
         System.out.println("Test print Signal sprites: ");
         for (int x = 0; x < 220; x++) {
            System.out.println(signalSBuilder.substring(x, x + 39));
            x += 39;
         }
      }
   }

   // So, by carefully timing the CPU instructions and the CRT drawing operations,
   // you should be able to determine whether the sprite is visible the instant
   // each pixel is drawn. If the sprite is positioned such that one of its three
   // pixels is the pixel currently being drawn, the screen produces a lit pixel
   // (#); otherwise, the screen leaves the pixel dark (.).
   // problem2
   static void setPixelsBuilder(int radioCycleValue, int currentRadioCycle, StringBuilder signalSBuilder) {
      int radioCycleModulo = currentRadioCycle % 40;

      // System.out.println("Value of signalAt = " + radioCycleValue +
      // " at current radio cycle " + radioCycleModulo);

      if ((radioCycleModulo - 1) == radioCycleValue ||
            (radioCycleModulo - 1) == radioCycleValue - 1 ||
            (radioCycleModulo - 1) == radioCycleValue + 1) {
         signalSBuilder.append("#");
         // System.out.println("draw pixel light at current radio cycle " +
         // currentRadioCycle);// System.out.print("#");
      } else {
         signalSBuilder.append(".");
         // System.out.println("draw pixel dark at current radio cycle " +
         // currentRadioCycle); // System.out.print("#");
      }
   }
}