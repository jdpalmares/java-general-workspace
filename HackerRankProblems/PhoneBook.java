
// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class PhoneBook {
    public String solution(String[] A, String[] B, String P) {
        int arrLenA = A.length;
        int arrLenB = B.length;
        // Check for illegal arguments
        if ((arrLenA < 1 || arrLenA > 100) || (arrLenB < 1 || arrLenB > 100))
            throw new IllegalArgumentException();

        // Put contacts in a hashmap for easier access later
        HashMap<String, String> hm = new HashMap<>();
        for (int i = 0; i < arrLenA; i++)
            hm.put(A[i], B[i]);

        // Arrange the hashmap
        Map<String, String> addressBook = new TreeMap<>(hm);

        // Iterate through erach
        for (HashMap.Entry<String, String> contact : addressBook.entrySet()) {
            // If the substring is found in the arranged contact
            if (contact.getValue().contains(P))
                return contact.getKey();
        }
        return "NO CONTACT";
    }
}
