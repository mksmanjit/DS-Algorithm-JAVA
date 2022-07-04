package sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * Radix sort works on sorting based on individual digit or letter position.
 * We must start sorting from the rightmost position and use a stable algorithm at each position.
 *
 *  Time Complexity : O(d*(n+b)), where b is the base for representing numbers, for example, for the decimal system, b is 10.
 *  What is the value of d? If n^c (c is some constant) is the maximum possible value, then d would be O(clogb(n))
 *  So overall time complexity is O((n+b) * clogb(n))
 *  Final Time Complexity : O(nlogb(n))
 *
 *
 *  Space Complexity: O(n+b).
 *
 * --------------------------------------------
 * Analysis
 * ---------------------------------------------
 * 1) Iterate from 1 to max digit count. O(d)
 * 2) Store count of each input value in count Array O(n)
 * 3) Change count[i] so that count[i] now contains actual
 *    position of this input in output array O(b)
 * 4) Build the output array,in reverse order so that first value should come
 *    first in the output array. O(n)
 * 5) Copy the output array to input array, so that input array now
 *    contains sorted values. O(n)
 *
 *  So time complexity is O(d*(n+b))
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] unsortedList = {123, 159, 1, 20, 999, 2, 88, 78, 6};
        System.out.println("unsorted List" + Arrays.stream(unsortedList).boxed().collect(Collectors.toList()));
        sort(unsortedList);
        System.out.println("sorted List" + Arrays.stream(unsortedList).boxed().collect(Collectors.toList()));

        int[] alreadySortedList = {1, 2, 6, 20, 78, 88, 123, 159, 999};
        System.out.println("unsorted List" + Arrays.stream(alreadySortedList).boxed().collect(Collectors.toList()));
        sort(unsortedList);
        System.out.println("sorted List" + Arrays.stream(alreadySortedList).boxed().collect(Collectors.toList()));
    }


    /**
     * Time Complexity - O(d*(n+b))
     * Space Complexity - O(n+b)
     * @param unsortedList
     */
    public static void sort(int[] unsortedList) {
        int maxValue = getMaxValue(unsortedList);
        // Iterate from 1 to max digit count.
        for (int exp = 1; maxValue/exp > 0; exp *= 10) { // O(d), d is the max digit in given input.
            countSort(unsortedList, exp);
        }
    }

    private static int getMaxValue(int[] inputs) {
        int max = Integer.MIN_VALUE;
        for(int input: inputs) {
            max = Math.max(input, max);
        }
        return max;
    }

    /**
     * Time Complexity - O(n+b)
     * Space Complexity - O(n+b)
     * @param unsortedList
     * @param exp
     */
    private static void countSort(int[] unsortedList, int exp) {
        // b is the base for representing numbers, for example, for the decimal system, b is 10
        int[] countArray = new int[10];
        int[] outputArray = new int[unsortedList.length];

        // store count of each input value
        for (int i = 0; i < unsortedList.length; i++) {  // O(n)
            countArray[(unsortedList[i] / exp) % 10] += 1;
        }
        // Change count[i] so that count[i] now contains actual
        // position of this input in output array
        for(int i = 1; i < countArray.length; i++) { // O(b)
            countArray[i] += countArray[i-1];
        }

        // Build the output array,in reverse order so that first value should come
        // first in the output array.
        for(int i = unsortedList.length - 1 ; i >= 0; i--) { // O(n)
            outputArray[countArray[(unsortedList[i] / exp) % 10] - 1] = unsortedList[i];
            --countArray[(unsortedList[i] / exp) % 10];
        }
        // Copy the output array to input array, so that input array now
        // contains sorted values
        for (int i = 0; i < unsortedList.length; ++i) { // O(n)
            unsortedList[i] = outputArray[i];
        }
    }
}
