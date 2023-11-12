class LongestConsec {

    public static String longestConsec(String[] strarr, int k) {
        int arrLen = strarr.length;
        int ansLen = 0;
        int maxLen = 0;
        int ansIndex = 0;
        String ansStr = "";
        // return blank if illegal arguments
        if (arrLen == 0 || k > arrLen || k <= 0)
            return ansStr;
        for (int i = 0; i < arrLen; i++) {
            // always get accumulated length of strings in array
            ansLen += strarr[i].length();

            // if threshold for consecutive elements is reached
            if (i > k - 1) {
                // subtract the leftmost element from the consecutive sequence
                ansLen -= strarr[i - k].length();
            }
            // if cumulative length is greater than the max length
            if (ansLen > maxLen) {
                maxLen = ansLen;
                ansIndex = i - k + 1;
            }
        }
        // retrieve string from the first index of the answer up to k cons seq
        for (int i = ansIndex; i < ansIndex + k; i++)
            ansStr += strarr[i];
        return ansStr;

        // Alternative lambda solution (also compares string length but maps string as
        // well as concatenating them)
        // return IntStream.rangeClosed(0, strarr.length - k)
        // .mapToObj(i -> Arrays.stream(strarr, i, i + k).collect(Collectors.joining()))
        // .max(Comparator.comparingInt(String::length))
        // .orElse("");
    }
}