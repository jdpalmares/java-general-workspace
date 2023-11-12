import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RepeatString {
    public static int findAoccur(String s, int n) {
        int strLen = s.length();
        if (s == null || strLen > 100 || strLen < 1)
            throw new IllegalArgumentException();

        int aOccur = 0;
        for (int i = 0; i < strLen; i++) {
            if (s.charAt(i) == 'a')
                aOccur++;
        }

        if (n % strLen == 0) {
            return aOccur * (n / strLen);
        } else {
            int finalOccur = aOccur * (n / strLen);
            for (int i = 0; i < (n % strLen); i++) {
                if (s.charAt(i) == 'a')
                    finalOccur++;
            }
            return finalOccur;
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter a number here : ");

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String s = bufferRead.readLine();
            System.out.println("Sum of Fibonnaci sequence = " + perimeter(new BigInteger(s)).toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}