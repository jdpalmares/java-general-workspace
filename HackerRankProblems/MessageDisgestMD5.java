// MD5 (Message-Digest algorithm 5) is a widely-used cryptographic hash function with a -bit hash value. Here are some common uses for MD5:

// To store a one-way hash of a password.
// To provide some assurance that a transferred file has arrived intact.
// MD5 is one in a series of message digest algorithms designed by Professor Ronald Rivest of 
// MIT (Rivest, ); however, the security of MD5 has been severely compromised, 
// most infamously by the Flame malware in . The CMU Software Engineering Institute essentially 
// considers MD5 to be "cryptographically broken and unsuitable for further use".

// Given an alphanumeric string, , 
// denoting a password, compute and print its MD5 encryption value.

// Input Format A single alphanumeric string denoting .

// Constraints
// String  consists of English alphabetic letters (i.e.,  and/or decimal digits 
// (i.e.,  through ) only.

// Output Format Print the MD5 encryption value of  on a new line.

import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDisgestMD5 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        /* Read and save the input String */
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        scan.close();

        /* Encode the String using MD5 */
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        byte[] digest = md.digest();

        /* Print the encoded value in hexadecimal */
        for (byte b : digest) {
            System.out.format("%02x", b);
        }
    }
}