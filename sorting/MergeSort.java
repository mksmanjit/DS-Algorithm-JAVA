package sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * This algorithm is suitable for large data sets as its best,average and worst complexity is O(nlogn).
 * Merge sort algorithm requires an additional memory space of 0(n) for the temporary array.
 *
 *  Time Complexity : O(nlogn), O(n) is for merging and logn is for depth of the tree.
 *  Space Complexity: O(n + logn), leading term is n, So O(n)
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
public class MergeSort {

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
     * Time Complexity: O(logn), which is the depth of the tree and each step we are reducing it by half.
     * Space Complexity:O(logn), which is the depth of the stack.
     *
     * @param unsortedList
     * @param l
     * @param r
     */
    public static void sort(int[] unsortedList, int l, int r) {
        if(l >= r) return;
        int m = (l + r) / 2;
        sort(unsortedList, l, m);
        sort(unsortedList, m + 1, r);
        merge(unsortedList, l, m, r);
    }

    /**
     * Time Complexity: O(l+r), which is equal to O(n) as we are dividing the list and then merging.
     * Space Complexity: O(l+r), which is equal to O(n) as we are dividing the list from the main list.
     *
     * @param unsortedList
     * @param l
     * @param m
     * @param r
     */
    public static void merge(int[] unsortedList, int l, int m, int r) {
        int lSize = m - l + 1;
        int rSize = r - m;
        int[] leftList = new int[lSize + 1];
        int[] rightList = new int[rSize + 1];

        for(int i = 0;i<lSize;i++) { // O(l) Where l is the left list size
            leftList[i] = unsortedList[l + i];
        }
        leftList[leftList.length - 1] = Integer.MAX_VALUE;

        for(int i = 0;i<rSize;i++) { // O(r) Where r is the left list size
            rightList[i] = unsortedList[m+i+1];
        }
        rightList[rightList.length - 1] = Integer.MAX_VALUE;

        int i = 0,j=0;
        for (int k = l; k <= r;k++) { // O(l+r) Where l,r is the left and right list size
            if(leftList[i] <= rightList[j]) {
                unsortedList[k] = leftList[i];
                i++;
            } else {
                unsortedList[k] = rightList[j];
                j++;
            }
        }
    }
}
