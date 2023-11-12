// In computer science, a priority queue is an abstract data type which is like a regular queue, 
// but where additionally each element has a "priority" associated with it. 
// In a priority queue, an element with high priority is served before an element with low priority.
// In this problem we will test your knowledge on Java Priority Queue.

// There are a number of students in a school who wait to be served. 
// Two types of events, ENTER and SERVED, can take place which are described below.

// ENTER: A student with some priority enters the queue to be served.
// SERVED: The student with the highest priority is served (removed) from the queue.
// A unique id is assigned to each student entering the queue. 
// The queue serves the students based on the following criteria (priority criteria):

// The student having the highest Cumulative Grade Point Average (CGPA) is served first.
// Any students having the same CGPA will be served by name in ascending case-sensitive 
// alphabetical order.
// Any students having the same CGPA and name will be served in ascending order of the id.
// Create the following two classes:

// The Student class should implement:
// The constructor Student(int id, String name, double cgpa).
// The method int getID() to return the id of the student.
// The method String getName() to return the name of the student.
// The method double getCGPA() to return the CGPA of the student.
// The Priorities class should implement the method List<Student> 
// getStudents(List<String> events) to process all the given events and 
// return all the students yet to be served in the priority order.

// Input Format
// The first line contains an integer, , describing the total number of events. 
// Each of the  subsequent lines will be of the following two forms:

// ENTER name CGPA id: The student to be inserted into the priority queue.
// SERVED: The highest priority student in the queue was served.
// The locked stub code in the editor reads the input and tests the correctness of 
// the Student and Priorities classes implementation.

// Output Format
// The locked stub code prints the names of the students yet to be served in the priority order. 
// If there are no such student, then the code prints EMPTY.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.PriorityQueue;

/*
 * Create the Student and Priorities classes here.
 */

//Alternate Solution using commparator in the PrioQ
//  PriorityQueue<Student> pq = new PriorityQueue<(
//     Comparator.comparing(Student::getCgpa).reversed()
//     .thenComparing(Student::getFname)
//     .thenComparing(Student::getToken)
//     );

class Student implements Comparable<Student> {
    String name = new String();
    double cgpa;
    int id;

    public Student(String name, double cgpa, int id) {
        this.name = name;
        this.cgpa = cgpa;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int compareTo(Student s) {
        // Set comparator to the class and not the Priority Q
        // set cgpa compare descending
        // then set name compare ascending
        // lastly id compare ascending
        if (cgpa == s.cgpa) {
            if (name.compareTo(s.name) == 0) {
                if (id == s.id)
                    return 0;
                else if (id > s.id)
                    return 1;
                else
                    return -1;
            } else
                return name.compareTo(s.name);
        } else if (cgpa > s.cgpa)
            return -1;
        else
            return 1;
    }
}

class Priorities {
    public ArrayList<Student> getStudents(List<String> events) {
        int n = events.size();
        PriorityQueue<Student> pq = new PriorityQueue<Student>();
        for (String i : events) {
            String[] s = new String[4];
            s = i.split("\\s");
            // Basically faster since SERVED is only one array while ENTER is 3
            // Can change to compare s[0] == "ENTER" or s[0] == "SERVED"
            if (s.length > 1) {
                pq.add(new Student(s[1], Double.valueOf(s[2]), Integer.valueOf(s[3])));
            } else {
                pq.poll();
            }
        }
        // can actually remove this part since it will be covered in main
        while (pq.size() > 1) {
            System.out.println(pq.poll().name);
        }
        return new ArrayList<Student>(pq);
    }
}

public class PriorityQStudentPrioServe {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st : students) {
                System.out.println(st.getName());
            }
        }
    }
}
