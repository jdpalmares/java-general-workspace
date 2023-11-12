// In this challenge, there is a connected undirected graph where each of the nodes is a color. 
// Given a color, find the shortest path connecting any two nodes of that color. 
// Each edge has a weight of . If there is not a pair or if the color is not found, print .

// For example, given , and edges and and colors for each node are we can draw the following graph:
// Each of the nodes is labeled [node]/[color] and is colored appropriately. 
// If we want the shortest path between color , blue, we see there is a direct path between nodes 
// and . For green, color, we see the path length from .There is no pair for node  having color, red.

// Function Description
// Complete the findShortest function in the editor below. 
// It should return an integer representing the length of the shortest path between two nodes 
// of the same color, or  if it is not possible.

// findShortest has the following parameter(s):
// g_nodes: an integer, the number of nodes
// g_from: an array of integers, the start nodes for each edge
// g_to: an array of integers, the end nodes for each edge
// ids: an array of integers, the color id per node
// val: an integer, the id of the color to match
// Input Format
// The first line contains two space-separated integers  and ,
// the number of nodes and edges in the graph.
// Each of the next  lines contains two space-separated integers  and , 
// the nodes connected by an edge.
// The next line contains  space-seperated integers, , 
// representing the color id of each node from  to .
// The last line contains the id of the color to analyze.
// Note: The nodes are indexed from  to .
// Output Format Print the single integer representing the smallest path length or -1.
import java.io.IOException;
import java.util.ArrayList;

public class ShortestPathColorClone {
    private static ArrayList<Integer>[] adjacencies;
    private static boolean[] visited;

    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, long val) {

        // we hold our adjacencies in an array of ArrayLists (+1 bc we wont be using
        // index 0)
        adjacencies = new ArrayList[graphNodes + 1];
        adjacencies[0] = new ArrayList<Integer>();

        visited = new boolean[graphNodes + 1];

        // initialize all the array lists within the adjacencies array and check to see
        // if we have at least 2 pairs
        int numCorrectColors = 0;
        for (int i = 1; i <= graphNodes; i++) {
            adjacencies[i] = new ArrayList<Integer>();
            // this will check to see if we have a valid number of pairs
            if (ids[i - 1] == val) {
                numCorrectColors++;
            }
        }

        // checks for the case that there aren't any possible pair combos
        if (numCorrectColors < 2) {
            return -1;
        }

        // add the adjacencies for our graph
        for (int i = 0; i < graphFrom.length; i++) {
            int node1 = graphFrom[i];
            int node2 = graphTo[i];
            adjacencies[node1].add(node2);
            adjacencies[node2].add(node1);
        }

        // initially, we set shortestPath to longest path possible, which is # of
        // graphNodes
        int shortestPath = graphNodes;
        for (int i = 1; i < adjacencies.length; i++) {
            // i-1 bc that ids array is indexed starting at 0
            long curNodeColor = ids[i - 1];
            int curPath = 0;

            // if this node's color isn't the one we're looking for, skip it
            if (curNodeColor != val) {
                continue;
            }

            // find the shortest path length for this node to another of a matching color
            curPath += findPathLength(i, val, ids);
            shortestPath = Math.min(shortestPath, curPath);
        }

        return shortestPath;
    }

    static int findPathLength(int curNode, long color, long[] ids) {
        visited[curNode] = true;
        ArrayList<Integer> curAdj = adjacencies[curNode];
        int minPath = ids.length;

        for (int i = 0; i < curAdj.size(); i++) {
            int curNeighbor = curAdj.get(i);
            int curPath = 1;

            // if this neighbor has already been visited, skip it
            if (visited[curNeighbor] == true) {
                continue;
            }

            // if this neighbor has the matching pair, then 1 is the shortest path
            if (ids[curNeighbor - 1] == color) {
                return curPath;
            }

            // if this neighbor doesn't meet any of the if conditions, then we must recurse
            curPath += findPathLength(curNeighbor, color, ids);
            minPath = Math.min(minPath, curPath);
        }

        return minPath;
    }

    public static void main(String[] args) throws IOException {

        int graphNodes = 5;

        int[] graphFrom = new int[] { 1, 2, 2, 3 };
        int[] graphTo = new int[] { 2, 3, 4, 5 };

        long[] ids = new long[] { 1, 2, 3, 1, 3 };

        int val = 1;

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

        System.out.println("Shortest path to the clone is - " + ans);
    }
}
