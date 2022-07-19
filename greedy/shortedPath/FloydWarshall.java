package greedy.shortedPath;

/**
 * The Floyd Warshall Algorithm is for solving the All Pairs Shortest Path problem.
 * The problem is to find shortest distances between every pair of vertices in a given edge weighted directed/undirected Graph.
 *
 * Time Complexity - O(V^3)
 * Space Complexity - O(V^2)
 *
 * Shortest Path Using Dijkstra - O(ELogV)
 * Finding All Pair Shortest Path Using Dijkstra - O(V*ELogV) = O(V^3LogV) , considering E = V^2
 * Shortest Path Using Bellman Ford - O(EV) = O(V^3)
 * Finding All Pair Shortest Path Using Bellman Ford - O(V*V^3) = O(V^4)
 *
 */
public class FloydWarshall {
    public static void main(String[] args) {
//        long[][] weightedPathMatrix = {
//                {0, 1, Integer.MAX_VALUE, 5},
//                {1, 0, 2, Integer.MAX_VALUE},
//                {Integer.MAX_VALUE, 2, 0, 1},
//                {5, Integer.MAX_VALUE, 1, 0},
//        };
        long[][] weightedPathMatrix = {
                {0, 11, 1, 6},
                {11, 0, 7, 3},
                {1, 7, 0, 2},
                {6, 3, 2, 0},
        };
        long[][] allPairShortestPath = allPairShortestPath(weightedPathMatrix);
        for (int i = 0; i < allPairShortestPath.length; i++) {
            for (int j = 0; j < allPairShortestPath.length; j++) {
                System.out.println("Path From " + i + " to " + j + " is " + allPairShortestPath[i][j]);
            }
        }
    }

    /**
     * Total Time Complexity - O(V^3)
     * Space Complexity - O(V^2), because of having extra memory for last path's.
     *
     * @param weightedPathMatrix1
     * @return
     */
    public static long[][] allPairShortestPath(long[][] weightedPathMatrix1) {
        long[][] weightedPathMatrix2 = new long[weightedPathMatrix1.length][weightedPathMatrix1.length];
        for (int i = 0; i < weightedPathMatrix1.length; i++) { // O(V)
            for (int j = 0; j < weightedPathMatrix1.length; j++) { // O(V)
                for (int k = 0; k < weightedPathMatrix1.length; k++) { // O(V)
                    weightedPathMatrix2[j][k] = weightedPathMatrix1[j][k]; // O(1)
                    if (weightedPathMatrix1[j][k] > (weightedPathMatrix1[j][i] + weightedPathMatrix1[i][k])) { // O(1)
                        weightedPathMatrix2[j][k] = weightedPathMatrix1[j][i] + weightedPathMatrix1[i][k];
                    }
                }
            }
            weightedPathMatrix1 = weightedPathMatrix2;
        }
        return weightedPathMatrix2;
    }
}
