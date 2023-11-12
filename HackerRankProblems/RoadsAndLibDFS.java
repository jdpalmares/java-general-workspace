// Determine the minimum cost to provide library access to all citizens of HackerLand. 
//There are  cities numbered from  to . Currently there are no libraries and the cities 
//are not connected. Bidirectional roads may be built between any city pair listed in . 
//A citizen has access to a library if:
// Their city contains a library.
// They can travel by road from their city to a city containing a library.

// The cost of building any road is , and the cost to build a library in any city is . 
//Build  roads at a cost of  and  libraries for a cost of . 
//One of the available roads in the cycle  is not necessary.
// There are  queries, where each query consists of a map of HackerLand and value of  and . 
//For each query, find the minimum cost to make libraries accessible to all the citizens.

// Complete the function roadsAndLibraries in the editor below.
// roadsAndLibraries has the following parameters:
// int n: integer, the number of cities
// int c_lib: integer, the cost to build a library
// int c_road: integer, the cost to repair a road
// int cities[m][2]: each  contains two integers that represent cities that can be connected by a new road
// Returns - int: the minimal cost

// Input Format
// The first line contains a single integer , that denotes the number of queries.
// The subsequent lines describe each query in the following format:
// - The first line contains four space-separated integers that describe the respective values 
//of , ,  and , the number of cities, number of roads, cost of a library and cost of a road.
// - Each of the next  lines contains two space-separated integers,  and , that describe a 
// bidirectional road that can be built to connect cities  and .

import java.io.*;
import java.util.*;

public class RoadsAndLibDFS {

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        // implement hybrid Union-find with nodes count
        // NOTE: parent[0], rank[0] and cityCount[0] are ignored for indexing simplicity
        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1]; // Union-find subtree rank to merge shorter to larger
        int[] cityCount = new int[n + 1];
        // initate rank and parents
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
            cityCount[i] = 1;
        }

        for (int[] conn : cities) { // iterate through each possible road
            int v = conn[0]; // source or 1st city
            int q = conn[1]; // destination or 2nd city
            // determine use (place numbered city in the proper parent placement?)
            int rootV = find(v, parent);
            int rootQ = find(q, parent);

            // place roots of 1st and 2nd city then rank them based on adjacent placement
            if (rootV != rootQ) {
                // union find depending on what is the smaller tree
                if (rank[rootV] > rank[rootQ]) {
                    parent[rootQ] = rootV;
                    cityCount[rootV] += cityCount[rootQ];
                } else {
                    parent[rootV] = rootQ;
                    cityCount[rootQ] += cityCount[rootV];

                    // if the two cities' rank is equal, increase value of 2nd city
                    if (rank[rootV] == rank[rootQ]) {
                        rank[rootQ]++;
                    }
                }
            }
        }

        // find all islands (connected sub-graphs) and
        // calculate minimal cost for each cities island
        // consider each "island" as a spanning tree,
        // e.g. it has minimal roads/nodes to connect all of them
        long totalCost = 0;
        for (int i = 1; i < n + 1; i++) {
            if (parent[i] == i) {
                // number of edges in spanning sub-tree of a graph with cityCount[i] nodes
                long roadsCount = cityCount[i] - 1;
                long buildRoadsAndOneLib = roadsCount * c_road + c_lib;
                int buildLibsInEachCity = cityCount[i] * c_lib;
                // check if build roads and one lib or build lib on ea city is better
                totalCost += Math.min(buildRoadsAndOneLib, buildLibsInEachCity);
            }
        }

        return totalCost;
    }

    // Union find with pass compression
    // place current value to where it should be in the parent value
    private static int find(int val, final int[] parents) {
        while (parents[val] != val) {
            // flatten the tree (one-pass variant) for performance
            parents[val] = parents[parents[val]];
            val = parents[val];
        }

        return val;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
