package greedy.shortedPath;

import java.util.Arrays;

/**
 * A Multistage graph is a directed, weighted graph in which the nodes can be divided into a
 * set of stages such that all edges are from a stage to next stage only (In other words there
 * is no edge between vertices of same stage and from a vertex of current stage to previous
 * stage).
 *
 * Time Complexity - O(V^2) i.e O(E)
 * Space Complexity - O(V)
 */
public class MultiStageGraph {
    public static void main(String[] args) {
        int[][] weightedPathMatrix = {
            {0,1,2,5,0,0,0,0},
            {0,0,0,0,4,11,0,0},
            {0,0,0,0,9,5,16,0},
            {0,0,0,0,0,0,2,0},
            {0,0,0,0,0,0,0,18},
            {0,0,0,0,0,0,0,13},
            {0,0,0,0,0,0,0,2},
            {0,0,0,0,0,0,0,0}
        };

        long[] destinations = shortestPathUsingBottomUpApproach(weightedPathMatrix);
        System.out.println("Distance from source " + 0  + " to Destination " + (weightedPathMatrix.length - 1) + " is " + destinations[0]);
    }

    private static long[] shortestPathUsingBottomUpApproach(int[][] weightedPathMatrix) {
        long[] destinations = new long[weightedPathMatrix.length];
        Arrays.fill(destinations, Integer.MAX_VALUE);
        // Setting destination node value to 0, as there is no path from the destination node.
        destinations[weightedPathMatrix.length - 1] = 0;
        // Total Time Complexity - O(V^2)
        for(int i = weightedPathMatrix.length - 2 ; i >= 0 ; i --) { // O(V)
            for(int j = i;j< weightedPathMatrix.length; j ++) { // O(V)
                if(weightedPathMatrix[i][j] != 0) {
                    destinations[i] = Math.min(destinations[i], weightedPathMatrix[i][j] + destinations[j]);
                }
            }
        }
        return  destinations;
    }
}
