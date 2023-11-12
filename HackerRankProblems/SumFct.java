import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumFct {
    public static BigInteger perimeter(BigInteger n) {
        BigInteger zero = BigInteger.ZERO;
        BigInteger one = BigInteger.ONE;

        BigInteger a = zero;
        BigInteger b = one;
        BigInteger c = one;
        BigInteger sum = zero;

        if (n == null)
            throw new IllegalArgumentException();
        if (n.compareTo(zero) < 0)
            throw new IllegalArgumentException();

        // Iterative Answer
        // do {
        // c = a.add(b);
        // a = b;
        // b = c;
        // n = n.subtract(one);
        // // System.out.println("a = " + a.toString());
        // // System.out.println("b = " + b.toString());
        // // System.out.println("c = " + c.toString());
        // // System.out.println("n = " + n.toString());
        // } while (n.compareTo(zero) >= 0);
        // sum = sum.add(c.subtract(one));

        // Dynamic Programming Answer
        // BigInteger fibDPArr[] = new BigInteger[n.intValue() + 1];
        // fibDPArr[0] = zero;
        // fibDPArr[1] = one;
        // for (int i = 2; i < n.intValue() + 2; i++) {
        // fibDPArr[i] = fibDPArr[i - 1].add(fibDPArr[i - 2]);
        // }
        // sum = fibDPArr[n.intValue()] - 1;

        // Binet's Formula Answer
        sum = BinetForm(n.add(BigInteger.valueOf(2))).subtract(BigInteger.valueOf(1));

        return sum.multiply(BigInteger.valueOf(4));
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

    public static BigInteger BinetForm(BigInteger n) {
        double squareRootOf5 = Math.sqrt(5);
        double phi = (1 + squareRootOf5) / 2;
        double nthTerm = ((Math.pow(phi, n.doubleValue()) - Math.pow(-phi, -(n.doubleValue()))) / squareRootOf5);
        return BigDecimal.valueOf(nthTerm).toBigInteger();
    }
}