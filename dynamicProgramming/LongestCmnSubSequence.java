package dynamicProgramming;

/**
 *  Given two sequences, find the length of longest subsequence present in both of them.
 *  A subsequence is a sequence that appears in the same relative order, but not necessarily
 *  contiguous. For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences
 *  of “abcdefg”.
 *
 *  Time Complexity Using Brute Force - O(2^n)
 *  Time Complexity Using memorization - O(m*n)
 *
 *  Space Complexity Brute Force- O(m+n)
 *  Space Complexity Using memorization - O(m*n)
 *
 */
public class LongestCmnSubSequence {
    public static void main(String[] args) {
        String str1 = "BCDAACD";
        String str2 = "ACDBAC";
        String maxLcs = lcsRecursiveBruteForce(str1, str2, str1.length() - 1, str2.length() - 1);
        System.out.println("Longest Sub Sequence Using Recursive Call BruteForce " + new StringBuilder(maxLcs).reverse().toString());
        String[][] dp = new String[str1.length()][str2.length()];
        maxLcs = lcsRecursiveMemorization(str1, str2, str1.length() - 1, str2.length() - 1, dp);
        System.out.println("Longest Sub Sequence Using Recursive Call BruteForce " + new StringBuilder(maxLcs).reverse().toString());
        maxLcs = lcsTopDownTabularMethod(str1,str2);
        System.out.println("Longest Sub Sequence Using DP " + new StringBuilder(maxLcs).reverse().toString());
    }

    /**
     * Time Complexity - O(2^n+m), where n is the length on larger string
     * Space Complexity - O(m+n)
     * @param str1
     * @param str2
     * @param i
     * @param j
     * @return
     */
    public static String lcsRecursiveBruteForce(String str1, String str2, int i, int j) {
        if (i < 0 || j < 0) {
            return "";
        }
        if (str1.charAt(i) == str2.charAt(j)) {
            return (str1.charAt(i) + "") + lcsRecursiveBruteForce(str1, str2, i - 1, j - 1);
        }
        String lcs1 = lcsRecursiveBruteForce(str1, str2, i - 1, j);
        String lcs2 = lcsRecursiveBruteForce(str1, str2, i, j - 1);
        if (lcs1.length() > lcs2.length()) {
            return lcs1;
        } else {
            return lcs2;
        }
    }

    /**
     * Time Complexity - O(m*n), where m is the length of str1 and n is the length of str2.
     * Space Complexity - O(m*n)
     *
     * @param str1
     * @param str2
     * @param i
     * @param j
     * @return
     */
    public static String lcsRecursiveMemorization(String str1, String str2, int i, int j, String[][] dp) {
        if (i < 0 || j < 0) {
            return "";
        }
        if(dp[i][j] != null) return dp[i][j];
        if (str1.charAt(i) == str2.charAt(j)) {
            dp[i][j] = (str1.charAt(i) + "") + lcsRecursiveMemorization(str1, str2, i - 1, j - 1, dp);
            return dp[i][j];
        }
        String lcs1 = lcsRecursiveMemorization(str1, str2, i - 1, j, dp);
        String lcs2 = lcsRecursiveMemorization(str1, str2, i, j - 1, dp);
        if (lcs1.length() > lcs2.length()) {
            dp[i][j] = lcs1;
        } else {
            dp[i][j] = lcs2;
        }
        return dp[i][j];
    }

    /**
     * Time Complexity - O(m*n), where m is the length of str1 and n is the length of str2.
     * Space Complexity - O(m*n)
     * @param str1
     * @param str2
     * @return
     */
    public static String lcsTopDownTabularMethod(String str1, String str2) {
        int[][] grid = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    grid[i + 1][j + 1] = grid[i][j] + 1;
                } else {
                    grid[i + 1][j + 1] = Math.max(grid[i + 1][j], grid[i][j + 1]);
                }
            }
        }
        return getLongestCommonSubStrSequence(str1, str2, grid);
    }

    private static String getLongestCommonSubStrSequence(String str1, String str2, int[][] grid) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = str1.length();
        int j = str2.length();
        while(i > 0 && j > 0) {
            if (str1.charAt(i-1) == str2.charAt(j-1)) {
                stringBuilder.append(str1.charAt(i-1));
                i--;
                j--;
            } else {
                if(grid[i][j] == grid[i-1][j]) {
                    i--;
                } else {
                    j--;
                }
            }

        }
        return stringBuilder.toString();
    }
}

