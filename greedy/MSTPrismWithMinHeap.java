package greedy;

import java.util.*;

/**
 * A minimum spanning tree (MST) or minimum weight spanning tree is a subset of the edges of
 * a connected, edge-weighted undirected graph that connects all the vertices together,
 * without any cycles and with the minimum possible total edge weight.
 *
 * Prism algorithm grow like a tree where krushkal algorithm grow like a forest.
 *
 * Time Complexity - O(v^2LogV) where n is the total number of vertex.
 * In Aggregate analysis, as you can see we are calling minHeap.offer method equal to the
 * Number of Edge so we can say Time Complexity will be O(Elogv)
 *
 * This implementation is better when your graph is sparse means E=V, for dense graph where
 * large number of edges are there i.e E=V^2 then Prism using without Min Heap is better.
 *
 */
public class MSTPrismWithMinHeap {

    static class Node {
        int parentNodeId;
        int nodeId;
        int weight;
        Node(int parentNodeId, int nodeId,int weight) {
            this.parentNodeId = parentNodeId;
            this.nodeId = nodeId;
            this.weight = weight;
        }
        int getWeight() {
            return this.weight;
        }
    }

    public static void main(String[] args) {
        int totalVertex = 7;
        int[][] adjacencyWeightMatrix = {   {0,0,0,0,0,0,0,0},
                {0,0,28,0,0,0,10,0},
                {0,28,0,16,0,0,0,14},
                {0,0,16,0,12,0,0,0},
                {0,0,0,12,0,22,0,18},
                {0,0,0,0,22,0,25,24},
                {0,10,0,0,0,25,0,0},
                {0,0,14,0,18,24,0,0},
        };
//        int totalVertex = 5;
//        int[][]  adjacencyWeightMatrix = {   {0,0,0,0,0,0},
//                {0,0,2,0,6,0},
//                {0,2,0,3,8,5},
//                {0,0,3,0,0,7},
//                {0,6,8,0,0,9},
//                {0,0,5,7,9,0}
//        };

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
     * Time Complexity - O(V^2 LogV), where V is vertex.
     * @param adjacencyWeightMatrix
     * @param mstNodes
     * @return
     */
    public static int mst(int[][] adjacencyWeightMatrix, int[][] mstNodes) {
        int minCost = 0;
        Set<Integer> usedNodes = new HashSet<>();
        Queue<Node> minHeap = new PriorityQueue<>(Comparator.comparing(Node::getWeight));
        Node node = new Node(1,1, 0);
        usedNodes.add(1);
        updateNearNodes(adjacencyWeightMatrix, usedNodes, minHeap, node); //O(VLogV)
        int i = 0;
        while (i < mstNodes.length) { // O(V)
            node = minHeap.poll(); // O(logV)
            minCost += node.weight;
            mstNodes[i][0] = node.parentNodeId;
            mstNodes[i++][1] = node.nodeId;
            usedNodes.add(node.nodeId);

            updateNearNodes(adjacencyWeightMatrix, usedNodes, minHeap, node); // O(VLogV)
        }
        return minCost;
    }

    /**
     * Time Complexity - O(Vlogv), O(V) for iterating over all the vertex and O(logV) for adding it to the Heap.
     * @param adjacencyWeightMatrix
     * @param usedNodes
     * @param minHeap
     * @param node
     */
    private static void updateNearNodes(int[][] adjacencyWeightMatrix, Set<Integer> usedNodes, Queue<Node> minHeap, Node node) {
        for(int j = 1; j< adjacencyWeightMatrix.length; j++) {
            if (!usedNodes.contains(j) && adjacencyWeightMatrix[node.nodeId][j] > 0) {
                // Instead of decrease key we are adding it to the queue as decrease key is not present
                // in the Priority Queue implementation.
                minHeap.offer(new Node(node.nodeId,j, adjacencyWeightMatrix[node.nodeId][j]));
            }
        }
    }
}
