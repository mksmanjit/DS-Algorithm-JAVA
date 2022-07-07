package heap;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Build Min Heap Time Complexity : O(n), It seems Time Complexity should be O(nlogn) as heapify is taking O(logn) and
 * we are iterating over n/2 element in minHeap method, So it should be O(nlogn) but in reality it is O(n)
 * See in Detail here : http://www.cs.umd.edu/~meesh/351/mount/lectures/lect14-heapsort-analysis-part.pdf
 *
 * Finding Min : O(1)
 * Insert new element: O(logn)
 * Removing Element: O(logn)
 * Decrease Key: O(logn)
 * Increase Key: O(logn)
 *
 * Space Complexity: O(logn), Because of recursive call in minHeapify method.
 */
public class MinHeap {

    public static void main(String[] args) {
        int[] list = {4,3,2,1};
        Heap heap = new Heap(list, list.length, list.length);

        System.out.println("List" + Arrays.stream(list).boxed().collect(Collectors.toList()));
        minHeap(heap);
        System.out.println("Min Heap" + Arrays.stream(list).boxed().collect(Collectors.toList()));

        list = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        heap = new Heap(list, list.length, list.length);
        System.out.println("List" + Arrays.stream(list).boxed().collect(Collectors.toList()));
        minHeap(heap);
        System.out.println("Min Heap" + Arrays.stream(list).boxed().collect(Collectors.toList()));

        extractMinHeap(heap);
        System.out.println("Min Heap,after extracting Max element" + Arrays.stream(list).boxed().collect(Collectors.toList()));

        increaseKeyInMinHeap(heap, 10, 0);
        System.out.println("Max Heap,after increase Key" + Arrays.stream(list).boxed().collect(Collectors.toList()));

        decreaseKeyInMaxHeap(heap, 0, 7);
        System.out.println("Max Heap,after decrease Key" + Arrays.stream(list).boxed().collect(Collectors.toList()));

        insertKeyInMinHeap(heap, -1);
        System.out.println("Max Heap,after Insert Key" + Arrays.stream(heap.getElements()).boxed().collect(Collectors.toList()));

        insertKeyInMinHeap(heap, 11);
        System.out.println("Max Heap,after Insert Key" + Arrays.stream(heap.getElements()).boxed().collect(Collectors.toList()));
    }


    public static void minHeap(Heap heap) {
        int lastNonLeafNode = heap.getMaxSize()/2; // totalElement/2 gives us the last non leaf element in a binary tree.
        for(int i = lastNonLeafNode; i >0 ;i--) {
            minHeapify(heap, i);
        }

    }

    /**
     * Time Complexity = O(logn)
     * Space Complexity = O(logn)
     * @param heap
     * @return
     */
    public static int extractMinHeap(Heap heap) {
        if(heap.getSize() < 1) {
            throw new IllegalStateException("No Element present in the Heap");
        }
        int arr[] = heap.getElements();
        int max = arr[0];
        arr[0] = arr[heap.getSize() - 1];
        arr[heap.getSize() - 1] = max;
        heap.setSize(heap.getSize() - 1);
        minHeapify(heap, 1);
        return max;
    }

    /**
     * Time Complexity = O(logn)
     * Space Complexity = O(1)
     * @param heap
     * @return
     */
    public static void increaseKeyInMinHeap(Heap heap, int value, int position) {
        if(heap.getSize() < 1) {
            throw new IllegalStateException("No Element present in the Heap");
        }
        int arr[] = heap.getElements();
        if(value < arr[position]) {
            throw new IllegalStateException("This is increase Key function you can not decrease key");
        }

        arr[position] = value;
        minHeapify(heap,position + 1); // O(logn)

    }

    /**
     * Time Complexity = O(logn)
     * Space Complexity = O(1)
     * @param heap
     * @return
     */
    public static void insertKeyInMinHeap(Heap heap, int value) {
        heap.increaseSize();
        heap.getElements()[heap.getSize() - 1] = Integer.MAX_VALUE;
        decreaseKeyInMaxHeap(heap, value, heap.getSize() - 1);
    }

    /**
     * Time Complexity = O(logn)
     * Space Complexity = O(logn)
     * @param heap
     * @return
     */
    public static void decreaseKeyInMaxHeap(Heap heap, int value, int position) {
        if(heap.getSize() < 1) {
            throw new IllegalStateException("No Element present in the Heap");
        }
        int arr[] = heap.getElements();
        if(value > arr[position]) {
            throw new IllegalStateException("This is decrease Key function you can not increase key");
        }

        arr[position] = value;
        int i = position;
        while(i>0 && arr[i/2] > arr[i]) { // O(logn) equivalent to height of the binary tree.
            int temp = arr[i/2];
            arr[i/2] = arr[i];
            arr[i] = temp;
            i = i / 2;
        }
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(logn)
     * @param heap
     * @param i
     */
    public static void minHeapify(Heap heap, int i) {
        int arr[] = heap.getElements();
        int left = 2*i; // Used to find left node in a binary tree.
        int right = 2*i + 1; // Used to find right node in a binary tree.
        int smallest = i;
        if(left <= heap.getSize() && arr[left - 1] < arr[i - 1]) {
            smallest = left;
        }
        if(right <= heap.getSize() && arr[right - 1] < arr[smallest - 1]) {
            smallest = right;
        }
        if(smallest != i) {
            int temp = arr[i - 1];
            arr[i-1] = arr[smallest-1];
            arr[smallest-1] = temp;
            minHeapify(heap, smallest);
        }
    }
}
