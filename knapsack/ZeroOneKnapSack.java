package knapsack;

/**
 *  Given the weights and values of n items, we need to put these items in a knapsack of
 *  capacity W to get the maximum total value in the knapsack.
 *
 *  In the 0-1 Knapsack problem, we are not allowed to break items. We either take the whole item or don’t take it.
 *
 *  Time Complexity Using Brute Force - O(2^n)
 *  Time Complexity Using memorization - O(n*w), where n is the number of items(each contain profit and weight) and w is
 *  max weight
 *
 *  Space Complexity Brute Force- O(n)
 *  Space Complexity Using memorization - O(n*w), where n is the number of items(each contain profit and weight) and w is
 *  max weight
 */
public class ZeroOneKnapSack {
    public static void main(String[] args) {
//        int[][] valueWeightPair = {{60,10},{100,20},{120,30}};
//        int capacity = 50;

        int[][] valueWeightPair = {{10,1},{15,2},{40,3}};
        int capacity = 2;
        int maxProfit= getMaxProfitBruteForce(valueWeightPair, capacity, 0);
        System.out.println("Brute Force Max Profit for capacity " + capacity + " is " + maxProfit);

        int[][] dp = new int[valueWeightPair.length][capacity + 1];
        for(int i =0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++){
                dp[i][j] = -1;
            }
        }
        maxProfit= getMaxProfitMemorization(valueWeightPair,dp, capacity, 0);
        System.out.println("Memorization Max Profit for capacity " + capacity + " is " + maxProfit);

        maxProfit= getMaxProfitBottomUp(valueWeightPair, capacity);
        System.out.println("Botton Up Max Profit for capacity " + capacity + " is " + maxProfit);


    }

    /**
     * Time Complexity - O(2^n)
     * Space Complexity - O(n)
     *
     * @param valueWeightPair
     * @param capacity
     * @param index
     * @return
     */
    private static int getMaxProfitBruteForce(int[][] valueWeightPair, int capacity, int index) {
        if (index == valueWeightPair.length || capacity == 0) return 0;
        int maxValue = 0;
        if (capacity >= valueWeightPair[index][1]) {
            maxValue = valueWeightPair[index][0] + getMaxProfitBruteForce(valueWeightPair, capacity - valueWeightPair[index][1], index + 1);
        }
        return Math.max(maxValue, getMaxProfitBruteForce(valueWeightPair, capacity, index + 1));
    }

    /**
     * Time Complexity - O(n*w), where n is the profitWeightInput and w is the capacity.
     * Space Complexity - O(n*w) + O(n)
     *
     * @param valueWeightPair
     * @param dp
     * @param capacity
     * @param index
     * @return
     */
    private static int getMaxProfitMemorization(int[][] valueWeightPair, int[][] dp, int capacity, int index) {
        if (index == valueWeightPair.length || capacity == 0) return 0;
        if(dp[index][capacity] != -1) {
            return dp[index][capacity];
        }
        System.out.println("Hello" + index + ">>" + capacity);
        int maxValue = 0;
        if (capacity >= valueWeightPair[index][1]) {
            maxValue = valueWeightPair[index][0] + getMaxProfitMemorization(valueWeightPair,dp, capacity - valueWeightPair[index][1], index + 1);
        }
        maxValue = Math.max(maxValue, getMaxProfitMemorization(valueWeightPair,dp, capacity, index + 1));
        dp[index][capacity] = maxValue;
        return maxValue;
    }

    /**
     * Time Complexity - O(n*w), where n is the profitWeightInput and w is the capacity.
     * Space Complexity - O(n*w)
     *
     * @param valueWeightPair
     * @param capacity
     * @return
     */
    private static int getMaxProfitBottomUp(int[][] valueWeightPair, int capacity) {
        int[][] dp = new int[valueWeightPair.length + 1][capacity + 1];
        for(int i =1;i<dp.length;i++) {
            for(int j=1;j<dp[0].length;j++) {
                if(valueWeightPair[i-1][1] <= j) {
                    System.out.println("Bye" + (i-1) + ">>" + j);
                    dp[i][j] = Math.max(dp[i-1][j], valueWeightPair[i-1][0] + dp[i-1][j - valueWeightPair[i-1][1]]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return  dp[dp.length -1][dp[0].length-1];
    }
}
