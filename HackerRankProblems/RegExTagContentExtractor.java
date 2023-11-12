// In a tag-based language like XML or HTML, contents are enclosed between a start tag and 
// an end tag like <tag>contents</tag>. Note that the corresponding end tag starts with a /.
// Given a string of text in a tag-based language, parse this text and retrieve the contents 
// enclosed within sequences of well-organized tags meeting the following criterion:
// 1) The name of the start and end tags must be same. The HTML code <h1>Hello World</h2> 
// is not valid, because the text starts with an h1 tag and ends with a non-matching h2 tag.
// 2) Tags can be nested, but content between nested tags is considered not valid. 
// For example, in <h1><a>contents</a>invalid</h1>, contents is valid but invalid is not valid.
// 3) Tags can consist of any printable characters.

// Input Format
// The first line of input contains a single integer,  (the number of lines).
// The  subsequent lines each contain a line of text.

// Constraints
// Each line contains a maximum of  printable characters.
// The total number of characters in all test cases will not exceed .

// Output Format
// For each line, print the content enclosed within valid tags.
// If a line contains multiple instances of valid content, print out each instance of
// valid content on a new line; if no valid content is found, print None.

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTagContentExtractor {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int testCases = Integer.parseInt(scan.nextLine());

        while (testCases-- > 0) {
            String line = scan.nextLine();

            boolean matchFound = false;
            /* Solution assumes we can't have the symbol "<" as text between tags */
            // <(.+)>
            // matches HTML start tags. The parentheses save the contents inside the
            // brackets into Group #1.
            // ([^<]+)
            // matches all the text in between the HTML start and end tags. We place a
            // special restriction on the text in that it can't have the "<" symbol. The
            // characters inside the parenthesis are saved into Group #2.
            // </\\1>
            // is to match the HTML end brace that corresponds to our previous start brace.
            // The \1 is here to match all text from Group #1.
            Pattern r = Pattern.compile("<(.+)>([^<]+)</\\1>");
            Matcher m = r.matcher(line);

            while (m.find()) {
                System.out.println(m.group(2));
                matchFound = true;
            }
            if (!matchFound) {
                System.out.println("None");
            }
        }
    }
}
