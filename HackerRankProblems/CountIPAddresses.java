// Implement a function that receives two IPv4 addresses, 
// and returns the number of addresses between them 
// (including the first one, excluding the last one).

// All inputs will be valid IPv4 addresses in the form of strings. 
// The last address will always be greater than the first one.
import java.util.Arrays;

public class CountIPAddresses {

    public static long ipsBetween(String start, String end) {
        String ipOct = "[.]"; // Alternative regex \\.

        // old impl
        // String[] startArr = start.split(IPoctindRegX);
        // String[] endArr = end.split(IPoctindRegX);

        // Alternative arrays long need import java util arrays
        long[] startArr = Arrays.stream(start.split(ipOct)).mapToLong(Long::parseLong).toArray();
        long[] endArr = Arrays.stream(end.split(ipOct)).mapToLong(Long::parseLong).toArray();

        long ans = 0;
        int startArrLen = startArr.length - 1;
        for (int i = 0; i <= startArrLen; i++) {
            // old impl
            // ans += (Long.valueOf(endArr[startArrLen - i]) -
            // Long.valueOf(startArr[startArrLen - i]))
            // * (Math.pow(256, i));
            ans += (endArr[startArrLen - i] - startArr[startArrLen - i]) * (Math.pow(256, i));
        }
        return ans;
    }
}
