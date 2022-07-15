package greedy.shortedPath;

import java.util.Arrays;

/**
 * Bellman-Ford finds the shortest distances from the source to all vertices.
 * The graph may contain negative weight edges.
 *
 * Dijkstra doesn’t work for Graphs with negative weights, Bellman-Ford works for such graphs.
 * Bellman-Ford is also simpler than Dijkstra and suites well for distributed systems.
 * But time complexity of Bellman-Ford is O(VE), which is more than Dijkstra.
 *
 * Time Complexity - O(VE)
 * Space Complexity - O(V)
 *
 */
public class BellmanFord {
    public static void main(String[] args) {
//        int[][] weightedPathMatrix = { {0,4,0,0,0,0,0,8,0},
//                {4,0,8,0,0,0,0,11,0},
//                {0,8,0,7,0,4,0,0,2},
//                {0,0,7,0,9,14,0,0,0},
//                {0,0,0,9,0,10,0,0,0},
//                {0,0,4,14,10,0,2,0,0},
//                {0,0,0,0,0,2,0,1,6},
//                {8,11,0,0,0,0,1,0,7},
//                {0,0,2,0,0,0,6,7,0},
//        };

        int[][] weightedPathMatrix = { {0,1,2},
                                       {0,0,2},
                                       {0,-3,0},
                                     };
        long[] destinations = null;
        try {
            destinations = shortestPath(weightedPathMatrix);
            for(int i = 0 ;i <destinations.length;i++) {
                System.out.println("Node " + i + " Weight " + destinations[i]);
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Time Complexity - O(VE), where V is the vertex and E is the Edges.
     * Space Complexity - O(V), where V is the vertex
     * @param weightedPathMatrix
     * @return
     */
    private static long[] shortestPath(int[][] weightedPathMatrix) {
        long[] destinations1 = new long[weightedPathMatrix.length];
        Arrays.fill(destinations1, Integer.MAX_VALUE); // O(V)
        destinations1[0] = 0;
        long[] destinations2 = new long[weightedPathMatrix.length];
        Arrays.fill(destinations2, Integer.MAX_VALUE); // O(V)
        destinations2[0] = 0;
        int num = 0;
        while (num < weightedPathMatrix.length) { // O(V-1)
            for (int i = 0; i < weightedPathMatrix.length; i++) { // O(E)
                for (int j = 0; j < weightedPathMatrix.length; j++) {
                    if (weightedPathMatrix[i][j] != 0 && destinations1[j] > (destinations1[i] + weightedPathMatrix[i][j])) {
                        destinations2[j] = (destinations1[i] + weightedPathMatrix[i][j]);
                    }
                }
            }
            num++;
            destinations1 = destinations2;
        }

        for (int i = 0; i < weightedPathMatrix.length; i++) { // O(E)
            for (int j = 0; j < weightedPathMatrix.length; j++) {
                if (weightedPathMatrix[i][j] != 0 && destinations1[j] > (destinations1[i] + weightedPathMatrix[i][j])) {
                   throw new IllegalArgumentException("Unable to calculate Shortest Path for the given input," +
                           " Because it has negative weight cycle");
                }
            }
        }
        return destinations1;
    }
}
