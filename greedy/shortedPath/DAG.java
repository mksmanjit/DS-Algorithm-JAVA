package greedy.shortedPath;

import java.util.Arrays;
import java.util.Stack;

/**
 * A directed acyclic graph (DAG) is a conceptual representation of a series of activities.
 * It consists of vertices and edges (also called arcs), with each edge directed from one
 * vertex to another, such that following those directions will never form a closed loop.
 * A directed graph is a DAG if and only if it can be topologically ordered,
 * by arranging the vertices as a linear ordering that is consistent with all edge directions.
 *
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u v,
 * vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.
 *
 * In DFS, we print a vertex and then recursively call DFS for its adjacent vertices.
 * In topological sorting, we need to print a vertex before its adjacent vertices.
 *
 * Topological Sorting is mainly used for scheduling jobs from the given dependencies among jobs.
 * In computer science, applications of this type arise in instruction scheduling, ordering of formula cell evaluation.
 *
 * Time Complexity- O(V+E)
 * Space Complexity - O(V) extra space is needed for the stack
 *
 */
public class DAG {

    public static void main(String[] args) {

//        int[][] weightedPathMatrix = {
//                {0,5,3,0,0,0},
//                {0,0,2,6,0,0},
//                {0,0,0,7,4,2},
//                {0,0,0,0,-1,1},
//                {0,0,0,0,0,-2},
//                {0,0,0,0,0,0},
//        };
        int[][] weightedPathMatrix = {
                {0,0,0,0,0,0,2},
                {5,0,4,0,0,0,0},
                {0,0,0,6,0,0,0},
                {0,0,0,0,0,7,0},
                {1,0,0,0,0,3,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
        };
        long[] destinations = shortestPath(weightedPathMatrix);
        for (int i = 0; i < destinations.length; i++) {
            System.out.println("Node " + i + " Weight " + destinations[i]);
        }

    }

    /**
     * Time Complexity - O(V + E)
     *
     * @param weightedPathMatrix
     * @param visited
     * @param stack
     * @param src
     */
    public static void topologicalSort(int[][] weightedPathMatrix,boolean[] visited, Stack stack, int src) {
        visited[src] = true;
        for(int i = 0;i<weightedPathMatrix.length;i++) {
            if (weightedPathMatrix[src][i] != 0 && !visited[i]) {
                topologicalSort(weightedPathMatrix, visited, stack, i);
            }
        }
        stack.push(src);

    }

    /**
     * Time Complexity - O(V+E)
     *
     * @param weightedPathMatrix
     * @return
     */
    private static long[] shortestPath(int[][] weightedPathMatrix) {
        long[] destinations = new long[weightedPathMatrix.length];
        Arrays.fill(destinations, Integer.MAX_VALUE);
        destinations[1] = 0;
        boolean[] visited = new boolean[weightedPathMatrix.length];
        Arrays.fill(visited, false);
        Stack<Integer> stack = new Stack<>();
        // Here we are iterating over all the vertex but we are visiting edge once only.
        // So Time Complexity should be O(V + E) not O(VE)
        for (int i = 0; i < weightedPathMatrix.length; i++) { // O(V)
            if (!visited[i]) {
                topologicalSort(weightedPathMatrix, visited, stack, i); // O(E)
            }
        }
        // Its looks like time complexity for this one should be O(VE), but if you think
        // we are visiting all the Edges once only, So instead of saying O(VE), we can
        // say the Time Complexity is O(E)
        while(!stack.isEmpty()) { // O(V)
            int node = stack.pop();
            for(int i = 0 ;i<weightedPathMatrix.length;i++) { // O(E)
                if(weightedPathMatrix[node][i] != 0 && destinations[i] > (destinations[node] + weightedPathMatrix[node][i])) {
                    destinations[i] = destinations[node] + weightedPathMatrix[node][i];
                }
            }
        }
        return destinations;
    }
}
