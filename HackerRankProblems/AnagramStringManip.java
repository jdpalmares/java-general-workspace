// A student is taking a cryptography class and has found anagrams to be very useful. 
// Two strings are anagrams of each other if the first string's letters can be rearranged to 
// form the second string. In other words, both strings must contain the same exact letters 
// in the same exact frequency. For example, bacdc and dcbac are anagrams,
// but bacdc and dcbad are not.

// The student decides on an encryption scheme that involves two large strings. 
// The encryption is dependent on the minimum number of character deletions required to 
// make the two strings anagrams. Determine this number.

// Given two strings,  and , that may or may not be of the same length,
// determine the minimum number of character deletions required to make  and  anagrams.
// Any characters can be deleted from either of the strings.

// Example

// Delete  from  and  from  so that the remaining strings are  and  which are anagrams. 
// This takes  character deletions.

// Function Description
// Complete the makeAnagram function in the editor below. makeAnagram has the following parameter(s):
// string a: a string
// string b: another string

// Returns int: the minimum total characters that must be deleted

// Input Format
// The first line contains a single string, .
// The second line contains a single string, .
import java.io.IOException;

public class AnagramStringManip {
    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        // make a copy since you cannnot manipulate the string you are itearting
        String firstCopy = a;

        for (char c : a.toCharArray()) {
            if (b.indexOf(c) != -1) {
                firstCopy = firstCopy.replaceFirst(c + "", "");
                b = b.replaceFirst(c + "", "");
            }
        }

        return firstCopy.length() + b.length();

        // solution using maps and list
        // Map<Character, Integer> count = new HashMap<>();
        // for( char ch: a.toCharArray() ) {
        // int ct = count.containsKey(ch) ? count.get(ch) : 0;
        // count.put(ch, (ct + 1));
        // }

        // for( char ch: b.toCharArray() ) {
        // int ct = count.containsKey(ch) ? count.get(ch) : 0;
        // count.put(ch, (ct - 1));
        // }

        // List<Integer> values = new ArrayList<>( count.values() );
        // int total = 0;
        // for( Integer v: values ) {
        // total += Math.abs(v);
        // }
        // return total;
    }

    public static void main(String[] args) throws IOException {
        String a = "cde";

        String b = "abc";

        int res = makeAnagram(a, b);

        System.out.println("Min number of time string must be deleted: " + res);
    }
}
