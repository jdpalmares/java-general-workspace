// You are given pointer to the root of the binary search tree and two values v1 and v2. 
// You need to return the lowest common ancestor (LCA) of v1 and v2 in the binary search tree.

// Function Description

// Complete the function lca in the editor below. It should return a pointer to the lowest common ancestor NodeA of the two values given.

// lca has the following parameters:
// - root: a pointer to the root NodeA of a binary search tree
// - v1: a NodeA.data value
// - v2: a NodeA.data value

// Input Format
// The first line contains an integer, n, the number of NodeAs in the tree.
// The second line contains n space-separated integers representing NodeA.data values.
// The third line contains two space-separated integers, v1 and v2.
// To use the test data, you will have to create the binary search tree yourself. Here on the platform, the tree will be created for you.

// Output Format Return the a pointer to the NodeA that is the lowest common ancestor of v1 and v2.

import java.util.*;

class NodeA {
   NodeA left;
   NodeA right;
   int data;

   NodeA(int data) {
      this.data = data;
      left = null;
      right = null;
   }
}

class TreeLowestCommonAncestor {

   /*
    * class NodeA int data; NodeA left; NodeA right;
    */
   public static NodeA lca(NodeA root, int v1, int v2) {
      if (root.data < v1 && root.data < v2) {
         return lca(root.right, v1, v2);
      }
      if (root.data > v1 && root.data > v2) {
         return lca(root.left, v1, v2);
      }
      return root;
   }

   public static NodeA insert(NodeA root, int data) {
      if (root == null) {
         return new NodeA(data);
      } else {
         NodeA cur;
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
      NodeA root = null;
      while (t-- > 0) {
         int data = scan.nextInt();
         root = insert(root, data);
      }
      int v1 = scan.nextInt();
      int v2 = scan.nextInt();
      scan.close();
      NodeA ans = lca(root, v1, v2);
      System.out.println(ans.data);
   }
}