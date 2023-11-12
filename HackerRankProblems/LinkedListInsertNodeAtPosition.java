// Given the pointer to the head node of a linked list and an integer to insert at a certain position, 
// create a new node with the given integer as its data attribute, insert this node at the desired position and return the head node.

// A position of 0 indicates head, a position of 1 indicates one node away from the head and so on. 
// The head pointer given may be null meaning that the initial list is empty.

// Example
// head refers to the first node in the list. data = 4, position = 2 Insert a node at position 2 with data = 4. The new list is 1 -> 2 -> 4 -> 3 

// Function Description Complete the function insertNodeAtPosition in the editor below. It must return a reference to the head node of your finished list.

// insertNodeAtPosition has the following parameters:
// head: a SinglyLinkedListNode pointer to the head of the list
// data: an integer value to insert as data in your new node
// position: an integer position to insert the new node, zero based indexing

// Returns SinglyLinkedListNode pointer: a reference to the head of the revised list
// Input Format
// The first line contains an integer , the number of elements in the linked list.
// Each of the next  lines contains an integer SinglyLinkedListNode[i].data.
// The next line contains an integer data, the data of the node that is to be inserted.
// The last line contains an integer position.

// Sample Input - 3 16 13 7 1 2
// Sample Output - 16 13 1 7
// Explanation The initial linked list is 16 -> 13 -> 7. Insert 1 at the position 2 which currently has 7 in it. 
// The updated linked list is 16 -> 13 -> 1 -> 7.

import java.io.*;
import java.util.*;

public class LinkedListInsertNodeAtPosition {

   static class SinglyLinkedListNode {
      public int data;
      public SinglyLinkedListNode next;

      public SinglyLinkedListNode(int nodeData) {
         this.data = nodeData;
         this.next = null;
      }
   }

   static class SinglyLinkedList {
      public SinglyLinkedListNode head;
      public SinglyLinkedListNode tail;

      public SinglyLinkedList() {
         this.head = null;
         this.tail = null;
         String test;
         test.substring(beginIndex, endIndex)
      }

      public void insertNode(int nodeData) {
         SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

         if (this.head == null) {
            this.head = node;
         } else {
            this.tail.next = node;
         }

         this.tail = node;
      }
   }

   public static void printSinglyLinkedList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter)
         throws IOException {
      while (node != null) {
         bufferedWriter.write(String.valueOf(node.data));

         node = node.next;

         if (node != null) {
            bufferedWriter.write(sep);
         }
      }
   }

   // Complete the insertNodeAtPosition function below.

   /*
    * For your reference:
    *
    * SinglyLinkedListNode { int data; SinglyLinkedListNode next; }
    *
    */
   static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head, int data, int position) {
      // Will Need to Return Head Node
      SinglyLinkedListNode trackedHeadNode = head;

      SinglyLinkedListNode nodeToInsert = new SinglyLinkedListNode(data);

      // Empty List - Returned newly created node or null
      if (head == null) {
         return nodeToInsert;
      }

      // Inserting a Node ahead of the List
      if (position == 0) {
         nodeToInsert.next = head;
         return nodeToInsert;
      }

      // Traverse the Singly Linked List to 1 Position Prior
      // Stop traversing if you reached the end of the List
      int currPosition = 0;

      while (currPosition < position - 1 && head.next != null) {
         head = head.next;
         currPosition++;
      }

      // Inserting a Node in-between a List or at the end of of a List
      SinglyLinkedListNode nodeAtPosition = head.next;
      head.next = nodeToInsert;
      head = head.next;
      head.next = nodeAtPosition;

      return trackedHeadNode;

   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      SinglyLinkedList llist = new SinglyLinkedList();

      int llistCount = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < llistCount; i++) {
         int llistItem = scanner.nextInt();
         scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

         llist.insertNode(llistItem);
      }

      int data = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int position = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      SinglyLinkedListNode llist_head = insertNodeAtPosition(llist.head, data, position);

      printSinglyLinkedList(llist_head, " ", bufferedWriter);
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
