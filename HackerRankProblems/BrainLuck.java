public class BrainLuck {
    private static int ptr; // Data pointer
    private static int length = 65535; // max limit of memory
    private static byte memory[] = new byte[length];
    private String code;
    private int inputCtr = 0;

    public BrainLuck(String code) {
        this.code = code;
    }

    public String process(String input) {
        char[] inputChar = input.toCharArray();
        int c = 0;
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < code.length(); i++) {
            // moves the pointer to the right
            if (code.charAt(i) == '>') {
                if (ptr == length - 1)// return to 0 if memory is
                    ptr = 0;
                else
                    ptr++;
            } else if (code.charAt(i) == '<') { // moves the pointer to the left
                if (ptr == 0) // returned to rightmost memory
                    ptr = length - 1;
                else
                    ptr--;
            } else if (code.charAt(i) == '+') { // add value of byte under pointer
                if (memory[ptr] == Byte.MAX_VALUE) // truncate overflow over max value
                    memory[ptr] = (byte) 0;
                else
                    memory[ptr]++;
            } else if (code.charAt(i) == '-') { // subtract value of byte under pointer
                if (memory[ptr] == (byte) 0) // treat as unsigned byte
                    memory[ptr] = Byte.MAX_VALUE;
                else
                    memory[ptr]--;
            } else if (code.charAt(i) == '.') { // outputs the byte at the data pointer
                System.out.print((char) (memory[ptr]));
                output.append((char) (memory[ptr]));
            } else if (code.charAt(i) == ',') { // get one byte of input and store to memory
                memory[ptr] = Integer.valueOf((int) inputChar[inputCtr]).byteValue();
                inputCtr++;
            } else if (code.charAt(i) == '[') { // square bracket jumper forward
                if (memory[ptr] == 0) {
                    i++;
                    while (c > 0 || code.charAt(i) != ']') {
                        if (code.charAt(i) == '[')
                            c++;
                        else if (code.charAt(i) == ']')
                            c--;
                        i++;
                    }
                }
            } else if (code.charAt(i) == ']') { // square bracket jumper forward
                if (memory[ptr] != 0) {
                    i--;
                    while (c > 0 || code.charAt(i) != '[') {
                        if (code.charAt(i) == ']')
                            c++;
                        else if (code.charAt(i) == '[')
                            c--;
                        i--;
                    }
                    i--;
                }
            }
        }
        return output.toString();
    }
}