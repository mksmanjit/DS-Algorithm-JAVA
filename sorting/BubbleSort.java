package sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *  Time Complexity : O(n^2)
 *  Space Complexity: O(1)
 *
 * --------------------------------------------
 * Analysis
 * ---------------------------------------------
 * Pass 1- Number of comparisons = (n-1)
 * Pass 2- Number of comparisons = (n-2)
 * Pass 3- Number of comparisons = (n-3)
 * ...
 * ...
 * Pass (n - 1)- Number of comparisons = (1)
 *
 * Now , calculating total number of comparison required to sort the array
 * (n-1) + (n-2) +  (n-3) + . . . 2 + 1
 * n (n-1)/2
 *
 * leading term in this is n^2, So time complexity is O(n^2)
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] unsortedList = {4, 5, 1, 2, 3, 7, 8, 9, 6};
        System.out.println("unsorted List" + Arrays.stream(unsortedList).boxed().collect(Collectors.toList()));
        sort(unsortedList);
        System.out.println("sorted List" + Arrays.stream(unsortedList).boxed().collect(Collectors.toList()));

        int[] alreadySortedList = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("unsorted List" + Arrays.stream(alreadySortedList).boxed().collect(Collectors.toList()));
        sort(unsortedList);
        System.out.println("sorted List" + Arrays.stream(alreadySortedList).boxed().collect(Collectors.toList()));
    }

    public static void sort(int[] unsortedList) {
        for (int i = 0; i < unsortedList.length; i++) {  // O(n)
            for (int j = 0; j < unsortedList.length - i - 1; j++) { // O(n)
                if (unsortedList[j] > unsortedList[j+1]) { // O(1)
                    int temp = unsortedList[j];
                    unsortedList[j] = unsortedList[j + 1];
                    unsortedList[j + 1] = temp;
                }
            }
        }
    }
}
