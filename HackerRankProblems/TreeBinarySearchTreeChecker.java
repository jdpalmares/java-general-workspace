// For the purposes of this challenge, we define a binary search tree to be a binary tree with the following properties:

// The data value of every node in a node's left subtree is less than the data value of that node.
// The data value of every node in a node's right subtree is greater than the data value of that node.
// The data value of every node is distinct.
// For example, the image on the left below is a valid BST. The one on the right fails on several counts:
// - All of the numbers on the right branch from the root are not larger than the root.
// - All of the numbers on the right branch from node 5 are not larger than 5.
// - All of the numbers on the left branch from node 5 are not smaller than 5.
// - The data value 1 is repeated.
// Function Description

// Complete the function checkBST in the editor below. It must return a boolean denoting whether or not the binary tree is a binary search tree.

// checkBST has the following parameter(s):

// root: a reference to the root node of a tree to test
// Input Format
// You are not responsible for reading any input from stdin. Hidden code stubs will assemble a binary tree and pass its root node to your function as an argument.
// Output Format Your function must return a boolean true if the tree is a binary search tree. Otherwise, it must return false.

import java.util.*;

class NodeD {
   NodeD left;
   NodeD right;
   int data;

   NodeD(int data) {
      this.data = data;
      left = null;
      right = null;
   }
}

class TreeBinarySearchTreeChecker {

   static boolean checkBST(NodeD root) {
      return check(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
   }

   static boolean check(NodeD n, int min, int max) {
      if (n == null)
         return true;
      if (n.data <= min || n.data >= max)
         return false;
      return check(n.left, min, n.data) && check(n.right, n.data, max);
   }

   public static NodeD insert(NodeD root, int data) {
      if (root == null) {
         return new NodeD(data);
      } else {
         NodeD cur;
         if (data <= root.data) {
            cur = insert(root.left, data);
            root.left = cur;
         } else {
            cur = insert(root.right, data);
            root.right = cur;
         }
         return root;
      }
   }

   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      int t = scan.nextInt();
      NodeD root = null;
      while (t-- > 0) {
         int data = scan.nextInt();
         root = insert(root, data);
      }
      scan.close();
      boolean isBst = checkBST(root);
      System.out.println(isBst);
   }
}