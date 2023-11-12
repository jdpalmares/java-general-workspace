// In computer science, a double-ended queue (dequeue, often abbreviated to deque, pronounced deck)
// is an abstract data type that generalizes a queue, for which elements can be added to or 
// removed from either the front (head) or back (tail).

// Deque interfaces can be implemented using various types of collections such as 
// LinkedList or ArrayDeque classes. For example, deque can be declared as:
// Deque deque = new LinkedList<>(); or Deque deque = new ArrayDeque<>();
// You can find more details about Deque here.

// In this problem, you are given  integers. You need to find the maximum number of 
// unique integers among all the possible contiguous subarrays of size .

// Note: Time limit is 3 second for this problem.
// Input Format
// The first line of input contains two integers n and m : representing the total number of integers 
// and the size of the subarray, respectively. The next line contains  space separated integers.

// Constraints
// The numbers in the array will range between .
// Output Format
// Print the maximum number of unique integers among all possible contiguous subarrays of size .

import java.util.*;

public class DequeSubarrayMaxUniqueInt {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        HashSet<Integer> set = new HashSet<>();

        int n = in.nextInt();
        int m = in.nextInt();
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int input = in.nextInt();

            // add in dequeue for checking sub array
            deque.add(input);
            // add in set to eliminate repeating element
            set.add(input);

            // if deque reaches size m, check size of set and
            // it will be the new max size
            if (deque.size() == m) {
                if (set.size() > max)
                    max = set.size();
                // if all numbers of the m subset is unique, break loop
                if (max == m)
                    break;
                // remove the first element in deque so that new element will
                // be inserted in it, also check if it is in the set and remove
                // it as well if it is in the set
                int first = deque.remove();
                if (!deque.contains(first))
                    set.remove(first);
            }
        }

        System.out.println(max);
        in.close();
    }
}
