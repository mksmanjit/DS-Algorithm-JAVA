package sorting;

import heap.Heap;
import heap.MaxHeap;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Heap sort is a comparison-based sorting technique based on Binary Heap data structure.
 *
 * extractMaxHeap takes - O(logn) time
 * and we have to call extractMaxHeap element from last non leaf element to second element.
 *
 * Why starting from the last non leaf element?
 * Because all leaf node is considered as already a Heap.
 *
 * Why till second element?
 * Because first element is already sorted.
 *
 * So in sort we have iterate over n/2, n/2 gives last non leaf elements.
 *
 * Total Time Complexity = O(n/2*logn) = O(nlogn)
 *
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] list = {2, 1, 4, 3};
        Heap heap = new Heap(list, list.length, list.length);

        System.out.println("List" + Arrays.stream(list).boxed().collect(Collectors.toList()));
        MaxHeap.maxHeap(heap);
        heapSort(heap);
        System.out.println("Sorted Heap" + Arrays.stream(list).boxed().collect(Collectors.toList()));

        list = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        heap = new Heap(list, list.length, list.length);
        System.out.println("List" + Arrays.stream(list).boxed().collect(Collectors.toList()));
        MaxHeap.maxHeap(heap);
        heapSort(heap);
        System.out.println("Sorted Heap" + Arrays.stream(list).boxed().collect(Collectors.toList()));
    }

    /**
     * Time Complexity: O(nlogn)
     * Space Complexity: O(logn)
     * @param heap
     */
    public static void heapSort(Heap heap) {

        for (int i = heap.getSize() - 1; i > 0 ; i--) { // O(n)
            MaxHeap.extractMaxHeap(heap); // O(logn)
        }
    }

}
