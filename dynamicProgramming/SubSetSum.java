package dynamicProgramming;

/**
 * Set of non-negative integers, and a value sum, determine if there is a subset of the
 * given set with sum equal to given sum.
 *
 *  Time Complexity Using Brute Force - O(2^n)
 *  Time Complexity Using memorization - O(m*s) , where m in the set size and s is the sum.
 *
 *  Space Complexity Brute Force- O(s), where s is the sum.
 *  Space Complexity Using memorization - O(m*s) , where m in the set size and s is the sum.
 */
public class SubSetSum {
    public static void main(String[] args) {
        int[] set = {3, 34, 4, 12, 5, 2};
        int sum = 30;
        boolean isPresent = isSubSetSumBruteForce(set, set.length - 1, sum);
        System.out.println("Sub Set Sum Using Recursive Call BruteForce " + sum + " is present " + isPresent);

        isPresent = isSubSetSumBottomUpTabularMethod(set, sum);
        System.out.println("Sub Set Sum Using DP " + sum + " is present " + isPresent);

    }

    /**
     * Time Complexity- O(2^n)
     * Space Complexity - O(s), where s is the sum, Space Complexity is the max stack size in this case.
     *
     * @param set
     * @param index
     * @param sum
     * @return
     */
    public static boolean isSubSetSumBruteForce(int[] set, int index, int sum) {
        if (sum == 0) return true;
        if (index == -1 || sum < 0) return false;
        return isSubSetSumBruteForce(set, index - 1, sum - set[index]) || isSubSetSumBruteForce(set, index - 1, sum);
    }

    /**
     * Time Complexity - O(m*s) where m is the length of given set and s is the sum
     * Space Complexity - O(m*s)
     * @param set
     * @param sum
     * @return
     */
    public static boolean isSubSetSumBottomUpTabularMethod(int[] set, int sum) {
        Boolean[][] dp = new Boolean[set.length + 1][sum + 1];
        for (int i = 1; i < sum + 1; i++) {
            dp[0][i] = false;
        }
        for (int i = 0; i < set.length + 1; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if(set[i-1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j - set[i-1]];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1] ;
    }
}
