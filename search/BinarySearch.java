package search;

/**
 * Binary Search works over sorted data only.
 *
 * 1. Iterative
 * Time Complexity - O(log n) // Iterating over half of the list items for determining the searched value
 * Space Complexity - O(1) // We are not using constant space only for holding the index
 *
 * 2. Recursive
 * Time Complexity - O(log n) // Iterating over half the list items for determining the searched value
 * Space Complexity - O(long n) // We are not using constant space only for holding the index
 *
 * --------------------------------------------
 * Analysis
 * ---------------------------------------------
 * 1st Iteration, Length of array = n
 * 2nd Iteration, Length of array = n/2
 * 3rd Iteration, Length of array = (n/2)/2 = n/2^2
 * ..
 * Kth Iteration, Length of array = n/2^k
 * After k iterations, the length of array becomes 1
 * Length of array = n/2^k  = 1
 * n = 2^k
 * Applying log function on both sides:
 * log2 (n) = log2 (2^k)
 * log2 (n) = k log2 (2) // Log2 (2) = 1
 * k = long2 (n)
 *
 */
public class BinarySearch {

    public static void main(String args[]) {
        int[] items = {1,2,3,4,5,6,7,8,9};

        // 1. Iterative
        int index = binarySearchIterative(items, 2);
        System.out.println(index + " For Value 2");

        index = binarySearchIterative(items, 4);
        System.out.println(index + " For Value 4");

        index = binarySearchIterative(items, 9);
        System.out.println(index + " For Value 9");

        index = binarySearchIterative(items, 7);
        System.out.println(index + " For Value 7");

        // 2. Recursive
        int start = 0;
        int end = items.length - 1;

        index = binarySearchRecursive(items, 2, start , end);
        System.out.println(index + " For Value 2");

        index = binarySearchRecursive(items, 4, start , end);
        System.out.println(index + " For Value 4");

        index = binarySearchRecursive(items, 9, start , end);
        System.out.println(index + " For Value 9");

        index = binarySearchRecursive(items, 7, start , end);
        System.out.println(index + " For Value 7");
    }

    public static int binarySearchIterative(int[] items, int valueToSearch) {
        int index = -1; // O(1)
        int start = 0;
        int end = items.length - 1;
        while (start <= end) { // O(log n)
            /*
             * Notice how the middle index is generated (int mid = low + ((high – low) / 2).
             * This to accommodate for extremely large arrays. If the middle index is generated simply by getting the middle index (int mid = (low + high) / 2),
             * an overflow may occur for an array containing 230 or more elements as the sum of low + high could easily exceed the maximum positive int value
             */
            int mid = start + ((end - start) / 2); // O(1)
            if (items[mid] == valueToSearch) { // O(1)
                index = mid;
                break;
            } else if (items[mid] > valueToSearch) {
                end = mid - 1;
            } else {
                start = mid + 1;

            }

        }
        return index;
    }

    public static int binarySearchRecursive(int[] items, int valueToSearch,int start, int end) {
        /*
         * Notice how the middle index is generated (int mid = low + ((high – low) / 2).
         * This to accommodate for extremely large arrays. If the middle index is generated simply by getting the middle index (int mid = (low + high) / 2),
         * an overflow may occur for an array containing 230 or more elements as the sum of low + high could easily exceed the maximum positive int value
         */
        int mid = start + ((end - start) / 2); // O(1)
        if (items[mid] == valueToSearch) { // O(1)
            return mid;
        } else if (items[mid] > valueToSearch) {
            return binarySearchRecursive(items, valueToSearch, start, mid - 1); // O(log n)
        } else {
            return binarySearchRecursive(items, valueToSearch, mid + 1, end);  // O(log n)
        }
    }

}
