package sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * Quick Sort in its general form is an in-place sort (i.e. it doesn’t require any extra storage) whereas merge sort
 * requires O(N) extra storage, N denoting the array size which may be quite expensive
 *
 *  Time Complexity : Best = Θ(nlogn) and Average=Ω(nlogn) and Worst = O(n2)
 *  O(n^2) - when list is already sorted in reverse order.
 *  Space Complexity: O(1)
 *
 * --------------------------------------------
 * Analysis
 * ---------------------------------------------
 * Pass 1- T(n) = 2*T(n/2^1) + c*n               // c is some constant time
 * Pass 2 - T(n/2^1) = 2*T(n/2^2) + c*n/2
 * Pass 3 - T(n/2^2) = 2*T(n/2^3) + c*n/2^2
 * ...
 * ...
 * Subsitute 2 and 3 in one
 * T(n) = 2*(2*2*T(n/2^3) + c*n + c*n/2 + c*n/2^2
 * T(n) = 2 ^ 3(T(n/2^3) + 3*c*n
 *
 * Pass k - T(n/2^(k-1)) = 2^k*T(n/2^k) + c*n*k
 * Assume n/2^k = 1, n = 2^k
 * Apply log on both side logn = klog2, k =logn
 *
 * T(n) =  n * 1 + c*n*logn
 *
 * leading term in this is nlogn, So time complexity is O(nlogn)
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] unsortedList = {3,2,1};
        System.out.println("unsorted List" + Arrays.stream(unsortedList).boxed().collect(Collectors.toList()));
        sort(unsortedList, 0, unsortedList.length - 1);
        System.out.println("sorted List" + Arrays.stream(unsortedList).boxed().collect(Collectors.toList()));

        unsortedList = new int[]{4, 5, 1, 2, 3, 7, 8, 9, 6};
        System.out.println("unsorted List" + Arrays.stream(unsortedList).boxed().collect(Collectors.toList()));
        sort(unsortedList, 0, unsortedList.length - 1);
        System.out.println("sorted List" + Arrays.stream(unsortedList).boxed().collect(Collectors.toList()));

        int[] alreadySortedList = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("unsorted List" + Arrays.stream(alreadySortedList).boxed().collect(Collectors.toList()));
        sort(unsortedList, 0, unsortedList.length - 1);
        System.out.println("sorted List" + Arrays.stream(alreadySortedList).boxed().collect(Collectors.toList()));
    }

    /**
     * Time Complexity: Best and Average is O(nlogn) and Worst is O(n^2)
     * Space Complexity:O(1), No extra space used.
     *
     * @param unsortedList
     * @param l
     * @param r
     */
    public static void sort(int[] unsortedList, int l, int r) { // O(logn)
        if(l >= r) return;
        int m = partition(unsortedList, l ,r);
        sort(unsortedList, l, m-1);
        sort(unsortedList, m + 1, r);
    }

    /**
     * Time Complexity: O(n) we are iterating over n-1 elements.
     * Space Complexity: O(1), No extra space used.
     *
     * @param unsortedList
     * @param l
     * @param r
     */
    public static int partition(int[] unsortedList, int l, int r) { // O(n)
        int lastNumber = unsortedList[r];
        int i = l - 1;
        for (int j = l; j < r ; j++) { // O(n)
            if (lastNumber >= unsortedList[j]) {
                i++;
                int temp = unsortedList[i];
                unsortedList[i] = unsortedList[j];
                unsortedList[j] = temp;
            }
        }
        int temp = unsortedList[i + 1];
        unsortedList[i + 1] = unsortedList[r];
        unsortedList[r] = temp;

        return i + 1;
    }
}
