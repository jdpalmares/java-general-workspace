public class VisibleNodeBinaryTreeCtr {
   public int solution(Tree T) {
         //Count the visible nodes by traversing the tree using recursion
         return countRecurVisNode(T, Integer.MIN_VALUE);
   }

   public int countRecurVisNode(Tree T, int prevNodeMaxVal) {
         if(T == null) return 0;

         int visNodeCtr = 0;

         //if path from root to current node has no value greater than current
         //add 1 to the counter of the visible node
         if(T.x >= prevNodeMaxVal) {
            visNodeCtr = 1;
            prevNodeMaxVal = T.x;
         }

         //counter plus recusively check from left and right side
         return visNodeCtr + countRecurVisNode(T.l, prevNodeMaxVal) + countRecurVisNode(T.r, prevNodeMaxVal);
   }

   class Tree{
      public int x;
      public Tree l;
      public Tree r;
   }
}

