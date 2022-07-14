package greedy.mst;

import java.util.Arrays;

/**
 * A minimum spanning tree (MST) or minimum weight spanning tree is a subset of the edges of
 * a connected, edge-weighted undirected graph that connects all the vertices together,
 * without any cycles and with the minimum possible total edge weight.
 *
 * Prism algorithm grow like a tree where krushkal algorithm grow like a forest.
 *
 * Time Complexity - O(V^2) where V is the total number of vertex.
 *
 * This implementation is better when your graph is dense means E=v^2, for sparse graph where
 * very less number of edges are there i.e same number as Vertex E=V then Prism using Min Heap is better.
 *
 */
public class MSTPrismWithoutMinHeap {

    public static void main(String[] args) {
//        int totalVertex = 7;
//        int[][] adjacencyWeightMatrix = {   {0,0,0,0,0,0,0,0},
//                                            {0,0,28,0,0,0,10,0},
//                                            {0,28,0,16,0,0,0,14},
//                                            {0,0,16,0,12,0,0,0},
//                                            {0,0,0,12,0,22,0,18},
//                                            {0,0,0,0,22,0,25,24},
//                                            {0,10,0,0,0,25,0,0},
//                                            {0,0,14,0,18,24,0,0},
//                                        };
        int totalVertex = 5;
        int[][]  adjacencyWeightMatrix = {   {0,0,0,0,0,0},
                {0,0,2,0,6,0},
                {0,2,0,3,8,5},
                {0,0,3,0,0,7},
                {0,6,8,0,0,9},
                {0,0,5,7,9,0}
        };

        int[][] mstNodes = new int[totalVertex - 1][2];
        int minCost = mst(adjacencyWeightMatrix, mstNodes);
        System.out.println("MST cost " + minCost);
        System.out.println("Edge And Weight Included in MST");
        System.out.println("Edge \tWeight");
        for (int i = 0; i < mstNodes.length; i++) {
            System.out.println(mstNodes[i][0] + "-" + mstNodes[i][1] + "\t\t" + adjacencyWeightMatrix[mstNodes[i][0]][mstNodes[i][1]]);
        }
    }

    /**
     * Time Complexity - O(V^2), where V is vertex.
     * @param adjacencyWeightMatrix
     * @param mstNodes
     * @return
     */
    public static int mst(int[][] adjacencyWeightMatrix, int[][] mstNodes) {
        int minCost = 0;
        int[] near = new int[adjacencyWeightMatrix.length];
        Arrays.fill(near, Integer.MAX_VALUE);
        updateNearTable(adjacencyWeightMatrix, near, 1); // O(V)
        near[1] = 0;
        for (int i = 1; i < adjacencyWeightMatrix.length - 1; i++) { // O(V)
            // Get the vertex with the min value
            int minIndex = getMinIndex(adjacencyWeightMatrix, near); // O(V)
            // Set the value ins the mst.
            mstNodes[i-1][0] = near[minIndex];
            mstNodes[i-1][1] =  minIndex;
            // Update the total cost.
            minCost += adjacencyWeightMatrix[near[minIndex]][minIndex];
            // Mark the vertex, so that it will not get considered again.
            near[minIndex] = 0;
            // Update the Near table using newly vertex included in mst.
            updateNearTable(adjacencyWeightMatrix, near, minIndex); // O(V)
        }

        return minCost;
    }

    /**
     * This will return the vertex with minimum key value, from the vertices not yet included in MST
     *
     * @param adjacencyWeightMatrix
     * @param near
     * @return
     */
    private static int getMinIndex(int[][] adjacencyWeightMatrix, int[] near) {
        int minIndex = -1;
        int minValue = Integer.MAX_VALUE;
        for (int j = 1; j < adjacencyWeightMatrix.length; j++) {
            if (near[j] != 0 && near[j] != Integer.MAX_VALUE && adjacencyWeightMatrix[near[j]][j] > 0) {
                if(adjacencyWeightMatrix[near[j]][j] < minValue) {
                    minValue = adjacencyWeightMatrix[near[j]][j];
                    minIndex = j;
                }
            }
        }
        return minIndex;
    }


    /**
     * Update the near table for all the values which are not included in the mst and only weights where
     * weightMatrix value > 0 and less than the current value present.
     *
     * @param adjacencyWeightMatrix
     * @param near
     * @param parentKey
     */
    private static void updateNearTable(int[][] adjacencyWeightMatrix, int[] near, int parentKey) {
        for (int j = 1; j < adjacencyWeightMatrix.length; j++) {
            if (near[j] != 0 && adjacencyWeightMatrix[parentKey][j] > 0 && ( near[j] == Integer.MAX_VALUE || adjacencyWeightMatrix[parentKey][j] < adjacencyWeightMatrix[near[j]][j])) {
                near[j] = parentKey;
            }
        }
    }
}
