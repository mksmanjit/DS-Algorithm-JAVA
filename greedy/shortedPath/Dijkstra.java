package greedy.shortedPath;

import java.util.*;

/**
 * Dijkstra finds the shortest distances from the source to all vertices.
 * Dijkstra’s algorithm doesn’t work for graphs with negative weight cycles.
 * It may give correct results for a graph with negative edges but you must allow
 * a vertex can be visited multiple times and that version will lose its fast time complexity.
 *
 * Time Complexity - O(ELogV)
 * Space Complexity - O(V), holding vertex in min heap.
 */
public class Dijkstra {

    static class Node {
        int id;
        int weight;
        Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return this.weight;
        }

    }

    public static void main(String[] args) {

        int[][] weightedPathMatrix = { {0,4,0,0,0,0,0,8,0},
                                       {4,0,8,0,0,0,0,11,0},
                                       {0,8,0,7,0,4,0,0,2},
                                       {0,0,7,0,9,14,0,0,0},
                                       {0,0,0,9,0,10,0,0,0},
                                       {0,0,4,14,10,0,2,0,0},
                                       {0,0,0,0,0,2,0,1,6},
                                       {8,11,0,0,0,0,1,0,7},
                                       {0,0,2,0,0,0,6,7,0},
                                     };

        Node[] destinations = shortestPath(weightedPathMatrix);
        for(int i = 0 ;i <destinations.length;i++) {
            System.out.println("Node " + i + " Weight " + destinations[i].weight);
        }

    }

    /**
     * Time Complexity = O(VLogV+ELogV) = O(ELogV)
     * Space Complexity = O(V)
     * @param weightedPathMatrix
     * @return
     */
    public static Node[] shortestPath(int[][] weightedPathMatrix) {
        Node[] destinations = new Node[weightedPathMatrix.length];
        for (int i = 0; i < weightedPathMatrix.length; i++) {
            destinations[i] = new Node(i,Integer.MAX_VALUE);
        }
        Node sourceNode = destinations[0];
        sourceNode.setWeight(0);
        Set<Integer> relaxedNodes = new HashSet<>();

        Queue<Node> queue = new PriorityQueue<>(Comparator.comparing(Node::getWeight)); // O(V)
        queue.offer(sourceNode);

        while(!queue.isEmpty()) { // O(V)
            Node node = queue.poll(); // O(LogV)
            int src = node.id;
            for(int dest = 0;dest<weightedPathMatrix.length;dest++) { // O(E)
                relaxEdges(weightedPathMatrix, destinations, relaxedNodes, queue, src, dest);
            }
            relaxedNodes.add(src);
        }

        return destinations;

    }

    private static void relaxEdges(int[][] weightedPathMatrix, Node[] destinations, Set<Integer> relaxedNodes, Queue<Node> queue, int src, int dest) {
        if(!relaxedNodes.contains(dest) && weightedPathMatrix[src][dest] != 0 && destinations[dest].getWeight() > (destinations[src].getWeight() + weightedPathMatrix[src][dest])) {
            destinations[dest] = new Node(dest, destinations[src].getWeight() + weightedPathMatrix[src][dest]);
            queue.offer(destinations[dest]); // O(logV)
        }
    }
}
