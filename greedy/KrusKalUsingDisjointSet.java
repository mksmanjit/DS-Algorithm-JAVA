package greedy;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A minimum spanning tree (MST) or minimum weight spanning tree is a subset of the edges of
 * a connected, edge-weighted undirected graph that connects all the vertices together,
 * without any cycles and with the minimum possible total edge weight.
 *
 * Kruskal algorithm grow like a forest where Prism algorithm grow like a tree.
 *
 * Kruskal’s algorithm runs faster in sparse graphs.
 * Prim’s algorithm runs faster in dense graphs.
 *
 * Time Complexity:- O(ELogE), where E is the edges.
 * as we know E=V^2
 * then O(ELogV^2) = O(2*ELogV) = O(ELogV)
 *
 * So Time Complexity we a can also right as - O(ELogV) or O(ELogE)
 *
 * Space Complexity - O(V), V is for holding the vertex in disjoint set.
 */
public class KrusKalUsingDisjointSet {

    static class Node {
        int parentId;
        int nodeId;
        int weight;
        Node(int parentId, int nodeId,int weight) {
            this.parentId = parentId;
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
     * Time Complexity - O(ELogE)
     * @param adjacencyWeightMatrix
     * @param mstNodes
     * @return
     */
    public static int mst(int[][] adjacencyWeightMatrix, int[][] mstNodes) {
        Queue<Node> minHeap = new PriorityQueue<>(Comparator.comparing(Node::getWeight)); // O(E)
        for (int i = 1; i < adjacencyWeightMatrix.length; i++) {
            for (int j = 1; j < adjacencyWeightMatrix.length; j++) {
                if (adjacencyWeightMatrix[i][j] > 0) {
                    minHeap.offer(new Node(i,j, adjacencyWeightMatrix[i][j]));
                }
            }
        }
        int i = 0;
        DisJointSet disJointSet = new DisJointSet(adjacencyWeightMatrix.length); // O(V)
        int minCost = 0;
        while(!minHeap.isEmpty()) { // O(E)
            Node node = minHeap.poll(); //O(logE)
            int parent1 = disJointSet.find(node.parentId); //O(1)
            int parent2 = disJointSet.find(node.nodeId);  //O(1)
            if(parent1 == parent2) {
                continue;
            }
            minCost += node.getWeight();
            disJointSet.union(parent1, parent2); //O(1)
            mstNodes[i][0] = node.parentId;
            mstNodes[i][1] = node.nodeId;
            i++;
        }
        return  minCost;
    }
}
