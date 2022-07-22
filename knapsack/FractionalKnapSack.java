package knapsack;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given the weights and values of n items, we need to put these items in a knapsack of
 * capacity W to get the maximum total value in the knapsack.
 *
 * In the 0-1 Knapsack problem, we are not allowed to break items. We either take the whole item or don’t take it.
 *
 * Time Complexity - O(nlogn)
 * Space Complexity - O(1)
 */
public class FractionalKnapSack {
    public static void main(String[] args) {
        int[][] valueWeightPair = {{60,10},{100,20},{120,30}};
        int capacity = 50;
        int maxProfit= getMaxProfit(valueWeightPair, capacity);
        System.out.println("Max Profit for capacity " + capacity + " is " + maxProfit);
    }

    /**
     * Time Complexity - O(nlogn)
     * Space Complexity - o(1)
     * @param valueWeightPair
     * @param capacity
     * @return
     */
    private static int getMaxProfit(int[][] valueWeightPair, int capacity) {
        Queue<int[]> maxHeap = new PriorityQueue<>((p1,p2) -> Float.compare(p2[0]/p2[1],p1[0]/p1[1])); // O(n)
        for(int i = 0; i< valueWeightPair.length;i++) {
            maxHeap.offer(valueWeightPair[i]);
        }
        int maxProfit = 0;
        while(!maxHeap.isEmpty()) { // O(n)
            int[] pair = maxHeap.poll(); // O(logn)
            if(capacity >= pair[1]) {
                maxProfit +=pair[0];
                capacity -= pair[1];
            } else{
                maxProfit += pair[0]/pair[1] * capacity;
                capacity = 0;
            }
            if(capacity == 0) break;
        }
        return  maxProfit;
    }
}
