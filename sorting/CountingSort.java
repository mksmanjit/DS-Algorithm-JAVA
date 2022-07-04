package sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * Counting Sort is efficient for small range of values.
 * Counting sort is efficient if the range of input data is not significantly greater
 * than the number of objects to be sorted. Consider the situation where the input
 * sequence is between range 1 to 10K and the data is 10, 5, 10K, 5K.
 *
 *  Time Complexity : O(n + k), where k is the range of data.
 *  Space Complexity: O(k), where k is the range of data.
 *
 * --------------------------------------------
 * Analysis
 * ---------------------------------------------
 * 1) Iterating over full list of input O(n)
 * 2) Setting values in the count array at an index of input value
 * 3) If duplicate value came then increase the counter to 1
 *
 * 1) Iterate over the count array O(k)
 *    and consider those index having value greater than 1
 * 2) Set the value in input array, value as count array index.
 *
 * So time complexity is O(n + k)
 */
public class CountingSort {

    public static void main(String[] args) {
        int[] unsortedList = {4, 5, 1, 2, 3, 7, 8, 9, 6};
        System.out.println("unsorted List" + Arrays.stream(unsortedList).boxed().collect(Collectors.toList()));
        sort_1(unsortedList, 10);
        System.out.println("Sort 1- sorted List" + Arrays.stream(unsortedList).boxed().collect(Collectors.toList()));

        sort_2(unsortedList, 10);
        System.out.println("Sort 2- sorted List" + Arrays.stream(unsortedList).boxed().collect(Collectors.toList()));

        unsortedList = new int[]{4, 5, 3, 9, 8,8};
        System.out.println("unsorted List" + Arrays.stream(unsortedList).boxed().collect(Collectors.toList()));
        sort_1(unsortedList, 10);
        System.out.println("Sort 1- sorted List" + Arrays.stream(unsortedList).boxed().collect(Collectors.toList()));

        sort_2(unsortedList, 10);
        System.out.println("Sort 2- sorted List" + Arrays.stream(unsortedList).boxed().collect(Collectors.toList()));

        int[] alreadySortedList = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("unsorted List" + Arrays.stream(alreadySortedList).boxed().collect(Collectors.toList()));
        sort_1(unsortedList, 10);
        System.out.println("Sort 1- sorted List" + Arrays.stream(alreadySortedList).boxed().collect(Collectors.toList()));

        sort_2(unsortedList, 10);
        System.out.println("Sort 2- sorted List" + Arrays.stream(alreadySortedList).boxed().collect(Collectors.toList()));
    }

    /**
     * Time Complexity - O(n+k)
     * Space Complexity - O(k)
     * @param unsortedList
     * @param maxRange
     */
    public static void sort_1(int[] unsortedList, int maxRange) {
        int[] countArray = new int[maxRange];
        // store count of each input value
        for (int i = 0; i < unsortedList.length; i++) {  // O(n)
            countArray[unsortedList[i]] += 1;
        }
        int count = 0;
        // Iterate over count array and reset the input array with count array index.
        for(int i = 0; i < countArray.length; i++) { // O(k)
            for(int j = 0;j< countArray[i]; j++) {
                unsortedList[count++] = i;
            }
        }
    }

    /**
     * This is used in case of Radix sort as index of count array is not same as input value.
     *
     * Time Complexity - O(n+k)
     * Space Complexity - O(n+k)
     * @param unsortedList
     * @param maxRange
     */
    public static void sort_2(int[] unsortedList, int maxRange) {
        int[] countArray = new int[maxRange];
        int[] outputArray = new int[unsortedList.length];
        // store count of each input value
        for (int i = 0; i < unsortedList.length; i++) {  // O(n)
            countArray[unsortedList[i]] += 1;
        }
        // Change count[i] so that count[i] now contains actual
        // position of this input in output array
        for(int i = 1; i < countArray.length; i++) { // O(k)
            countArray[i] += countArray[i-1];
        }

        // Build the output array
        for(int i = 0; i < unsortedList.length; i++) { // O(n)
            outputArray[countArray[unsortedList[i]] - 1] = unsortedList[i];
            --countArray[unsortedList[i]];
        }
        // Copy the output array to input array, so that input array now
        // contains sorted values
        for (int i = 0; i < unsortedList.length; ++i) { // O(n)
            unsortedList[i] = outputArray[i];
        }
    }
}
